package com.javarush.games.anygame.islandia.herbivores.type;

import com.javarush.games.anygame.islandia.herbivores.PlantEating;
import com.javarush.games.anygame.islandia.herbivores.RabbType;
import com.javarush.games.anygame.islandia.moves.AnyGame;

public class Horse extends PlantEating {

    private String icon = "🐎";
    private double live = getWeight();
    private int daysLive = 80 * AnyGame.hungrySpeed;;

    public Horse() {
        super(400, 20, 4, 60);
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
    public void talk() {
        super.talk();
    }

    @Override
    public int deadPluss() {
        return RabbType.horseDead++;
    }

    @Override
    public int livePluss() {
        return RabbType.horseLive++;
    }
}