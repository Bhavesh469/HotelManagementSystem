package com.hotel;
public class Guest {
    private String name;
    private String address;

    public Guest(String name, String address) {
        // This validation logic allows your second test (invalidInput) to pass
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Guest name cannot be empty.");
        }
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Guest address cannot be empty.");
        }

        this.name = name;
        this.address = address;
    }

    public void create() {
        System.out.println("Guest created: " + name);
    }

    public String getName() {
        return name;
    }

    // --- THIS WAS MISSING ---
    public String getAddress() {
        return address;
    }
}