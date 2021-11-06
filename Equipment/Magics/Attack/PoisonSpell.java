package com.company.Equipment.Magics.Attack;

import com.company.Colors;
import com.company.Equipment.Armors.ArmorType;
import com.company.Equipment.Magics.Magic;
import com.company.GamePlay.CreatureController;

public class PoisonSpell extends Magic implements Attackable{

    private final double damage;

    public  PoisonSpell(double damage){
        this.chance = 10; // ( % )
        this.damage = damage;
    }

    public  PoisonSpell(double damage, int coast){
        this.chance = 10; // ( % )
        this.damage = damage;
        this.price = coast;
    }

    private void info(){
        System.out.println(Colors.PURPLE_BACKGROUND + Colors.BLACK_BOLD + "Poison spell has been used. ");
        System.out.println(Colors.RESET);
    }

    @Override
    public void useAttack(CreatureController enemy) {
        System.out.println("Attack happens");
        if(tryChance()) {
            enemy.takeDamage(damage, ArmorType.getRandomType());
            hasUsed = true;
            info();
        }
    }

    @Override
    public String toString() {
        return "ATTACK-SPELL PoisonSpell{" +
                "damage=" + damage +
                '}';
    }
}
