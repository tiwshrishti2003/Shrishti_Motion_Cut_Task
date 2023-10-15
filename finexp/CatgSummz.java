package com.finexp;

public class CatgSummz {
    private Category catid;
    private float total;

    public CatgSummz(Category catid, float total) {
        this.catid = catid;
        this.total = total;
    }

    public Category getCatid() {
        return catid;
    }

    public void setCatid(Category catid) {
        this.catid = catid;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CatgSummz{" +
                "catid=" + catid +
                ", total=" + total +
                '}';
    }
}
