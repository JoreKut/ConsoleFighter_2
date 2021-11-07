package com.company.GamePlay;

import com.company.Creatures.Enemy;
import com.company.Creatures.Hero;
import com.company.MapCharacters;
import com.company.UI.GraphicBattleMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Area implements KeyListener {


    protected char[][] map;

    protected HeroController mainHero;
    protected ArrayList<EnemyController> enemyControllerList = new ArrayList<>();

    private final ExecutorService  ex = Executors.newCachedThreadPool();

    private final GraphicBattleMap graphicMap = new GraphicBattleMap(600,600);

    public Area(String[] map){

        graphicMap.addKeyListener(this);
        graphicMap.setRequestFocusEnabled(true);
        graphicMap.loadMap(map);
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

        ex.submit(enemyController);
    }
    public void setHero(Hero mainHero){
        mainHero.location = getFreeLocation();
        this.mainHero = new HeroController(mainHero);
        this.mainHero.loadMap(map);

        ex.submit(this.mainHero);
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
        }while (map[y][x] != MapCharacters.SPACE.sym);

        map[y][x] = MapCharacters.LOCK_AREA.sym;
        return new Point(x,y);
    }

    private void printMap(){

        // Очистка
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if(map[i][j] != MapCharacters.WALL.sym)
                    map[i][j] = MapCharacters.SPACE.sym;
            }
        }

        // Добавление
        for(EnemyController enemy : enemyControllerList){
           map[enemy.creature.location.y][enemy.creature.location.x] = enemy.creature.mapSigh;
        }

        if(mainHero.isAlive())
            map[mainHero.creature.location.y][mainHero.creature.location.x] = mainHero.creature.mapSigh;

        graphicMap.loadMap(map);

    }

    public void start(){
        graphicMap.startShowing();
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
                    break;
                }
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Выключаем мобов
        for(EnemyController enemy : enemyControllerList) {
            enemy.exit();
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(enemy.creature.name + " leaving the battle arena.");
        }

        graphicMap.breakShowing();

        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ex.shutdownNow();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!mainHero.isFight){
            int x = mainHero.creature.location.x;
            int y = mainHero.creature.location.y;



            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                if(x+1 != map[0].length && map[y][x+1] != MapCharacters.WALL.sym)
                    mainHero.creature.location.x++;
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                if(x-1 != 0 && map[y][x-1] != MapCharacters.WALL.sym)
                    mainHero.creature.location.x--;
            }

            if(e.getKeyCode() == KeyEvent.VK_DOWN){
                if(y+1 != map.length && map[y+1][x] != MapCharacters.WALL.sym)
                    mainHero.creature.location.y++;
            }
            if(e.getKeyCode() == KeyEvent.VK_UP){
                if(y+1 != 0 && map[y-1][x] != MapCharacters.WALL.sym)
                    mainHero.creature.location.y--;
            }
        }


    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
