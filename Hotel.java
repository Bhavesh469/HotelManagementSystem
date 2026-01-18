import java.util.ArrayList;

public class Hotel {
    private String name;
    private ArrayList<Room> rooms = new ArrayList<>();

    public Hotel(String name) {
        this.name = name;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }
    public Room suggestRoomByType(String type) {
    for (Room r : rooms) {
        if (r.isAvailable() && r.getType().getKind().equalsIgnoreCase(type)) {
            return r;
        }
    }
    return null;
}


    public boolean available() {
        for (Room r : rooms) {
            if (r.isAvailable()) return true;
        }
        return false;
    }

    public Room suggestRoom() {
        for (Room r : rooms) {
            if (r.isAvailable()) return r;
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
