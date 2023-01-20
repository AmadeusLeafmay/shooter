import java.util.ArrayList;

public class Chest {
    public static void chestItem(Person gamer, ArrayList<Object> weapons) {
        float chance = (float) Math.random();
        int weapon;
        if (chance > 0.05 && chance <= 0.15) {
            weapon = 0;
        } else if (chance > 0.15 && chance <= 0.25) {
            weapon = 1;
        } else if (chance > 0.25 && chance <= 0.35) {
            weapon = 2;
        } else if (chance > 0.35 && chance <= 0.45) {
            weapon = 3;
        } else if (chance > 0.45 && chance <= 0.55) {
            weapon = 4;
        } else if (chance > 0.55 && chance <= 0.65) {
            weapon = 5;
        } else if (chance > 0.65 && chance <= 0.75) {
            weapon = 6;
        } else if (chance > 0.75 && chance <= 0.85) {
            weapon = 7;
        } else if (chance > 0.85 && chance <= 0.95) {
            weapon = 8;
        } else if (chance > 0.95 && chance <= 0.99) {
            weapon = 9;
        } else {
            weapon = 0;
        }
        chestChoice(weapon, gamer, weapons);
    }

    private static void chestChoice(int weapon, Person gamer, ArrayList<Object> weapons) {
        String name = Menu.getWeaponName(weapons.get(weapon));
        switch (Menu.chestInteraction(name)) {
            case 1:
                gamer.nullWeapons();
                gamer.setWeapon(weapons.get(weapon));
                if (gamer.getLevel() < weapon) {
                    gamer.setLevel(weapon);
                }
            case 2:
                break;
        }
    }
}

