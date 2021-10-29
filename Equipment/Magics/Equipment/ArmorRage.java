package com.company.Equipment.Magics.Equipment;

import com.company.Equipment.Armors.Armor;
import com.company.Equipment.Equipment;
import com.company.Equipment.Magics.Magic;

public class ArmorRage extends Magic{

    float upgradePart;

    public ArmorRage(float upgradePart){
        this.upgradePart = upgradePart;
    }

    private void info(){
        System.out.println("Armor Rage spell has been used. ");
    }

    public void use(Equipment item) {
        if(item instanceof Armor && !hasUsed && tryChance()){
            ((Armor) item).healthPoint *= (1+upgradePart);
            hasUsed = true;
            info();
        }
    }
}
