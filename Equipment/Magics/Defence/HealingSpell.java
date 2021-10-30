package com.company.Equipment.Magics.Defence;

import com.company.Equipment.Magics.Defence.Defenceble;
import com.company.Equipment.Magics.Magic;
import com.company.GamePlay.CreatureController;

public class HealingSpell extends Magic {

    private final double _healingValue;

    public HealingSpell(double _healingValue){
        this._healingValue = _healingValue;
    }

    private void info(){
        System.out.println("Healing spell has been used. ");
    }
    @Override
    public void use(CreatureController creature) {
        System.out.println("Defence happens");
        if (!hasUsed && tryChance()) {
            creature.addHealth(_healingValue);
            hasUsed = true;
            info();
        }
    }

    @Override
    public String toString() {
        return "HealingSpell{" +
                "_healingValue=" + _healingValue +
                '}';
    }
}
