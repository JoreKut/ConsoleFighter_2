package com.company.Equipment.Weapons;

import com.company.Equipment.Armors.ArmorType;
import com.company.Equipment.Equipment;
import com.company.Equipment.Magics.Attack.Attackable;
import com.company.GamePlay.CreatureController;

public class Weapon extends Equipment {
    public double damagePoint;
    public int reloadDelay;
    public Attackable superpower;  // Срабатывает перед выстрелом

    public Weapon(String name, double damagePoint, int accessLevel, int reloadDelay){
        this.name = name;
        this.damagePoint = damagePoint;
        this.accessLevel = accessLevel;
        this.reloadDelay = reloadDelay;
    }

    public void setMagic(Attackable superpower){
        this.superpower = superpower;
    }

    public void shootTo(CreatureController creature){

        ArmorType zone = ArmorType.getRandomType();
        System.out.println("Shoot to " + creature.getName() + " area: " + zone.name());
        creature.takeDamage(damagePoint, zone);
    }

}
