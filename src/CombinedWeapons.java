public class CombinedWeapons implements MeleeInterface, RangeInterface {
    private final int meleeDamage;
    private final int rangeDamage;
    private final int[] magazine;
    String name;

    public CombinedWeapons(String name, int meleeDamage, int rangeDamage, int magazineCount) {
        this.name = name;
        this.meleeDamage = meleeDamage;
        this.rangeDamage = rangeDamage;
        magazine = new int[magazineCount];
    }

    public String getName() {
        return name;
    }

    public int getMeleeDamage() {
        return meleeDamage;
    }

    public int getRangeDamage() {
        return rangeDamage;
    }

    public int[] getMagazine() {
        return magazine;
    }

}
