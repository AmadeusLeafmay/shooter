package Game.Logic;

import Game.Objects.Person;
import Game.Objects.Room;
import Game.Weapon.CombinedWeapons;
import Game.Weapon.MeleeWeapons;
import Game.Weapon.RangeWeapons;

import java.util.Scanner;

public class Menu {
    private final static Scanner in = new Scanner(System.in);

    public static int startMenu() {
        System.out.println("Добро пожаловать\n1.Начать игру\n2.Выйти из игры\n");
        return in.nextInt();
    }

    public static int roomInteraction(Room room) {

        StringBuilder builder = new StringBuilder();
        builder.append("Двери за вами захлопнулись, вы видите ");
        switch (room.getSize()) {
            case 1 -> builder.append("маленькую ");
            case 2 -> builder.append("среднюю ");
            case 3 -> builder.append("большую ");
        }
        builder.append("комнату. В ней есть проход прямо");
        if (room.getLeftDoor()) {
            builder.append(", проход налево");
        }
        if (room.getRightDoor()) {
            builder.append(", проход направо");
        }
        if (room.getChest()) {
            builder.append(", также вы замечаете сундук в маленькой боковой комнате");
        }
        builder.append("\n");
        builder.append("Что будете делать?\n");
        builder.append("1.Идти прямо\n");
        if (room.getLeftDoor()) {
            builder.append("2.Идти налево\n");
        }
        if (room.getRightDoor()) {
            builder.append("3.Идти направо\n");
        }
        if (room.getChest()) {
            builder.append("4.Открыть сундук\n");
        }
        builder.append("5.Закончить игру\n");
        System.out.println(builder);
        return in.nextInt();
    }

    public static int duelInteraction(Person gamer, Person enemy) {
        System.out.println("Оружие в руках противника:" + getWeaponName(enemy) + "\nЧто будете делать?");
        if(gamer.getWeapon() instanceof MeleeWeapons){
            System.out.println("1.Бить в ближнем\n2.Бросить оружие в врага\n5.Бежать\n");
        } else if (gamer.getWeapon() instanceof RangeWeapons) {
            System.out.println("3.Cтрелять\n4.Перезарядка\n5.Бежать\n");
        } else if (gamer.getWeapon() instanceof CombinedWeapons){
            System.out.println("1.Бить в ближнем\n2.Бросить оружие в врага\n3.Cтрелять\n4.Перезарядка\n5.Бежать\n");
        } else {
            System.out.println("5.Бежать\n");
        }
        return in.nextInt();
    }

    public static int enemyInteraction() {
        System.out.println("Вы видите врага. Что будете делать?\n1.Сражаться\n2.Бежать");
        return in.nextInt();
    }

    public static int chestInteraction(String name) {
        System.out.println("Вам выпадает:" + name + ".\n1.Взять\n2.Оставить\n");
        return in.nextInt();
    }

    public static void deathMessage() {
        System.out.println("Вы умерли, игра окончена");
    }

    public static void backShootMessage(int damage) {
        System.out.printf("Вам выстрелили в спину, нанеся %d урона\n", damage);
    }

    public static void weaponNameMessage(Person gamer) {
        System.out.println("Оружие у вас в руках:" + getWeaponName(gamer));
    }

    private static String getWeaponName(Person person) {
        if(person.getWeapon() instanceof MeleeWeapons){
            return ((MeleeWeapons) person.getWeapon()).getName();
        } else if (person.getWeapon() instanceof RangeWeapons){
            return ((RangeWeapons) person.getWeapon()).getName();
        } else if (person.getWeapon() instanceof CombinedWeapons){
            return ((CombinedWeapons) person.getWeapon()).getName();
        } else {
            return "Никакого";
        }
    }

    public static String getWeaponName(Object weapon) {
        if(weapon instanceof MeleeWeapons){
            return ((MeleeWeapons) weapon).getName();
        } else if (weapon instanceof RangeWeapons){
            return ((RangeWeapons) weapon).getName();
        } else if (weapon instanceof CombinedWeapons){
            return ((CombinedWeapons) weapon).getName();
        } else {
            return "Никакого";
        }
    }

    public static void enemyHealth(Person enemy) {
        System.out.println("Здоровье врага:" + enemy.getCurrentHealth());
    }

    public static void gamerHealth(Person gamer) {
        System.out.println("Ваше здоровье:" + gamer.getCurrentHealth());
    }

    public static void attackMiss() {
        System.out.println("Промах");
    }

    public static void attackDamage(int damage) {
        System.out.println("Нанесено урона:" + damage);
    }

    public static void gamerTurn() {
        System.out.println("Ваш ход:");
    }

    public static void enemyTurn() {
        System.out.println("Ход противника:");
    }

    public static void magazineIsFull() {
        System.out.println("Магазин полный");
    }

    public static void needReload() {
        System.out.println("Нужна перезарядка");
    }

    public static void enemyDeath() {
        System.out.println("Вам удалось пройти");
    }

    public static void frame() {
        System.out.println("******************************************************");
    }

}
