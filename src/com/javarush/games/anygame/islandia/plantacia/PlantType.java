package com.javarush.games.anygame.islandia.plantacia;

public class PlantType {
    public Plant creats(Enum1 type) {

        Plant plant = null;
        switch (type) {
            case HERB -> plant = new Herb();
            case KOKO -> plant = new Koko();

            default -> {
            }
        }
        return plant;
    }
}
