package com.company.Equipment.Magics.Defence;

import com.company.Colors;
import com.company.Equipment.Magics.Defence.Defenceble;
import com.company.Equipment.Magics.Magic;
import com.company.GamePlay.CreatureController;

public class HealingSpell extends Magic implements Defenceble{

    private final double _healingValue;

    public HealingSpell(double _healingValue){
        this.chance = 30; // ( % )
        this._healingValue = _healingValue;
    }

    public HealingSpell(double _healingValue, int coast){
        this.chance = 30; // ( % )
        this._healingValue = _healingValue;
        this.price = coast;
    }

    private void info(){
        System.out.println(Colors.CYAN_BACKGROUND + Colors.BLACK_BOLD + "Healing spell has been used. ");
    }
    @Override
    public void useDefence(CreatureController creature) {
        System.out.println("Defence happens");
        if (!hasUsed && tryChance()) {
            creature.addHealth(_healingValue);
            hasUsed = true;
            info();
        }
    }

    @Override
    public String toString() {
        return "DEFENCE-SPELL HealingSpell{" +
                "_healingValue=" + _healingValue +
                '}';
    }
}
