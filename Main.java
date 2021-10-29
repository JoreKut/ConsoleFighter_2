package com.company;

import com.company.Creatures.Enemy;
import com.company.Creatures.Hero;
import com.company.Equipment.Armors.Armor;
import com.company.Equipment.Armors.ArmorType;
import com.company.Equipment.Magics.Attack.Attackable;
import com.company.Equipment.Magics.Attack.PoisonSpell;
import com.company.Equipment.Magics.Defence.Defenceble;
import com.company.Equipment.Magics.Defence.HealingSpell;
import com.company.Equipment.Magics.Equipment.Equipmentable;
import com.company.Equipment.Magics.Equipment.WeaponRage;
import com.company.Equipment.Magics.Magic;
import com.company.Equipment.Weapons.Weapon;
import com.company.GamePlay.Area;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

interface FormatString{
    String format(int num);
}


public class Main {

    static ArrayList<Weapon> weaponList = new ArrayList<>();
    static ArrayList<Armor> armorList = new ArrayList<>();
    static ArrayList<Magic> magicList = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);

    static Hero<PoisonSpell, HealingSpell, WeaponRage> hero;

    public static void mainMenu() throws InterruptedException {
        System.out.println("Choose option: ");
        System.out.println("1. Shop ");
        System.out.println("2. Arena ");
        System.out.println("3. Crusade ");
        System.out.println("4. Profile ");
        System.out.println("5. Exit ");

        System.out.print(">> ");
        int choice = scanner.nextInt();
        switch (choice){
            case 1 -> Shop();
            case 2 -> Arena();
            case 3 -> Crusade();
            case 4 -> Profile();
        }
    }

    public static void Shop() throws InterruptedException {
        System.out.println("\t_______SHOP_______ ");
        System.out.println("\tChoose type of stuff: ");
        System.out.println("\t1. Weapon ");
        System.out.println("\t2. Armor ");
        System.out.println("\t3. Magic");
        System.out.println("\t4. Exit");

        System.out.print("\t>> ");
        int choice = scanner.nextInt();
        switch (choice){
            case 1 -> WeaponShop();
            case 2 -> ArmorShop();
            case 3 -> MagicShop();
            case 4 -> mainMenu();
        }

    }

    public static void WeaponShop() throws InterruptedException {
        System.out.println("\t\t_______WEAPON_______");
        System.out.println("\t\tChoose the weapon to buy");
        int i = 1;
        for(Weapon weapon : weaponList){
            System.out.println("\t\t" + i++ + ". " + weapon.name + "  coast: " + weapon.price + "coins");
        }
        System.out.println("\t\t" + i + ". exit");

        System.out.print("\t\t>> ");
        int choice = scanner.nextInt();
        Weapon boughtWeapon;
        if(choice < magicList.size())
            boughtWeapon = weaponList.get(choice);
        // Equipment -> add new Item
        Shop();
    }

    public static void ArmorShop() throws InterruptedException {
        System.out.println("\t\t_______ARMOR_______");
        System.out.println("\t\tChoose the armor to buy");
        int i = 1;
        for(Armor armor : armorList){
            System.out.println("\t\t" + i++ + ". " + armor.name + "  coast: " + armor.price + "coins");
        }
        System.out.println("\t\t" + i + ". exit");

        System.out.print("\t\t>> ");
        int choice = scanner.nextInt();
        Armor boughtArmor;
        if(choice < armorList.size())
            boughtArmor = armorList.get(choice);
        // Equipment -> add new Item
        Shop();
    }

    public static void MagicShop() throws InterruptedException {
        System.out.println("\t\t_______MAGIC_______");
        System.out.println("\t\tChoose the magic to buy");
        int i = 1;
        for(Magic magic : magicList){
            System.out.println("\t\t" + i++ + ". " + magic.name + "  coast: " + magic.price + "coins");
        }
        System.out.println("\t\t" + i + ". exit");

        System.out.print("\t\t>> ");
        int choice = scanner.nextInt();
        Magic boughtMagic;
        if(choice < magicList.size())
            boughtMagic = magicList.get(choice);
        // Equipment -> add new Item
        Shop();
    }

    public static void Arena() throws InterruptedException {
        System.out.println("\t_______ARENA_______ ");

        mainMenu();
    }

    public static void Crusade() throws InterruptedException {
        System.out.println("\t_______CRUSADE_______ ");

        String[] battleMap = {
                "---------------",
                "|             |",
                "|             |",
                "|             |",
                "|             |",
                "---------------",
        };

        Area area = new Area(battleMap);

        Armor gucci_helmet = new Armor(50, ArmorType.HELMET);
        Armor H_and_M_Chest = new Armor(150, ArmorType.CHEST);
        Armor adidas_legs = new Armor(70, ArmorType.LEGS);
        Armor nike = new Armor(60, ArmorType.BOOTS);

        PoisonSpell poisonSpell = new PoisonSpell(20);
        HealingSpell healingSpell = new HealingSpell(50);
        WeaponRage weaponRage = new WeaponRage(0.2f); // увеличение на 20%


        Weapon w1 = new Weapon("Gun", 10, 1, 1000);

        hero = new Hero<PoisonSpell, HealingSpell, WeaponRage>("Hero", 20, 20);

        hero.setAttackMagic(poisonSpell);
        hero.setDefenceMagic(healingSpell);
        hero.setEquipmentMagic(weaponRage);

        hero.addWeapon(w1);

        hero.addArmor(gucci_helmet);
        hero.addArmor(H_and_M_Chest);
        hero.addArmor(adidas_legs);
        hero.addArmor(nike);


        Enemy enemy1 = new Enemy("Jack-enemy", 50, 1);
        Enemy enemy2 = new Enemy("Steve-enemy", 50, 1);
        Enemy enemy3 = new Enemy("Anna-enemy", 50, 1);

        area.setHero(hero);
        area.addEnemy(enemy1);
        area.addEnemy(enemy2);
        area.addEnemy(enemy3);

        Thread areaThread = new Thread(area);
        areaThread.start();

        areaThread.join();

        System.out.println("\tBattle has ended!");

        mainMenu();
    }

    public static void Profile() throws InterruptedException {
        System.out.println("_______PROFILE_______ ");
        System.out.println("Character: " + hero.name);
        System.out.println("Health: " + hero.baseHealthPoints);
        System.out.println("Damage: " + hero.damagePoint);
        System.out.println("Level: " + hero.level);
        System.out.println("");;
        mainMenu();
    }

    public static void main(String[] args) throws InterruptedException {
        // write your code here

        mainMenu();

    }
}
