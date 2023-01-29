package Game.Objects;

public class Person {
    private int currentHealth;
    private Object weapon;
    private final Object defaultWeapon;
    private int level;

    public Person(int health, Object weapon, Object defaultWeapon) {
        this.weapon = weapon;
        this.defaultWeapon = defaultWeapon;
        currentHealth = health;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void nullWeapons() {
        weapon = defaultWeapon;
    }

    public Object getWeapon() {
        return weapon;
    }

    public void setWeapon(Object object) {
        weapon = object;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
