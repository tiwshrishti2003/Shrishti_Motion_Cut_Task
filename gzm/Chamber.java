package com.gzm;

public class Chamber {
    private String name;
    private String desc;
    private Asura asura;
    private Artifact artifact ;
    private Chamber[] chamber_exit;
    public Chamber(String name, String desc) {
        this.name = name;
        this.desc = desc;
        this.chamber_exit = new Chamber[4]; // front - Left - back - Right
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public void setChamber_exit(Chamber front, Chamber left, Chamber back, Chamber right)
    {
        this.chamber_exit[0] = front;
        this.chamber_exit[1] = left;
        this.chamber_exit[2] = back;
        this.chamber_exit[3] = right;

    }

    public Asura getAsura() {
        return asura;
    }

    public void setAsura(Asura asura) {
        this.asura = asura;
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    public Chamber get_exit(int direction)
    {
        return chamber_exit[direction];
    }
    public static void createExitPoints(Chamber chamber, Chamber front, Chamber left, Chamber back, Chamber right) {
        chamber.setChamber_exit(front, left, back, right);
    }

    @Override
    public String toString() {
        return "Chamber{" +
                "name='" + name + '\'' +
                ", asura=" + asura +
                ", artifact=" + artifact +
                '}';
    }
}
