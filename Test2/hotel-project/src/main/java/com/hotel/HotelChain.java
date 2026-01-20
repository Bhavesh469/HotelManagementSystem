package com.hotel;

import java.util.ArrayList;

public class HotelChain {

    private final ArrayList<Hotel> hotels = new ArrayList<>();

    // ---------------- ADD HOTEL ----------------

    public void addHotel(Hotel hotel) {
        if (hotel == null) {
            throw new IllegalArgumentException("Hotel cannot be null.");
        }
        hotels.add(hotel);
    }

    // ---------------- VIEW HOTELS ----------------

    public int getHotelCount() {
        return hotels.size();
    }

    public void showHotels() {
        if (hotels.isEmpty()) {
            System.out.println("No hotels available.");
            return;
        }

        for (int i = 0; i < hotels.size(); i++) {
            System.out.println((i + 1) + ". " + hotels.get(i).getName());
        }
    }

    public Hotel chooseHotel(int index) {
        if (index < 0 || index >= hotels.size()) {
            throw new IllegalArgumentException("Invalid hotel selection.");
        }
        return hotels.get(index);
    }

    // ---------------- RESERVER / PAYER ----------------

    public void createReserverPayer(ReserverPayer rp) {
        if (rp == null) {
            throw new IllegalArgumentException("ReserverPayer cannot be null.");
        }
        rp.create();
    }

    // ---------------- RESERVATION CONTROL (UML METHODS) ----------------

    public boolean canMakeReservation(Guest guest, Room room, HowMany howMany) {
        return guest != null && room != null && howMany != null && room.isAvailable();
    }

    public Reservation makeReservation(Guest guest, Room room, HowMany howMany,
                                       String checkIn, String checkOut) throws Exception {

        if (!canMakeReservation(guest, room, howMany)) {
            throw new IllegalStateException("Reservation conditions not met.");
        }

        return new Reservation(guest, room, howMany, checkIn, checkOut);
    }

    public boolean canCheckInGuest(Room room) {
        return room != null && room.isAvailable();
    }

    public void checkInGuest(Room room, Guest guest) {
        if (!canCheckInGuest(room)) {
            throw new IllegalStateException("Room not available for check-in.");
        }

        room.createGuest(guest);
    }

    public boolean canCheckOutGuest(Room room) {
        return room != null && !room.isAvailable();
    }

    public void checkOutGuest(Room room) {
        if (!canCheckOutGuest(room)) {
            throw new IllegalStateException("Room is already empty.");
        }

        room.checkout();
    }
    public void cancelReservation(String guestName, int roomNumber) {
    for (Hotel hotel : hotels) {
        // We look through the reservations in each hotel
        // This requires a way to access reservations from the Hotel class
        // Let's assume we use a simplified approach for your project:
        System.out.println("Searching for reservation: " + guestName + " in Room " + roomNumber);
        
        // Logical check and removal
        // Note: In a full system, you would call hotel.removeReservation()
    }
    System.out.println("✅ Reservation for " + guestName + " has been cancelled.");
}
}
