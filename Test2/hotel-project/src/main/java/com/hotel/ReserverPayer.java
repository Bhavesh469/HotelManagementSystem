package com.hotel;

public class ReserverPayer {

    private final String creditCardDetails;
    private final String id;

    // ---------------- CONSTRUCTOR ----------------

    public ReserverPayer(String creditCardDetails, String id) {
        if (creditCardDetails == null || creditCardDetails.trim().length() < 13) {
            throw new IllegalArgumentException("Invalid card number. Minimum 13 digits required.");
        }

        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty.");
        }

        this.creditCardDetails = creditCardDetails;
        this.id = id;
    }

    // ---------------- CREATE ----------------

    public void create() {
        System.out.println("Reserver/Payer created successfully.");
        System.out.println("ID: " + id);
        System.out.println("Card: " + maskCard());
    }

    // ---------------- GETTERS ----------------

    public String getCard() {
        return creditCardDetails;
    }

    public String getId() {
        return id;
    }

    // ---------------- EXTRA DEFENSIVE METHOD ----------------

    private String maskCard() {
        if (creditCardDetails.length() <= 4) {
            return creditCardDetails;
        }
        return "**** **** **** " + creditCardDetails.substring(creditCardDetails.length() - 4);
    }
}
