package com.test.testdata;

import java.util.Date;

/**
 * Created by dgabrove on 05/31/2016.
 */
public class TestData {
    private int amount;
    private double total;
    private String name;
    private String surname;
    private Date dtStart;
    private String other;

    public TestData(int amount, double total, String name, String surname, Date dtStart, String other) {
        this.amount = amount;
        this.total = total;
        this.name = name;
        this.surname = surname;
        this.dtStart = dtStart;
        this.other = other;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDtStart() {
        return dtStart;
    }

    public void setDtStart(Date dtStart) {
        this.dtStart = dtStart;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TestData{");
        sb.append("amount=").append(amount);
        sb.append(", total=").append(total);
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", dtStart=").append(dtStart);
        sb.append(", other='").append(other).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
