package com.finexp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Expense {
    private int expId;
    private String uid;
    private String name;
    private Category catid;
    private String desc;
    private Date date;
    private float amount;

    public Expense(int expId, String uid, String name, Category catid, String desc, Date date, float amount) {
        this.expId = expId;
        this.uid = uid;
        this.name = name;
        this.catid = catid;
        this.desc = desc;
        this.date = date;
        this.amount = amount;
    }

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCatid() {
        return catid;
    }

    public void setCatid(Category catid) {
        this.catid = catid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String dt_format = formatter.format(this.date);
        return "Expense{" +
                "expId=" + expId +
                ", uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", catid=" + catid +
                ", desc='" + desc + '\'' +
                ", date=" + dt_format +
                ", amount=" + amount +
                '}';
    }
}

