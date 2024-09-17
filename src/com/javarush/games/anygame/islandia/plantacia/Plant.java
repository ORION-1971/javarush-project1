package com.javarush.games.anygame.islandia.plantacia;

import com.javarush.games.anygame.islandia.moves.Animals;

public class Plant extends Animals {
    private String icon;
    private double live;

    public Plant() {
        super(0, 1);
    }

    public double getLive() {
        return this.live;
    }

    public void setLive() {
        this.live = live;
    }

    public String getIcon() {
        return this.icon;
    }

    @Override
    public String toString() {
        return icon;
    }
}
