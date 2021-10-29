package com.company.GamePlay;

import com.company.Creatures.Creature;
import com.company.Equipment.Armors.Armor;
import com.company.Equipment.Armors.ArmorType;
import com.company.Equipment.Equipment;
import com.company.Equipment.Weapons.Weapon;

import java.util.ArrayList;
import java.util.Random;


public class CreatureController {

    protected Creature creature;
    protected CreatureController creatureTarget;

    Armor helmet;
    Armor chest;
    Armor legs;
    Armor boots;

    Weapon weapon;

    public double baseHealthPoint;

    public char[][] map;
    protected boolean isFight = false;
    public boolean exit = false;

    public void attackEnemy(){

        for(Equipment item : creature.inventory){
            if(item instanceof Weapon && item.isActive()){
                ((Weapon) item).shootTo(creatureTarget);
            }
        }


        creatureTarget.takeDamage(creature.damagePoint, ArmorType.getRandomType());

        checkTargetAlive();

    }

    public void checkTargetAlive(){
        if(!creatureTarget.isAlive()) {
            isFight = false;
            exit = true;
            System.out.println("*********************************************");
            System.out.println(creature.name + ": I win !");
            System.out.println(creatureTarget.creature.name + ": I lose...");
            System.out.println("*********************************************\n\n\n");

        }
    }

    public void setOpponent(CreatureController opponent) throws InterruptedException {
        this.creatureTarget = opponent;
        isFight = true;

    }
    public boolean isAlive(){
        return creature.isAlive;
    }


    public void takeDamage(double damagePoint) {

        creature.healthPoint -= damagePoint;
        creature.checkAlive();
    }

    public void takeDamage(double damagePoint, ArmorType armorArea){

        // Ищем в инвенторе предмет : тип -> Armor, поле ArmorType == armorArea

        for(Equipment item : creature.inventory){
            if(item instanceof Armor && ((Armor) item).type == armorArea){
                // Снижение дамага (или нет)
                System.out.print(creature.name + " за");
                damagePoint = ((Armor) item).takeDamage(damagePoint);

                if(((Armor) item).isBroken()){
                    System.out.println(((Armor) item).type.name() + item.name + " is broken (");
                }

                break;
            }
        }

        // То, что осталось прокидываем как обычный урон

        takeDamage(damagePoint);
    }

    public void loadMap(char[][] map){
        this.map = map;
    }

    public ArrayList<Equipment> throwLoot(){
        return creature.inventory;
    }

    public void addHealth(double adding){
        creature.healthPoint += adding;
    }

    public void exit(){
        exit = true;
    }
    public void move() throws InterruptedException {

        int direction = new Random().nextInt(4);
        int stepNumber = new Random().nextInt(5);

        for(int i = 0; i < stepNumber; i++){

            if(direction == 0){
                // UP
                creature.location.y--;
                if(map[creature.location.y][creature.location.x] != ' ')
                    creature.location.y++;
            }else if(direction == 1){
                // RIGHT
                creature.location.x++;
                if(map[creature.location.y][creature.location.x] != ' ')
                    creature.location.x--;
            }else if(direction == 2){
                // DOWN
                creature.location.y++;
                if(map[creature.location.y][creature.location.x] != ' ')
                    creature.location.y--;
            }else {
                // LEFT
                creature.location.x--;
                if(map[creature.location.y][creature.location.x] != ' ')
                    creature.location.x++;
            }
        }

    }

    public String getName(){
        return creature.name;
    }
}
