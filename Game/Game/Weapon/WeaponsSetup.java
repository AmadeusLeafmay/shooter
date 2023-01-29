package Game.Weapon;

import java.util.ArrayList;

public class WeaponsSetup {
    private static final ArrayList<Object> weapons = new ArrayList<>();

    public static void addWeapon(Object weapon) {
        weapons.add(weapon);
    }

    public static ArrayList<Object> getWeapons() {
        return weapons;
    }
}
