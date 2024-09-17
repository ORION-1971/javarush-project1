package com.javarush.games.anygame.islandia.mouse;

import com.javarush.games.anygame.islandia.moves.AnyGame;

public class Mouse extends Rats {

    private String icon = "🐭";
    private double live = getWeight();
    private int daysLive = 1  * AnyGame.hungrySpeed;;           // дней жизни

    public Mouse() {
        super(0.05, 200, 1, 0);
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
        return MouseType.mouseDead++;
    }

    @Override
    public int livePluss() {
        return MouseType.mouseLive++;
    }
}
