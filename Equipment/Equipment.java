package com.company.Equipment;

import com.company.Creatures.GameObject;

public class Equipment extends GameObject {
    public double price;
    public int accessLevel = 1;
    public int level;
    public double healthPoint;
    private boolean _active;

    public void setActive(){
        _active = true;
    }
    public void disActive(){
        _active = false;
    }
    public boolean isActive(){
        return _active;
    }

    @Override
    public String toString() {
        return  name  +
                ", level=" + level +
                ", price=" + price +
                ", accessLevel=" + accessLevel +
                ", level=" + level +
                ", healthPoint=" + healthPoint +
                ", _active=" + _active +
                '}';
    }
}
