import java.util.ArrayList;


public class GameLoop {
    private static boolean end = false;
    private static ArrayList<Object> weapons = new ArrayList<>();

    public static void start() {
        switch (Menu.startMenu()) {
            case 1 -> startGame();
            case 2 -> end = true;
        }
    }

    private static void startGame() {
        setupWeapons();
        Person gamer = new Person(1000, weapons.get(5), weapons.get(0));
        gamer.setLevel(5);
        while (!end) {
            Menu.frame();
            if (gamer.getCurrentHealth() < 0) {
                Menu.deathMessage();
                end = true;
                break;
            }
            Menu.weaponNameMessage(gamer);
            Room currentRoom = new Room();
            if (currentRoom.getEnemy()) {
                Person enemy = spawnEnemy(gamer.getLevel());
                switch (Menu.enemyInteraction()) {
                    case 1 -> Duel.startDuel(gamer, enemy, currentRoom);
                    case 2 -> {
                        int backDamage = 100 + (int) (Math.random() * 300);
                        gamer.setCurrentHealth(gamer.getCurrentHealth() - backDamage);
                        Menu.backShootMessage(backDamage);
                    }
                }
            }
            switch (Menu.roomInteraction(currentRoom)) {
                case 1, 2, 3:
                    break;
                case 4:
                    Chest.chestItem(gamer, weapons);
                    break;
                case 5:
                    end = true;
            }
        }
    }

    private static void setupWeapons() {
        WeaponsSetup.addWeapon(new MeleeWeapons("Нож", 30));
        WeaponsSetup.addWeapon(new MeleeWeapons("Меч", 60));
        WeaponsSetup.addWeapon(new MeleeWeapons("Булава", 90));
        WeaponsSetup.addWeapon(new MeleeWeapons("Копье", 120));
        WeaponsSetup.addWeapon(new RangeWeapons("Пистолет", 60, 6));
        WeaponsSetup.addWeapon(new RangeWeapons("Дробовик", 120, 4));
        WeaponsSetup.addWeapon(new RangeWeapons("Автомат", 20, 30));
        WeaponsSetup.addWeapon(new RangeWeapons("Пулемет", 10, 100));
        WeaponsSetup.addWeapon(new CombinedWeapons("Солнечный меч", 100, 400, 1));
        WeaponsSetup.addWeapon(new CombinedWeapons("Шипастая винтовка", 60, 100, 10));
        weapons = WeaponsSetup.getWeapons();
    }

    private static Person spawnEnemy(int level) {
        if (Math.random() > 0.5) {
            level++;
        } else {
            level += 2;
        }
        int health = 1000 + 150 * level;
        return new Person(health, weapons.get(level), weapons.get(0));
    }
}
