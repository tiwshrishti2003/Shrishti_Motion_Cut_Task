package com.gzm;

public class Asura {
    private String name;
    private int stamina;
    private int hit;

    public Asura(String name) {
        this.name = name;
        this.stamina = 500;
        this.hit = 50;
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
