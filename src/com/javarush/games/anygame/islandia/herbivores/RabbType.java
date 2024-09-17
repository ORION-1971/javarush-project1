package com.javarush.games.anygame.islandia.herbivores;

import com.javarush.games.anygame.islandia.herbivores.type.*;

public class RabbType {

    //---------------------- КНИГА МЕРТВЫХ КРОЛИКОВ -------------------------
    public static int rabbitLive;                   // живых кролов
    public static int rabbitDead;                   // мертвых кролов

    public static int goatLive;
    public static int goatDead;

    public static int buffaloLive;
    public static int buffaloDead;

    public static int sheepLive;
    public static int sheepDead;

    public static int horseLive;
    public static int horseDead;

    public static int deerLive;
    public static int deerDead;

    public static int boarLive;
    public static int boarDead;

    public static int duckLive;
    public static int duckDead;

    public static int chickenLive;
    public static int chickenDead;


    //---------------------- СВИДЕТЕЛЬСТВА О РОЖДЕНИИ ----------------------------

    public PlantEating creats(Enum2 type) {
        PlantEating plantEating = null;
        switch (type) {
            case BUFFALO -> plantEating = new Buffalo();
            case RABBIT -> plantEating = new Rabbit();
            case GOAT -> plantEating = new Goat();
            case SHEEP -> plantEating = new Sheep();
            case HORSE -> plantEating = new Horse();
            case DEER -> plantEating = new Deer();
            case BOAR -> plantEating = new Boar();

            case DUCK -> plantEating = new Duck();
            case CHICKEN -> plantEating = new Chicken();

            default -> {
            }
        }
        return plantEating;
    }
}
