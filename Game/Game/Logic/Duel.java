package Game.Logic;

import Game.Objects.Person;
import Game.Objects.Room;
import Game.Weapon.CombinedWeapons;
import Game.Weapon.MeleeWeapons;
import Game.Weapon.RangeWeapons;
import static Game.Logic.Menu.*;
public class Duel {
    private static boolean turn = true;

    public static void startDuel(Person gamer, Person enemy, Room room) {
        while (gamer.getCurrentHealth() > 0 && enemy.getCurrentHealth() > 0) {
            if (enemy.getCurrentHealth() <= 0) {
                Menu.enemyDeath();
            }
            if (gamer.getCurrentHealth() <= 0) {
                Menu.deathMessage();
            }
            if (turn) {
                frame();
                gamerHealth(gamer);
                enemyHealth(enemy);
                Menu.gamerTurn();
                gamerTurn(gamer, enemy, room);
            } else {
                frame();
                Menu.enemyTurn();
                enemyTurn(enemy, gamer, room);
            }
        }
        turn = true;
    }

    private static void gamerTurn(Person gamer, Person enemy, Room room) {
        switch (duelInteraction(gamer, enemy)) {
            case 1 -> {
                meleeAttack(gamer.getWeapon(), enemy, room);
                turn = false;
            }
            case 2 -> {
                meleeThrow(gamer.getWeapon(), enemy, room);
                turn = false;
            }
            case 3 -> {
                boolean success;
                success = rangeAttack(gamer.getWeapon(), gamer, enemy, room);
                if (success) {
                    turn = false;
                } else {
                    needReload();
                }
            }
            case 4 -> {
                rangeReload(gamer.getWeapon());
                turn = rangeReload(gamer.getWeapon());
            }
            case 5 -> {
                enemy.setCurrentHealth(0);
                int backDamage = 100 + (int) (Math.random() * 300);
                gamer.setCurrentHealth(gamer.getCurrentHealth() - backDamage);
                backShootMessage(backDamage);
            }
        }
    }

    private static void enemyTurn(Person gamer, Person enemy, Room room) {
        boolean success;
        if (gamer.getWeapon() instanceof MeleeWeapons) {
            meleeAttack(gamer.getWeapon(), enemy, room);
            turn = true;
        } else if (gamer.getWeapon() instanceof RangeWeapons) {
            success = rangeAttack(gamer.getWeapon(), gamer, enemy, room);
            if (!success) {
                rangeReload(gamer.getWeapon());
            }
            turn = true;
        } else {
            if (Math.random() > 0.5) {
                meleeAttack(gamer.getWeapon(), enemy, room);
            } else {
                success = rangeAttack(gamer.getWeapon(), gamer, enemy, room);
                if (!success) {
                    rangeReload(gamer.getWeapon());
                }
            }
            turn = true;
        }
    }

    private static void meleeAttack(Object weapon, Person enemy, Room room) {
        MeleeWeapons meleeWeapon;
        CombinedWeapons combinedWeapon;
        if (weapon instanceof MeleeWeapons) {
            meleeWeapon = (MeleeWeapons) weapon;
            meleeWeapon.meleeAttack(meleeWeapon.getDamage(), room, enemy);
        } else {
            combinedWeapon = (CombinedWeapons) weapon;
            combinedWeapon.meleeAttack(combinedWeapon.getMeleeDamage(), room, enemy);
        }
    }

    private static boolean rangeAttack(Object weapon, Person gamer, Person enemy, Room room) {
        RangeWeapons rangeWeapon;
        CombinedWeapons combinedWeapon;
        if (weapon instanceof RangeWeapons) {
            rangeWeapon = (RangeWeapons) weapon;
            if (rangeWeapon.getMagazine()[0] != 0) {
                rangeWeapon.rangeAttack(rangeWeapon.getDamage(), rangeWeapon.getMagazine(), room, gamer, enemy);
                return true;
            } else {
                return false;
            }
        } else {
            combinedWeapon = (CombinedWeapons) weapon;
            if (combinedWeapon.getMagazine()[0] != 0) {
                combinedWeapon.rangeAttack(combinedWeapon.getRangeDamage(), combinedWeapon.getMagazine(), room, gamer, enemy);
                return true;
            } else {
                return false;
            }
        }
    }

    private static void meleeThrow(Object weapon, Person enemy, Room room) {
        MeleeWeapons meleeWeapon;
        CombinedWeapons combinedWeapon;
        if (weapon instanceof MeleeWeapons) {
            meleeWeapon = (MeleeWeapons) weapon;
            meleeWeapon.throwAttack(meleeWeapon.getDamage(), room, enemy);
        } else {
            combinedWeapon = (CombinedWeapons) weapon;
            combinedWeapon.throwAttack(combinedWeapon.getMeleeDamage(), room, enemy);
        }
    }

    private static boolean rangeReload(Object weapon) {
        RangeWeapons rangeWeapon;
        CombinedWeapons combinedWeapon;
        if (weapon instanceof RangeWeapons) {
            rangeWeapon = (RangeWeapons) weapon;
            if (rangeWeapon.getMagazine()[0] != 0) {
                magazineIsFull();
                return false;
            } else {
                rangeWeapon.reload(rangeWeapon.getMagazine());
                return true;
            }
        } else {
            combinedWeapon = (CombinedWeapons) weapon;
            if (combinedWeapon.getMagazine()[0] != 0) {
                magazineIsFull();
                return false;
            } else {
                combinedWeapon.reload(combinedWeapon.getMagazine());
                return true;
            }
        }
    }
}
