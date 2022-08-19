package com.company;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int id;
    private String name;
    private List<AccountRecord> charges = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        int sum = 0;

        for (AccountRecord accountRecord: this.getCharges()) {
            sum += accountRecord.getCharge();
        }

        return sum;
    }

    public List<AccountRecord> getCharges() {
        return charges;
    }

    @Override
    public String toString() {
        String updatedStringMethod = "Id: " + this.getId() + " Name: " + this.getName() +
                " Balance: " + this.getBalance();
        return updatedStringMethod;
    }
}
