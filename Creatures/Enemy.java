package com.company.Creatures;

public class Enemy extends Creature {

    public int experiencePointForVictory;
    public int coinsForVictory;

    public Enemy(String name, double healthPoint, int level) {
        this.name = name;
        this.healthPoint = healthPoint;
        this.level = level;
        this.damagePoint = level * 10;
        this.currentDamagePoint = damagePoint;
        experiencePointForVictory = 100;
        coinsForVictory = level * 20;
    }


}
