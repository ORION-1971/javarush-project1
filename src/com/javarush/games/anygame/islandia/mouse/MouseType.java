package com.javarush.games.anygame.islandia.mouse;

public class MouseType {

    public static int mouseLive;
    public static int mouseDead;

    public Rats creats(Enum4 type) {

        Rats rats = null;
        switch (type) {
            case MOUSE -> rats = new Mouse();

            default -> {
            }
        }
        return rats;
    }
}
