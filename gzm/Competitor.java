package com.gzm;

public class Competitor {
    private String name;
    private int stamina;
    private int hit;

    public Competitor(String name) {
        this.name = name;
        this.stamina = 1000;
        this.hit = 100;
    }

    public String getName() {
        return name;
    }

    public int getStamina() {
        return stamina;
    }

    public int getHit() {
        return hit;
    }

    public void takeHit(int factor) {
        stamina -= factor;
    }
}
