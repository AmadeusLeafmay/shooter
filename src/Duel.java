public class Duel {
    private static boolean turn = true;

    public static void startDuel(Person gamer, Person enemy, Room room) {
        while (gamer.getCurrentHealth() > 0 && enemy.getCurrentHealth() > 0) {
            if (turn) {
                Menu.frame();
                Menu.gamerHealth(gamer);
                Menu.enemyHealth(enemy);
                Menu.gamerTurn();
                gamerTurn(gamer, enemy, room);
            } else {
                Menu.frame();
                Menu.enemyTurn();
                enemyTurn(enemy, gamer, room);
            }
            if (enemy.getCurrentHealth() <= 0) {
                Menu.enemyDeath();
            }
            if (gamer.getCurrentHealth() <= 0) {
                Menu.deathMessage();
            }
        }
        turn = true;
    }

    private static void gamerTurn(Person gamer, Person enemy, Room room) {
        String type = gamer.getWeapon().getClass().getName();
        switch (Menu.duelInteraction(gamer, enemy)) {
            case 1 -> {
                MeleeAttack(type, gamer.getWeapon(), enemy, room);
                turn = false;
            }
            case 2 -> {
                MeleeThrow(type, gamer.getWeapon(), enemy, room);
                turn = false;
            }
            case 3 -> {
                boolean success;
                success = RangeAttack(type, gamer.getWeapon(), gamer, enemy, room);
                if (success) {
                    turn = false;
                } else {
                    Menu.needReload();
                }
            }
            case 4 -> {
                RangeReload(type, gamer.getWeapon());
                turn = RangeReload(type, gamer.getWeapon());
            }
            case 5 -> {
                enemy.setCurrentHealth(0);
                int backDamage = 100 + (int) (Math.random() * 300);
                gamer.setCurrentHealth(gamer.getCurrentHealth() - backDamage);
                Menu.backShootMessage(backDamage);
            }
        }
    }

    private static void enemyTurn(Person gamer, Person enemy, Room room) {
        String type = gamer.getWeapon().getClass().getName();
        boolean success;
        if (type.equals("MeleeWeapons")) {
            MeleeAttack(type, gamer.getWeapon(), enemy, room);
            turn = true;
        } else if (type.equals("RangeWeapons")) {
            success = RangeAttack(type, gamer.getWeapon(), gamer, enemy, room);
            if (!success) {
                RangeReload(type, gamer.getWeapon());
            }
            turn = true;
        } else {
            if (Math.random() > 0.5) {
                MeleeAttack(type, gamer.getWeapon(), enemy, room);
            } else {
                success = RangeAttack(type, gamer.getWeapon(), gamer, enemy, room);
                if (!success) {
                    RangeReload(type, gamer.getWeapon());
                }
            }
            turn = true;
        }
    }

    private static void MeleeAttack(String type, Object weapon, Person enemy, Room room) {
        MeleeWeapons meleeWeapon;
        CombinedWeapons combinedWeapon;
        if (type.equals("MeleeWeapons")) {
            meleeWeapon = (MeleeWeapons) weapon;
            meleeWeapon.meleeAttack(meleeWeapon.getDamage(), room, enemy);
        } else {
            combinedWeapon = (CombinedWeapons) weapon;
            combinedWeapon.meleeAttack(combinedWeapon.getMeleeDamage(), room, enemy);
        }
    }

    private static boolean RangeAttack(String type, Object weapon, Person gamer, Person enemy, Room room) {
        RangeWeapons rangeWeapon;
        CombinedWeapons combinedWeapon;
        if (type.equals("RangeWeapons")) {
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

    private static void MeleeThrow(String type, Object weapon, Person enemy, Room room) {
        MeleeWeapons meleeWeapon;
        CombinedWeapons combinedWeapon;
        if (type.equals("MeleeWeapons")) {
            meleeWeapon = (MeleeWeapons) weapon;
            meleeWeapon.throwAttack(meleeWeapon.getDamage(), room, enemy);
        } else {
            combinedWeapon = (CombinedWeapons) weapon;
            combinedWeapon.throwAttack(combinedWeapon.getMeleeDamage(), room, enemy);
        }
    }

    private static boolean RangeReload(String type, Object weapon) {
        RangeWeapons rangeWeapon;
        CombinedWeapons combinedWeapon;
        if (type.equals("RangeWeapons")) {
            rangeWeapon = (RangeWeapons) weapon;
            if (rangeWeapon.getMagazine()[0] != 0) {
                Menu.magazineIsFull();
                return false;
            } else {
                rangeWeapon.reload(rangeWeapon.getMagazine());
                return true;
            }
        } else {
            combinedWeapon = (CombinedWeapons) weapon;
            if (combinedWeapon.getMagazine()[0] != 0) {
                Menu.magazineIsFull();
                return false;
            } else {
                combinedWeapon.reload(combinedWeapon.getMagazine());
                return true;
            }
        }
    }
}
