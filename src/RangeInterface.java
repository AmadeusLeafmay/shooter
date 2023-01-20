public interface RangeInterface {
    default void rangeAttack(int damage, int[] magazine, Room room, Person gamer, Person enemy) {
        StringBuilder builder = new StringBuilder();
        int fullDamage = 0;
        builder.append("Результат: ");
        for (int i = 0; i < magazine.length; i++) {
            float chance = (float) Math.random();
            if (chance > 0.5) {
                enemy.setCurrentHealth(enemy.getCurrentHealth() - damage * magazine[i]);
                builder.append("|").append(damage * magazine[i]).append("|");
                fullDamage += damage * magazine[i];
            } else if (chance + (0.1 * room.getSize()) > 0.95) {
                gamer.setCurrentHealth(gamer.getCurrentHealth() - damage * magazine[i]);
                builder.append("|Рикошет").append(damage * magazine[i]).append("|");
            } else {
                builder.append("|Промах|");
            }
            magazine[i] = 0;
        }
        System.out.println(builder);
        Menu.attackDamage(fullDamage);
    }

    default void reload(int[] magazine) {
        for (int i = 0; i < magazine.length; i++) {
            int bullet = (int) (1 + Math.random() * 3);
            magazine[i] = bullet;
        }
    }
}
