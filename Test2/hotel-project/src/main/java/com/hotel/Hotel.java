package com.hotel;

import java.util.ArrayList;

public class Hotel {

    private String name;
    private final ArrayList<Room> rooms = new ArrayList<>();
    private final ArrayList<Reservation> reservations = new ArrayList<>();

    // ---------------- CONSTRUCTOR ----------------

    public Hotel(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Hotel name cannot be empty.");
        }
        this.name = name;
    }

    // ---------------- ROOM METHODS ----------------

    public void addRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null.");
        }
        rooms.add(room);
    }

    public boolean available() {
        for (Room r : rooms) {
            if (r.isAvailable()) return true;
        }
        return false;
    }

    public Room suggestRoomByType(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Room type is required.");
        }

        for (Room r : rooms) {
            if (r.isAvailable() &&
                r.getType().getKind().equalsIgnoreCase(type)) {
                return r;
            }
        }
        return null;
    }

    // ---------------- RESERVATION METHODS (NEW) ----------------

    public void addReservation(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation cannot be null.");
        }
        reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation cannot be null.");
        }
        reservations.remove(reservation);
    }

    public boolean hasReservation(Reservation reservation) {
        if (reservation == null) return false;
        return reservations.contains(reservation);
    }
    public Reservation findReservationByGuest(String guestName) {
    for (Reservation res : reservations) {
        if (res.getGuest().getName().equalsIgnoreCase(guestName)) {
            return res;
        }
    }
    return null;
}

    // ---------------- GETTERS ----------------

    public String getName() {
        return name;
    }
}
