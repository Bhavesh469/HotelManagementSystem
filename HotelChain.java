import java.util.ArrayList;

public class HotelChain {
    private ArrayList<Hotel> hotels = new ArrayList<>();

    public void createReserverPayer(ReserverPayer rp) {
        rp.create();
    }

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public void showHotels() {
        for (int i = 0; i < hotels.size(); i++) {
            System.out.println((i + 1) + ". " + hotels.get(i).getName());
        }
    }

    public Hotel chooseHotel(int index) {
        return hotels.get(index);
    }
}
