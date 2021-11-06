package com.company.Creatures;

import com.company.MapCharacters;

public class Enemy extends Creature {

    public int experiencePointForVictory;
    public int coinsForVictory;

    public Enemy(String name, double healthPoint, int level) {
        this.mapSigh = MapCharacters.ENEMY.sym;
        this.name = name;
        this.healthPoint = healthPoint;
        this.level = level;
        this.damagePoint = level * 10;
        this.currentDamagePoint = damagePoint;
        experiencePointForVictory = 100;
        coinsForVictory = level * 20;
    }


}
