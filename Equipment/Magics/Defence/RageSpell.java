package com.company.Equipment.Magics.Defence;

import com.company.Colors;
import com.company.Equipment.Magics.Magic;
import com.company.GamePlay.CreatureController;

public class RageSpell extends Magic implements Defenceble{

    private final double _damageValue;

    public RageSpell(double _damageValue){
        this.chance = 20; // ( % )
        this._damageValue = _damageValue;
    }

    public RageSpell(double _damageValue, int coast){
        this.chance = 20; // ( % )
        this._damageValue = _damageValue;
        this.price = coast;
    }

    private void info(){
        System.out.println(Colors.CYAN_BACKGROUND + Colors.BLACK_BOLD + "Rage spell has been used. ");
        System.out.println(Colors.RESET);
    }

    @Override
    public void useDefence(CreatureController creature){
        System.out.println("Defence happens");
        if(!hasUsed && tryChance()) {
            creature.addDamagePoint(_damageValue);
            hasUsed = true;
            info();
        }
    }

    @Override
    public String toString() {
        return "DEFENCE-SPELL RageSpell{" +
                "_damageValue=" + _damageValue +
                '}';
    }
}
