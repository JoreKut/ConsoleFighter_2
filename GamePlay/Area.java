package com.company.GamePlay;

import com.company.Colors;
import com.company.Creatures.Enemy;
import com.company.Creatures.Hero;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Area implements Runnable{

    protected char[][] map;

    protected HeroController mainHero;
    protected ArrayList<EnemyController> enemyControllerList = new ArrayList<>();

    private ArrayList<Thread> threads = new ArrayList<>();

    // * - Wall
    // @ - Player
    // A-Z - Enemies

    public Area(String[] map){

        char[][] charMap = new char[map.length][];

        // Формируем массив символов из массива строк
        for(int i = 0; i < map.length; i++){
            charMap[i] = map[i].toCharArray();
        }

        this.map = charMap;
    }

    public void addEnemy(Enemy enemy){
        enemy.location = getFreeLocation();
        EnemyController enemyController = new EnemyController(enemy);
        enemyController.loadMap(map);
        enemyControllerList.add(enemyController);

        Thread enemyThread = new Thread(enemyController);
        enemyThread.start();
        threads.add(enemyThread);
    }
    public void setHero(Hero mainHero){
        mainHero.location = getFreeLocation();
        this.mainHero = new HeroController(mainHero);
        this.mainHero.loadMap(map);
        new Thread(this.mainHero).start();
    }

    private void clearDead(){
        enemyControllerList.removeIf(enemy -> !enemy.isAlive());
    }

    public Point getFreeLocation(){
        Random rand = new Random();
        int x,y;
        do {
            x = 1 + rand.nextInt(map[0].length-1);
            y = 1 + rand.nextInt(map.length-1);
        }while (map[y][x] != ' ');

        map[y][x] = 'x';
        return new Point(x,y);
    }

    private void printMap(){

        // Очистка
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if(map[i][j] != '-' && map[i][j] != '|' && map[i][j] != '#')
                    map[i][j] = ' ';
            }
        }

        // Добавление
        for(EnemyController enemy : enemyControllerList){
           map[enemy.creature.location.y][enemy.creature.location.x] = enemy.creature.mapSigh;
        }

        if(mainHero.isAlive())
            map[mainHero.creature.location.y][mainHero.creature.location.x] = mainHero.creature.mapSigh;

        // Вывод
        for(char[] line : map){
            for(char sym : line){
                if(sym == '@')
                    System.out.print(Colors.GREEN_BACKGROUND + Colors.BLACK_BOLD);
                else if(sym != '|' && sym != '-' && sym != ' ')
                    System.out.print(Colors.RED_BACKGROUND + Colors.BLACK_BOLD );

                System.out.print(sym + Colors.RESET);

            }
            System.out.println();
        }
    }

    @Override
    public void run() {
        System.out.println("I am AREA-Thread !");
        mainHero.recovery();
        // Map OUTPUT
        while(mainHero.isAlive()){
            try {
                mainHero.checkAround(enemyControllerList);
                if(!mainHero.isAlive())
                    break;
                clearDead();
                printMap();
                if(enemyControllerList.size() == 0){
                    mainHero.exit();
                    // try ro use magic
                    break;
                }
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(EnemyController enemy : enemyControllerList) {
            enemy.exit();
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(enemy.creature.name + " leaving the battle arena.");
        }


    }
}
