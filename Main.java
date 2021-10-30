package com.company;

import com.company.Creatures.Enemy;
import com.company.Creatures.Hero;
import com.company.Equipment.Armors.Armor;
import com.company.Equipment.Armors.ArmorType;
import com.company.Equipment.Equipment;
import com.company.Equipment.Magics.Attack.Attackable;
import com.company.Equipment.Magics.Attack.PoisonSpell;
import com.company.Equipment.Magics.Defence.Defenceble;
import com.company.Equipment.Magics.Defence.HealingSpell;
import com.company.Equipment.Magics.Defence.RageSpell;
import com.company.Equipment.Magics.Equipment.ArmorRage;
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

    static Hero<PoisonSpell, HealingSpell, WeaponRage> hero = new Hero<PoisonSpell, HealingSpell, WeaponRage>("Hero", 20, 20);

    public static void loadWeaponShop(){
        weaponList.add(new Weapon("AK-47", 47,  600,2 ));
        weaponList.add(new Weapon("Desert Eagle", 30,  1000, 3));
        weaponList.add(new Weapon("USP-S", 20, 900,3));
        weaponList.add(new Weapon("Rocket-M", 60, 4000, 5));
    }

    public static void loadArmorShop(){

        armorList.add(new Armor("Тканевая броня | ШЛЕМ", 10, ArmorType.HELMET   , 10,1));
        armorList.add(new Armor("Тканевая броня | НАГРУНИК", 10, ArmorType.CHEST, 60,1));
        armorList.add(new Armor("Тканевая броня | ШТАНЫ", 10, ArmorType.LEGS    , 30,1));
        armorList.add(new Armor("Тканевая броня | БОТИНКИ", 10, ArmorType.BOOTS , 15,1));

        armorList.add(new Armor("Кожаная броня | ШЛЕМ", 20, ArmorType.HELMET    , 100, 3));
        armorList.add(new Armor("Кожаная броня | НАГРУНИК", 20, ArmorType.CHEST , 600, 3));
        armorList.add(new Armor("Кожаная броня | ШТАНЫ", 20, ArmorType.LEGS     , 300, 3));
        armorList.add(new Armor("Кожаная броня | БОТИНКИ", 20, ArmorType.BOOTS  , 150, 3));


        armorList.add(new Armor("Железная броня | ШЛЕМ", 40, ArmorType.HELMET   , 300, 6));
        armorList.add(new Armor("Железная броня | НАГРУНИК", 40, ArmorType.CHEST, 1800, 6));
        armorList.add(new Armor("Железная броня | ШТАНЫ", 40, ArmorType.LEGS    , 900, 6));
        armorList.add(new Armor("Железная броня | БОТИНКИ", 40, ArmorType.BOOTS , 450, 6));


        armorList.add(new Armor("Алмазная броня | ШЛЕМ", 80, ArmorType.HELMET   , 900, 15));
        armorList.add(new Armor("Алмазная броня | НАГРУНИК", 80, ArmorType.CHEST, 1800, 15));
        armorList.add(new Armor("Алмазная броня | ШТАНЫ", 80, ArmorType.LEGS    , 2700, 15));
        armorList.add(new Armor("Алмазная броня | БОТИНКИ", 80, ArmorType.BOOTS , 1350, 15));

    }

    public static void loadHero(){
        Armor gucci_helmet = new Armor("Default helmet ", 50, ArmorType.HELMET);
        Armor H_and_M_Chest = new Armor("Default Chest ", 150, ArmorType.CHEST);
        Armor adidas_legs = new Armor("Default Legs ", 70, ArmorType.LEGS);
        Armor nike = new Armor("Default Boots ",60, ArmorType.BOOTS);

        PoisonSpell poisonSpell = new PoisonSpell(20);
        HealingSpell healingSpell = new HealingSpell(50);
        WeaponRage weaponRage = new WeaponRage(0.2f); // увеличение на 20%


        Weapon w1 = new Weapon("Gun", 10, 1, 1000);

        hero.setAttackMagic(poisonSpell);
        hero.setDefenceMagic(healingSpell);
        hero.setEquipmentMagic(weaponRage);

        hero.addWeapon(w1);

        hero.addArmor(gucci_helmet);
        hero.addArmor(H_and_M_Chest);
        hero.addArmor(adidas_legs);
        hero.addArmor(nike);

    }

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
            System.out.println("\t\t" + (hero.level < weapon.accessLevel ? Colors.BLACK_BACKGROUND + Colors.WHITE_BOLD + "LOCKED " : Colors.WHITE_BACKGROUND + Colors.BLACK_BOLD +  "ACCESSED ") + i++ + ". " + weapon.name + "  coast: " + (int)weapon.price + "$ ACCESS_LEVEL: " + weapon.accessLevel + "\t\t" + Colors.RESET);
        }
        System.out.println("\t\t" + i + ". exit");

        System.out.print("\t\t>> ");
        int choice = scanner.nextInt();

        while (choice < i){
            Weapon boughtWeapon;
            if(choice < weaponList.size() + 1) {
                boughtWeapon = weaponList.get(choice - 1);

                if(boughtWeapon.accessLevel <= hero.level){

                    if(boughtWeapon.price < hero.coins){

                        hero.addWeapon(boughtWeapon);
                        hero.coins -= boughtWeapon.price;
                        System.out.println("Поздравляем с приобретением !");
                    }
                    else {
                        System.out.println("Вам нужно подкопить больше денег... "+ boughtWeapon.name + " пока что слишком дорогое для вас.");
                    }
                }
                else {
                    System.out.println("Вам нужно прокачать вашего героя... "+ boughtWeapon.name + " оружие вам еще недоступно((( ");
                }

            }

            System.out.print("\t\t>> ");
            choice = scanner.nextInt();

        }

            // Equipment -> add new Item
        Shop();
    }

    public static void ArmorShop() throws InterruptedException {
        System.out.println("\t\t_______ARMOR_______");
        System.out.println("\t\tChoose the armor to buy");
        int i = 1;
        for(Armor armor : armorList){
            System.out.println("\t\t"+ (hero.level < armor.accessLevel ? Colors.BLACK_BACKGROUND + Colors.WHITE_BOLD + "LOCKED" : Colors.WHITE_BACKGROUND + Colors.BLACK_BOLD + "ACCESSED ") + i++ + ". " + armor.name + "  coast: " + (int)armor.price + "coins ACCESS_LEVEL: " + armor.accessLevel + "\t\t" + Colors.RESET);
        }
        System.out.println("\t\t" + i + ". exit");

        System.out.print("\t\t>> ");
        int choice = scanner.nextInt();
        while (choice < i){
            Armor boughtArmor;
            if(choice < armorList.size() + 1) {
                boughtArmor = armorList.get(choice - 1);

                if(boughtArmor.accessLevel <= hero.level){

                    if(boughtArmor.price <= hero.coins){

                        hero.addArmor(boughtArmor);
                        hero.coins -= boughtArmor.price;
                        System.out.println("\t\tПоздравляем с приобретением !");
                    }
                    else {
                        System.out.println("\t\tВам нужно подкопить больше денег..." + boughtArmor.name + " пока что слишком дорогое для вас.");
                    }
                }
                else {
                    System.out.println("\t\tВам нужно прокачать вашего героя... "+ boughtArmor.name + " вам еще недоступна((( ");
                }
            }

            System.out.print("\t\t>> ");
            choice = scanner.nextInt();

        }


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
        String[] battleMap = {
                // '|' or '-'
                "------------------------------------",
                "|                                  |",
                "|                                  |",
                "|                                  |",
                "------------------------------------",
        };

        int enemyCounter = 5;
        int enemyLevel = 1;
        Scanner scanner = new Scanner(System.in);
        while(hero.isAlive){
            Area battleArena = new Area(battleMap);
            battleArena.setHero(hero);

            for(int i = 0; i < enemyCounter; i++){
                battleArena.addEnemy(new Enemy("Zombie", 50, enemyLevel));
            }

            Thread battle = new Thread(battleArena);

            battle.start();
            battle.join();

            enemyCounter++;
            enemyLevel++;
            System.out.println("Next level: " + enemyLevel);
            System.out.println("Continue? y/n");

            if(scanner.nextLine().equals("n")){
                break;
            }

        }


        mainMenu();
    }

    public static void Crusade() throws InterruptedException {
        System.out.println("\t_______CRUSADE_______ ");

        String[] battleMap = {
                // '|' or '-'
                "---------------",
                "|             |",
                "|             |",
                "|             |",
                "---------------",
        };

        Area area = new Area(battleMap);



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
        System.out.println(Colors.GREEN_BACKGROUND + Colors.BLACK_BOLD + "\tCharacter: " + hero.name + "\t" + Colors.RESET);
        System.out.println(Colors.GREEN_BACKGROUND + Colors.BLACK_BOLD + "\tHealth: " + hero.baseHealthPoints + "\t" + Colors.RESET);
        System.out.println(Colors.GREEN_BACKGROUND + Colors.BLACK_BOLD + "\tDamage: " + hero.damagePoint + "\t" + Colors.RESET);
        System.out.println(Colors.GREEN_BACKGROUND + Colors.BLACK_BOLD + "\tLevel: " + hero.level + "\t" + Colors.RESET);
        System.out.println(Colors.GREEN_BACKGROUND + Colors.BLACK_BOLD + "\tExperience: " + hero.experience + "\t" + Colors.RESET);
        System.out.println(Colors.GREEN_BACKGROUND + Colors.BLACK_BOLD + "\tCoins: " + hero.coins + "\t" + Colors.RESET);
        System.out.println(Colors.GREEN_BACKGROUND + Colors.BLACK_BOLD + "\tInventory: " + "\t" + Colors.RESET);
        for(Equipment item : hero.inventory){
            System.out.println("\t\t" + Colors.CYAN_BACKGROUND + Colors.BLACK_BOLD +" * " + item  + "\t" + Colors.RESET);
        }
        System.out.println("");;
        mainMenu();
    }

    public static void main(String[] args) throws InterruptedException {
        // write your code here
        loadWeaponShop();
        loadArmorShop();
        loadHero();
        mainMenu();

    }
}
