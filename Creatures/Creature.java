package com.company.Creatures;

import com.company.Equipment.Armors.Armor;
import com.company.Equipment.Equipment;
import com.company.Equipment.Weapons.Weapon;

import java.awt.*;
import java.util.ArrayList;

public class Creature extends GameObject {

    private static char nextSym = 65;

    public double healthPoint;
    public double damagePoint;
    public Point location;

    public boolean isAlive = true;

    public ArrayList<Equipment> inventory = new ArrayList<>();

    public char mapSigh = nextSym++;

    public Creature(String name, double healthPoint, double damagePoint){
        this.name = name;
        this.healthPoint = healthPoint;
        this.damagePoint = damagePoint;
    }

    public void checkAlive(){
        if(healthPoint <= 0)
            isAlive = false;
    }

    public void addArmor(Armor newArmor){
        boolean accept = false;

        int count = 0;

        for(Equipment item : inventory){
            if(item instanceof Armor && ((Armor) item).type == newArmor.type ){
                if(item.isActive()){
                    count++;
                    if(newArmor.healthPoint > item.healthPoint)
                        accept = true;
                }
                item.disActive();
            }
        }
        if(accept || count == 0)
            newArmor.setActive();
        inventory.add(newArmor);
    }

    public void addWeapon(Weapon newWeapon){
        boolean accept = false;
        int count = 0;
        for(Equipment item : inventory){
            if(item instanceof Weapon){
                count++;
                if(item.isActive()){
                    if(newWeapon.damagePoint > ((Weapon) item).damagePoint)
                        accept = true;
                }
                item.disActive();
            }
        }
        if(accept || count == 0)
            newWeapon.setActive();
        inventory.add(newWeapon);
    }

}
