package com.innova.ecommercestore.models;

public class Card {
    private String cardnumber;
    private String year;
    private String month;

    public Card(String cardnumber, String year, String month) {
        this.cardnumber = cardnumber;
        this.year = year;
        this.month = month;
    }

    public Card() {
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
