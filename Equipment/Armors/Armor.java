package com.company.Equipment.Armors;

import com.company.Equipment.Equipment;

public class Armor extends Equipment implements Protectable {

    public ArmorType type;
    private boolean _active = false;

    public Armor(double healthPoint, ArmorType type){
        this.healthPoint = healthPoint;
        this.type = type;
    }


    public boolean isBroken(){
        return healthPoint <= 0;
    }

    @Override
    public double takeDamage(double damage) {

        if (healthPoint > 0){
            healthPoint -= damage;
            System.out.println("Отражено " + damage + " HP    Броня: " + healthPoint);
            return 0;
        }

        System.out.println("Отражено 0 HP Броня сломана");

        return damage;
    }
}
