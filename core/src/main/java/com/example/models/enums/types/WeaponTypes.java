package com.example.models.enums.types;

//TODO: Texture for weapons.
public enum WeaponTypes {
    REVOLVER(6, 1, 1, 20, "Revolver"),
    SHOTGUN(2, 1, 4, 10, "Shotgun"),
    DUAL_SMGS(24, 2, 1, 8, "Dual Smgs");

    public static final String[] allNames = allWeaponNames();

    public final int magSize;
    public final int reloadTime;
    public final int projectileAmount;
    public final int dmg;
    public final String name;

    public static WeaponTypes getWeaponTypeByName(String name) {
        for (WeaponTypes weaponTypes : values()) {
            if (name.equals(weaponTypes.name)) {
                return weaponTypes;
            }
        }
        return null;
    }

    private static String[] allWeaponNames() {
        String[] arr = new String[values().length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = values()[i].name;
        }
        return arr;
    }

    WeaponTypes(int magSize, int reloadTime, int projectileAmount, int dmg, String name) {
        this.dmg = dmg;
        this.magSize = magSize;
        this.reloadTime = reloadTime;
        this.projectileAmount = projectileAmount;
        this.name = name;
    }
}
