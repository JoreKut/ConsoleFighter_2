package com.company.GamePlay;

import com.company.Colors;
import com.company.Creatures.Creature;
import com.company.Equipment.Armors.Armor;
import com.company.Equipment.Armors.ArmorType;
import com.company.Equipment.Equipment;
import com.company.Equipment.Weapons.Weapon;

import java.util.Random;


public class CreatureController {

    protected Creature creature;
    protected CreatureController creatureTarget;

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


        creatureTarget.takeDamage(creature.currentDamagePoint, ArmorType.getRandomType());

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

    public void setOpponent(CreatureController opponent) {
        this.creatureTarget = opponent;
        isFight = true;

    }
    public boolean isAlive(){
        return creature.isAlive;
    }

    public void setNewActiveArmor(){

        Armor bestOption = null;

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
                damagePoint = ((Armor) item).takeDamage(damagePoint);

                if(((Armor) item).isBroken()){
                    System.out.println(Colors.RED_BACKGROUND + Colors.BLACK_BOLD + ((Armor) item).type.name() + item.name + " is broken (");
                    creature.inventory.remove(item);
                    setNewActiveArmor();
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

    public void addHealth(double adding){
        creature.healthPoint += adding;
    }

    public void addDamagePoint(double damagePoints){
        creature.currentDamagePoint += damagePoints;
    }

    public void exit(){
        exit = true;
    }

    public void move() throws InterruptedException {

        int direction = new Random().nextInt(4);
        int stepNumber = new Random().nextInt(5);

        for(int i = 0; i < stepNumber; i++){

            if(creature.location.y == 0 || creature.location.x == 0 || creature.location.y == map.length ||  creature.location.x == map[0].length )
                continue;

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
