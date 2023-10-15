package com.finexp;

public class UserSummz {
    private String uid;
    private float total;

    public UserSummz(String uid, float total) {
        this.uid = uid;
        this.total = total;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "UserSummz{" +
                "uid='" + uid + '\'' +
                ", total=" + total +
                '}';
    }
}
