package com.company;

public enum MapCharacters {

    HERO('@'),
    ENEMY('E'),
    SPACE(' '),
    LOCK_AREA('x'),
    WALL('*');

    public char sym;

    MapCharacters(char sym) {
        this.sym = sym;
    }


}
