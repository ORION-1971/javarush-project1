package com.javarush.games.anygame.islandia.hishniki;

import com.javarush.games.anygame.islandia.hishniki.type.*;

public class PredatorType {

    public static int bearLive;
    public static int bearDead;

    public static int wolfLive;
    public static int wolfDead;

    public static int foxLive;
    public static int foxDead;

    public static int boaLive;
    public static int boaDead;

    public static int eagleLive;
    public static int eagleDead;

    public Predator creats(Enum3 type) {

        Predator predator = null;
        switch (type) {
            case BEAR -> predator = new Bear();
            case WOLF -> predator = new Wolf();
            case FOX -> predator = new Fox();
            case BOA -> predator = new Boa();
            case EAGLE -> predator = new Eagle();

            default -> {
            }
        }
        return predator;
    }
}
