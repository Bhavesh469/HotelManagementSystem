package com.hotel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {

    private final Guest guest;
    private final Room room;
    private final Date checkInDate;
    private final Date checkOutDate;

    private boolean checkedIn = false;
    private boolean checkedOut = false;

    // ---------------- CONSTRUCTOR ----------------

    public Reservation(Guest guest, Room room, HowMany howMany,
                       String checkInStr, String checkOutStr) throws ParseException {

        if (guest == null || room == null || howMany == null) {
            throw new IllegalArgumentException("Reservation data is incomplete.");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date in = sdf.parse(checkInStr);
        Date out = sdf.parse(checkOutStr);

        if (!out.after(in)) {
            throw new IllegalArgumentException("Check-out must be after check-in.");
        }

        this.guest = guest;
        this.room = room;
        this.checkInDate = in;
        this.checkOutDate = out;
    }

    // ---------------- RESERVATION ACTIONS ----------------

    public void create() {
        room.createGuest(guest);
        System.out.println("Reservation created for " + guest.getName());
    }

    public void checkIn() {
        if (checkedIn) {
            throw new IllegalStateException("Guest already checked in.");
        }
        checkedIn = true;
        System.out.println(guest.getName() + " checked in.");
    }

    public void checkOut() {
        if (!checkedIn) {
            throw new IllegalStateException("Guest not checked in yet.");
        }
        if (checkedOut) {
            throw new IllegalStateException("Guest already checked out.");
        }

        checkedOut = true;
        room.checkout();
        System.out.println(guest.getName() + " checked out.");
    }
    public void cancel() {
    if (checkedIn) {
        throw new IllegalStateException("Cannot cancel a reservation after check-in.");
    }
    room.checkout(); // This sets occupied = false in the Room class
    System.out.println("Reservation for " + guest.getName() + " has been cancelled.");
}

    // ---------------- STATUS METHODS ----------------

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    // ---------------- GETTERS ----------------

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public Date getCheckIn() {
        return checkInDate;
    }

    public Date getCheckOut() {
        return checkOutDate;
    }
}
