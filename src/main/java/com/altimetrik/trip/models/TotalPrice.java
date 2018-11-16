package com.altimetrik.trip.models;

public class TotalPrice {
    private String amount;
    private String currency;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "TotalPrice [amount=" + amount + ", currency=" + currency + "]";
    }

}
