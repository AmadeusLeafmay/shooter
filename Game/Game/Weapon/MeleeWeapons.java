package Game.Weapon;

import Game.Interfaces.MeleeInterface;

public class MeleeWeapons implements MeleeInterface {
    private final int damage;
    String name;

    public MeleeWeapons(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }
}
