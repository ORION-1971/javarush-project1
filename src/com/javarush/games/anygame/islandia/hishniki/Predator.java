package com.javarush.games.anygame.islandia.hishniki;

import com.javarush.games.anygame.islandia.moves.Animals;

public class Predator extends Animals {
    private String icon;
    private double weight;
    private double weightEat;

    public Predator(double weight, int max, int speed, double weightEat) {       //вес, макс количество, скорость, сытность
        super(max, speed);
        this.weight = weight;
        this.weightEat = weightEat;
    }

    public double getWeight() {
        return weight;
    }

    public double getWeightEat() {
        return weightEat;
    }

    public String getIcon() {
        return icon;
    }

    public String toString() {
        return icon;
    }

    public int livePluss() {
        return 0;
    }
}







