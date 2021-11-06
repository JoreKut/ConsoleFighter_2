package com.company.Creatures;

import com.company.Equipment.Magics.Attack.Attackable;
import com.company.Equipment.Magics.Defence.Defenceble;
import com.company.Equipment.Magics.Equipment.Equipmentable;
import com.company.Equipment.Magics.Magic;
import com.company.MapCharacters;

public class Hero extends Creature {

    public double baseHealthPoints;
    public int experience;

    public Hero(String name, double healthPoint, double damagePoint, boolean DEBUG_MODE) {
        super(name, healthPoint, damagePoint);
        this.baseHealthPoints = healthPoint;
        mapSigh = MapCharacters.HERO.sym;
        experience = 0;
        if(DEBUG_MODE){
            coins = 1000000;
            level = 555;
        }
    }

    public void tryLevelUp(){
        if(experience > level*400){
            experience = 0;
            level++;
        }
    }

    public void setAttackMagic(Attackable attackMagic){
        this.attackMagic = attackMagic;
        inventory.add((Magic)attackMagic);
    }
    public void setDefenceMagic(Defenceble defenceMagic){
        this.defenceMagic = defenceMagic;
        inventory.add((Magic)defenceMagic);
    }
    public void setEquipmentMagic(Equipmentable equipmentMagic){
        this.equipmentMagic = equipmentMagic;
        inventory.add((Magic)equipmentMagic);
    }

    public void recovery(){
        this.healthPoint = baseHealthPoints;
        isAlive = true;
        currentDamagePoint = damagePoint;
    }
}
