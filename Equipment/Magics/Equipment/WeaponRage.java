package com.company.Equipment.Magics.Equipment;

import com.company.Colors;
import com.company.Equipment.Equipment;
import com.company.Equipment.Magics.Magic;
import com.company.Equipment.Weapons.Weapon;

public class WeaponRage extends Magic implements Equipmentable{

    float upgradePart;

    public WeaponRage(float upgradePart, int coast){
        this.chance = 55; // ( % )
        this.upgradePart = upgradePart;
        this.price = coast;
    }
    public WeaponRage(float upgradePart){
        this.chance = 55; // ( % )
        this.upgradePart = upgradePart;
    }
    private void info(){
        System.out.println(Colors.YELLOW_BACKGROUND + Colors.BLACK_BOLD + "Weapon Rage spell has been used. ");
    }

    public void useEquipmentUpgrade(Equipment item) {
        System.out.println("Equipment happens");
        if(item instanceof Weapon && !hasUsed && tryChance()){
            ((Weapon) item).damagePoint *= (1+upgradePart);
            hasUsed = true;
            info();
        }
    }

    @Override
    public String toString() {
        return "EQUIPMENT-SPELL WeaponRage{" +
                "upgradePart=" + upgradePart +
                '}';
    }
}
