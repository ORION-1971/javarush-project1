package com.javarush.games.anygame.islandia.hishniki.type;

import com.javarush.games.anygame.islandia.hishniki.Predator;
import com.javarush.games.anygame.islandia.hishniki.PredatorType;
import com.javarush.games.anygame.islandia.moves.AnyGame;

public class Boa extends Predator {

    private String icon = "🐍";
    private double live = getWeight();
    private int daysLive = 20 * AnyGame.hungrySpeed;

    public Boa() {
        super(15, 30, 1, 2);
    }

    @Override
    public double getLive() {
        if (this.live <= 0) return 10200;            // двухсотый
        return this.live;
    }

    @Override
    public void setLive() {
        this.live = this.live - getWeight() / daysLive;
    }

    @Override
    public void setLive(double live) {
        this.live = this.live + live;
    }

    @Override
    public String getIcon() {
        return this.icon;
    }

    @Override
    public String toString() {
        return this.icon;
    }

    @Override
    public int deadPluss() {
        return PredatorType.boaDead++;
    }

    @Override
    public int livePluss() {
        return PredatorType.boaLive++;
    }
}
