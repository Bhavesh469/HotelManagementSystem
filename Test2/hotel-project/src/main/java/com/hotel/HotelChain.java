package com.hotel;
import java.util.ArrayList;

public class HotelChain {
    private ArrayList<Hotel> hotels = new ArrayList<>();

    public void addHotel(Hotel hotel) {
        if (hotel == null) {
            throw new IllegalArgumentException("Hotel cannot be null.");
        }
        hotels.add(hotel);
    }
    public int getHotelCount() {
    return hotels.size();
}

    public void showHotels() {
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

    public void createReserverPayer(ReserverPayer rp) {
        if (rp == null) {
            throw new IllegalArgumentException("ReserverPayer cannot be null.");
        }
        rp.create();
    }
}
