package com.javarush.games.anygame.islandia.moves;

public abstract class Animals {
    String icon;
    double live;
    String dead;

    private int max;
    private int speed;


    public Animals(int max, int speed) {       //вес, макс количество, скорость, сытность
        this.max = max;
        this.speed = speed;
    }

    public int getMax() {
        return max;
    }

    public int getSpeed() {
        return speed;
    }

    public String getIcon() {
        return this.icon;
    }

    public double getLive() {
        return live;
    }

    public void setLive() {

    }

    public void setLive(double live) {
    }

    public void talk() {
        System.out.println(getIcon() + " умер от голода");
    }

    public int deadPluss() {
        return 0;
    }
}