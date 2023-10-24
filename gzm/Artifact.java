package com.gzm;

public class Artifact {
    private String name;
    private int energyInc;
    private int energyDec;

    public Artifact(String name, int energyInc, int energyDec) {
        this.name = name;
        this.energyInc = energyInc;
        this.energyDec = energyDec;
    }

    public String getName() {
        return name;
    }

    public int getEnergyInc() {
        return energyInc;
    }

    public int getEnergyDec() {
        return energyDec;
    }

}
