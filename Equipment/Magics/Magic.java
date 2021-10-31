package com.company.Equipment.Magics;

import com.company.Equipment.Equipment;
import com.company.GamePlay.CreatureController;

import java.util.Random;

public class Magic extends Equipment {

    public int chance;
    protected boolean hasUsed = false;

    public Magic(int chance){
        this.chance = chance;
    }

    public Magic() {

    }

    protected boolean tryChance(){
        int rand = new Random().nextInt(100);
        return chance < rand;
    }

    public void use(CreatureController creature){}

}
