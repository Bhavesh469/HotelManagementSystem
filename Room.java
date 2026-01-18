public class Room {
    private int number;
    private RoomType type;
    private boolean occupied = false;

    public Room(int number, RoomType type) {
        this.number = number;
        this.type = type;
    }

    public boolean isAvailable() {
        return !occupied;
    }

    public void createGuest(Guest guest) {
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
