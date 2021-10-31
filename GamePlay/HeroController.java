package com.company.GamePlay;

import com.company.Creatures.Enemy;
import com.company.Creatures.Hero;
import com.company.Equipment.Armors.Armor;
import com.company.Equipment.Equipment;
import com.company.Equipment.Magics.Defence.HealingSpell;
import com.company.Equipment.Magics.Magic;
import com.company.Equipment.Weapons.Weapon;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HeroController extends CreatureController implements Runnable{

    Hero buffHero;

    public HeroController(Hero myHero) {
        buffHero = myHero;
        this.creature = myHero;
        this.baseHealthPoint = myHero.healthPoint;
    }

    public void checkAround(ArrayList<EnemyController> enemyList) throws InterruptedException {
        for(EnemyController enemy : enemyList){
            if(aroundMe(enemy)){
                // SET FIGHT MODE
                setOpponent(enemy);
                enemy.setOpponent(this);

                break;
            }
        }
    }
    public void checkTargetAlive(){
        if(!creatureTarget.isAlive()) {
            isFight = false;
            getLoot(((EnemyController)creatureTarget).throwLoot());

            ((Hero)creature).experience += ((Enemy)creatureTarget.creature).experiencePointForVictory* creature.level;
            creature.coins += ((Enemy) creatureTarget.creature).coinsForVictory;
            ((Hero) creature).tryLevelUp();

            System.out.println("*********************************************");
            System.out.println(creature.name + ": I win !");
            System.out.println(creatureTarget.creature.name + ": I lose...");
            System.out.println("*********************************************\n\n\n");

        }
    }

    private boolean aroundMe(EnemyController enemy){
        int x = enemy.creature.location.x;
        int y = enemy.creature.location.y;

        int myX = creature.location.x;
        int myY = creature.location.y;

        return x <= myX+1 && x >= myX-1 && y <= myY+1 && y >= myY-1;
    }

    public void recovery(){
        this.creature.healthPoint = this.baseHealthPoint;
        this.creature.isAlive = true;
    }

    public void getLoot(ArrayList<Equipment> enemyLoot){
        for(Equipment lootItem : enemyLoot){

            if(lootItem instanceof Armor){
                System.out.println("После боя получена новая вещь(броня) - " + lootItem.name);
                creature.addArmor((Armor) lootItem);
            }

            if(lootItem instanceof Weapon){
                System.out.println("После боя получена новая вещь(Оружие) - " + lootItem.name);
                creature.addWeapon((Weapon) lootItem);
            }

            if(lootItem instanceof HealingSpell){
                if(creature.healthPoint < baseHealthPoint) {
                    System.out.print("HealingSpell - " + creature.healthPoint);
                    ((HealingSpell) lootItem).use(this);
                        System.out.println(" - " + creature.healthPoint);
                }
            }

        }
    }

    private void tryToUseMagic(){

        int chance = new Random().nextInt(3);

        if(chance == 0 && creature.attackMagic != null){
            System.out.println("ATTACKABLE SPELL...");
            creature.attackMagic.useAttack(creatureTarget);
        }else if(chance == 1 && creature.defenceMagic != null){
            System.out.println("DEFENCEBLE SPELL...");
            creature.defenceMagic.useDefence(this);
        }else if(creature.equipmentMagic != null) {
            System.out.println("EQUIPMENT SPELL...");
            // Применяем к каждой активной броне
            for(Equipment item : creature.inventory){
                if(item instanceof Armor && item.isActive()){
                    creature.equipmentMagic.useEquipmentUpgrade(item);
                }
            }
        }
    }

    @Override
    public void run() {
        while (creature.isAlive && !exit){
            try {
                if(isFight && creatureTarget.isAlive()){
                    attackEnemy();
                    tryToUseMagic();
                    TimeUnit.MILLISECONDS.sleep(1000);
                }
                else{
                    // MOVEMENT
                    move();
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
