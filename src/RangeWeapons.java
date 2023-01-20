public class RangeWeapons implements RangeInterface {
    private final int damage;
    private final int[] magazine;
    String name;

    public RangeWeapons(String name, int damage, int magazineCount) {
        this.name = name;
        this.damage = damage;
        magazine = new int[magazineCount];
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int[] getMagazine() {
        return magazine;
    }
}
