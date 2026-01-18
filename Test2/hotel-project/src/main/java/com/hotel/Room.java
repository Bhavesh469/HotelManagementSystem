package com.hotel;

public class Room {
    private int number;
    private RoomType type;
    private boolean occupied = false;

    public Room(int number, RoomType type) {
        if (number <= 0) {
            throw new IllegalArgumentException("Room number must be positive.");
        }
        if (type == null) {
            throw new IllegalArgumentException("Room type cannot be null.");
        }

        this.number = number;
        this.type = type;
    }

    public boolean isAvailable() {
        return !occupied;
    }

    public void createGuest(Guest guest) {
        if (occupied) {
            throw new IllegalStateException("Room is already occupied.");
        }
        if (guest == null) {
            throw new IllegalArgumentException("Guest cannot be null.");
        }

        occupied = true;
        System.out.println("Guest " + guest.getName() + " assigned to Room " + number);
    }

    public int getNumber() {
        return number;
    }

    public RoomType getType() {
        return type;
    }
   
}
