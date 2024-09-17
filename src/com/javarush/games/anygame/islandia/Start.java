package com.javarush.games.anygame.islandia;

import com.javarush.games.anygame.islandia.moves.AnyGame;

public class Start {
    public static void main(String[] args) {
        System.out.println("СТАРТ ИГРЫ");
        AnyGame anyGame = new AnyGame();
        anyGame.initialize();
    }
}
