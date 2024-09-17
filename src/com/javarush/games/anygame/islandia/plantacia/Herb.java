package com.javarush.games.anygame.islandia.plantacia;

public class Herb extends Plant {

    private String icon = "🌿";

    @Override
    public String getIcon() {
        return this.icon;
    }

    @Override
    public String toString() {
        return this.icon;
    }
}