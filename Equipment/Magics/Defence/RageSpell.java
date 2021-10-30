package com.company.Equipment.Magics.Defence;

import com.company.Equipment.Magics.Magic;
import com.company.GamePlay.CreatureController;

public class RageSpell extends Magic {

    private final double _damageValue;

    public RageSpell(double _damageValue){
        this._damageValue = _damageValue;
    }

    private void info(){
        System.out.println("Rage spell has been used. ");
    }

    @Override
    public void use(CreatureController creature){
        System.out.println("Defence happens");
        if(!hasUsed && tryChance()) {
            creature.takeDamage(_damageValue);
            hasUsed = true;
            info();
        }
    }

    @Override
    public String toString() {
        return "RageSpell{" +
                "_damageValue=" + _damageValue +
                '}';
    }
}
