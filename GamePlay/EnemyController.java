package com.company.GamePlay;

import com.company.Creatures.Enemy;
import com.company.Equipment.Equipment;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class EnemyController extends CreatureController implements Runnable{

    public EnemyController(Enemy myEnemy) {
        this.creature = myEnemy;
    }

    public ArrayList<Equipment> throwLoot(){
        return creature.inventory;
    }

    @Override
    public void run() {
        while (creature.isAlive){
            try {
                if(exit)
                    break;
                if(creatureTarget != null){
                    if(!creatureTarget.isAlive()){
                        System.out.println(creature.name + ": My opponent is dead...");
                        break;
                    }
                }
                if(isFight && Objects.requireNonNull(creatureTarget).isAlive()){
                    attackEnemy();
                    TimeUnit.MILLISECONDS.sleep(400);
                }
                else if(creature.isAlive){
                    // MOVEMENT
                    move();
                    TimeUnit.MILLISECONDS.sleep(400);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
