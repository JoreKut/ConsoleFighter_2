package com.company.Equipment.Magics.Attack;

import com.company.Equipment.Armors.ArmorType;
import com.company.Equipment.Magics.Magic;
import com.company.GamePlay.CreatureController;

public class PoisonSpell extends Magic {

    private final double damage;

    public  PoisonSpell(double damage){
        this.damage = damage;
    }

    private void info(){
        System.out.println("Poison spell has been used. ");
    }

    @Override
    public void use(CreatureController enemy) {
        System.out.println("Attack happens");
        if(!hasUsed && tryChance()) {
            enemy.takeDamage(damage, ArmorType.getRandomType());
            hasUsed = true;
            info();
        }
    }
}
