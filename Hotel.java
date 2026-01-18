import java.util.ArrayList;

public class Hotel {
    private String name;
    private ArrayList<Room> rooms = new ArrayList<>();

    public Hotel(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Hotel name cannot be empty.");
        }
        this.name = name;
    }

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
            if (r.isAvailable() && r.getType().getKind().equalsIgnoreCase(type)) {
                return r;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
