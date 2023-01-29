package Game.Interfaces;

import Game.Logic.Menu;
import Game.Objects.Person;
import Game.Objects.Room;

public interface MeleeInterface {

    default void meleeAttack(int damage, Room room, Person enemy) {
        int roomSize = room.getSize();
        float chance = (float) Math.random();
        if (chance - (0.15 * roomSize) > 0.4) {
            enemy.setCurrentHealth(enemy.getCurrentHealth() - damage);
            Menu.attackDamage(damage);
        } else {
            Menu.attackMiss();
        }
    }

    default void throwAttack(int damage, Room room, Person enemy) {
        int roomSize = room.getSize();
        float chance = (float) Math.random();
        if (chance - (0.15 * roomSize) > 0.3) {
            enemy.setCurrentHealth(enemy.getCurrentHealth() - damage * 8);
        } else {
            Menu.attackMiss();
        }
    }
}
