package com.company.Equipment.Armors;

import java.util.Random;

public enum ArmorType {
    HELMET,
    CHEST,
    LEGS,
    BOOTS;

    public static ArmorType getRandomType(){
        int zone = new Random().nextInt(100);

        if(zone < 5){
            // HEAD - 5%
            return HELMET;
        }
        else if(zone < 15){
            // BOOTS - 10%
            return BOOTS;
        }
        else if(zone < 30){
            // LEGS - 15%
            return LEGS;
        }
        else{
            // CHEST - 70%
            return CHEST;
        }
    }
}
