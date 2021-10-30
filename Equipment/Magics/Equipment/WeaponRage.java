package com.company.Equipment.Magics.Equipment;

import com.company.Equipment.Equipment;
import com.company.Equipment.Magics.Magic;
import com.company.Equipment.Weapons.Weapon;

public class WeaponRage extends Magic{

    float upgradePart;

    public WeaponRage(float upgradePart){
        this.upgradePart = upgradePart;
    }

    private void info(){
        System.out.println("Weapon Rage spell has been used. ");
    }

    public void use(Equipment item) {
        System.out.println("Equipment happens");
        if(item instanceof Weapon && !hasUsed && tryChance()){
            ((Weapon) item).damagePoint *= (1+upgradePart);
            hasUsed = true;
            info();
        }
    }

    @Override
    public String toString() {
        return "WeaponRage{" +
                "upgradePart=" + upgradePart +
                '}';
    }
}
