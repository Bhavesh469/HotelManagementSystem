import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {
    private Guest guest;
    private Room room;
    private HowMany howMany;
    private Date checkIn;
    private Date checkOut;

    public Reservation(Guest guest, Room room, HowMany howMany, String checkInStr, String checkOutStr) throws ParseException {
        if (guest == null || room == null || howMany == null) {
            throw new IllegalArgumentException("Reservation data is incomplete.");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.checkIn = sdf.parse(checkInStr);
        this.checkOut = sdf.parse(checkOutStr);

        if (!checkOut.after(checkIn)) {
            throw new IllegalArgumentException("Check-out date must be after check-in date.");
        }

        this.guest = guest;
        this.room = room;
        this.howMany = howMany;
    }

    public void create() {
        room.createGuest(guest);
        long diff = checkOut.getTime() - checkIn.getTime();
        long nights = diff / (1000 * 60 * 60 * 24);

        System.out.println("Reservation created for " + guest.getName());
        System.out.println("Rooms booked: " + howMany.getNumber());
        System.out.println("Check-in: " + checkIn);
        System.out.println("Check-out: " + checkOut);
        System.out.println("Total nights: " + nights);
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }
}
