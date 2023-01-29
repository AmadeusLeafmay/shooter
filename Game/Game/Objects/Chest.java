package Game.Objects;

import Game.Logic.Menu;

import java.util.ArrayList;

public class Chest {
    public static void chestItem(Person gamer, ArrayList<Object> weapons) {
        float chance = (float) Math.random();
        int weapon = 0;
        float iteration = 0;
        for(int i = 0; i <= 9; i++){
            if(iteration <= chance && chance <= iteration + 0.15){
                weapon = i;
                break;
            } else {
                weapon = 0;
            }
            iteration += 0.15;
        }
        chestChoice(weapon, gamer, weapons);
    }

    private static void chestChoice(int weapon, Person gamer, ArrayList<Object> weapons) {
        String name = Menu.getWeaponName(weapons.get(weapon));
        if(Menu.chestInteraction(name) == 1){
            gamer.nullWeapons();
            gamer.setWeapon(weapons.get(weapon));
            if (gamer.getLevel() < weapon) {
                gamer.setLevel(weapon);
            }
        }
    }
}

