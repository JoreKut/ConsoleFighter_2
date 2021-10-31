package com.company.Equipment.Magics.Equipment;

import com.company.Colors;
import com.company.Equipment.Armors.Armor;
import com.company.Equipment.Equipment;
import com.company.Equipment.Magics.Magic;

public class ArmorRage extends Magic implements Equipmentable{

    float upgradePart;

    public ArmorRage(float upgradePart){
        this.chance = 25; // ( % )
        this.upgradePart = upgradePart;
    }
    public ArmorRage(float upgradePart, int coast){
        this.chance = 25; // ( % )
        this.upgradePart = upgradePart;
        this.price = coast;
    }

    private void info(){
        System.out.println(Colors.YELLOW_BACKGROUND + Colors.BLACK_BOLD + "Armor Rage spell has been used. ");
    }

    public void useEquipmentUpgrade(Equipment item) {
        if(item instanceof Armor && !hasUsed && tryChance()){
            ((Armor) item).healthPoint *= (1+upgradePart);
            hasUsed = true;
            info();
        }
    }

    @Override
    public String toString() {
        return "EQUIPMENT-SPELL ArmorRage{" +
                "upgradePart=" + upgradePart +
                '}';
    }
}
