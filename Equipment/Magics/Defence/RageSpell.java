package com.company.Equipment.Magics.Defence;

import com.company.Equipment.Magics.Magic;
import com.company.GamePlay.CreatureController;

public class RageSpell extends Magic {

    private final double _damageValue;

    public RageSpell(double _healingValue){
        this._damageValue = _healingValue;
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


}
