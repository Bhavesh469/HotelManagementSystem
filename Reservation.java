import java.util.Date;

public class Reservation {
    private Date reservationDate;
    private Date startDate;
    private Date endDate;
    private int number;

    private Guest guest;
    private Room room;
    private HowMany howMany;

    public Reservation(Guest guest, Room room, HowMany howMany) {
        this.guest = guest;
        this.room = room;
        this.howMany = howMany;
        this.reservationDate = new Date();
    }

    public void create() {
        room.createGuest(guest);
        System.out.println("Reservation created for " + guest.getName());
        System.out.println("Rooms booked: " + howMany.getNumber());
    }
}
