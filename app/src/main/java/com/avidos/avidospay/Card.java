package com.avidos.avidospay;

import java.util.ArrayList;

/**
 * Created by Alan on 21/02/2017.
 */

public class Card {

    private String cardNumber;
    private String cvv;
    private String cardHolderName;
    private String expiryDate;
    private String paymentType;

    public Card(String cardNumber, String cvv, String cardHolderName, String expiryDate, String paymentType, boolean isActivated) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.paymentType = paymentType;
        this.isActivated = isActivated;
    }

    private boolean isActivated;

    public Card() {
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    private static int lastContactId = 0;

    public static ArrayList<Card> createPaymentMethods(int numContacts) {
        ArrayList<Card> contacts = new ArrayList<Card>();

        for (int i = 1; i <= numContacts; i++) {
            if(i == 1) {
                contacts.add(new Card(null, null, "person" + ++lastContactId, null, "Efectivo", true));
            }else {
                contacts.add(new Card("5555555555555555", "620", "person" + ++lastContactId, "1205", "Tarjeta de crédito", false));
            }
        }

        return contacts;
    }
}
