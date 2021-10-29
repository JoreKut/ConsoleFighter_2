package com.company.Creatures;

import com.company.Equipment.Equipment;
import com.company.Equipment.Magics.Attack.Attackable;
import com.company.Equipment.Magics.Defence.Defenceble;
import com.company.Equipment.Magics.Magic;

import java.util.ArrayList;

public class Hero<A extends Magic, B extends Magic, C extends Magic> extends Creature {

    public A attackMagic;
    public B defenceMagic;
    public C equipmentMagic;

    public double baseHealthPoints;
    public int experience;
    public ArrayList<Equipment> backpack = new ArrayList<>();

    public Hero(String name, double healthPoint, double damagePoint) {
        super(name, healthPoint, damagePoint);
        this.baseHealthPoints = healthPoint;
        mapSigh = '@';
        experience = 0;
    }

    public void setAttackMagic(A attackMagic){
        this.attackMagic = attackMagic;
        inventory.add(attackMagic);
    }
    public void setDefenceMagic(B defenceMagic){
        this.defenceMagic = defenceMagic;
        inventory.add(defenceMagic);
    }
    public void setEquipmentMagic(C equipmentMagic){
        this.equipmentMagic = equipmentMagic;
        inventory.add(equipmentMagic);
    }


    public void recovery(){
        this.healthPoint = baseHealthPoints;
    }

}
