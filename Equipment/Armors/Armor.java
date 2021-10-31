package com.company.Equipment.Armors;

import com.company.Equipment.Equipment;

public class Armor extends Equipment implements Protectable {

    public ArmorType type;

    public Armor(String name, double healthPoint, ArmorType type, int price ,int accessLevel){
        this.level = 1;
        this.name = name;
        this.healthPoint = healthPoint;
        this.type = type;
        this.accessLevel = accessLevel;
        this.price = price;
    }

    public Armor(String name, double healthPoint, ArmorType type){

        this.name = name;
        this.healthPoint = healthPoint;
        this.type = type;
        this.price = level * 200;
        this.level = 1;
        this.accessLevel = 1;
    }

    public boolean isBroken(){
        return healthPoint <= 0;
    }

    @Override
    public double takeDamage(double damage) {

        if (healthPoint > 0){
            healthPoint -= damage;
            System.out.println("Отражено " + damage + " HP    Броня: " + healthPoint);
            return 0;
        }

        System.out.println("Отражено 0 HP Броня сломана");

        return damage;
    }

    @Override
    public String toString() {
        return "Armor{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", type=" + type +
                ", price=" + price +
                ", accessLevel=" + accessLevel +
                ", level=" + level +
                ", healthPoint=" + healthPoint +
                '}';
    }
}
