package com.company.GamePlay;

import com.company.Creatures.Enemy;

import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class EnemyController extends CreatureController implements Runnable{

    //public Enemy creature;
    //public HeroController characterTarget;


    public EnemyController(Enemy myEnemy) {
        this.creature = myEnemy;
    }

    /*
    private void move() throws InterruptedException {
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
            TimeUnit.MILLISECONDS.sleep(1000);
        }

    }

     */
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
                    TimeUnit.MILLISECONDS.sleep(1000);
                }
                else if(creature.isAlive){
                    // MOVEMENT
                    move();
                    TimeUnit.MILLISECONDS.sleep(100);
                    //System.out.println(creature.name + " is waiting for battle...");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
