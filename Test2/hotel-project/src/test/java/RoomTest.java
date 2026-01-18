import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.hotel.Guest;
import com.hotel.Room;
import com.hotel.RoomType;

class RoomTest {

    @Test
    void assignGuest_success() {
        // Arrange
        RoomType type = new RoomType("Single", 100);
        Room room = new Room(101, type);
        Guest guest = new Guest("Ali", "Karachi");

        // Act
        room.createGuest(guest);

        // Assert
        assertFalse(room.isAvailable());
    }

    @Test
    void assignGuest_twice_throwException() {
        // Arrange
        RoomType type = new RoomType("Single", 100);
        Room room = new Room(101, type);
        Guest guest = new Guest("Ali", "Karachi");

        // Act
        room.createGuest(guest);

        // Assert
        assertThrows(IllegalStateException.class, () -> {
            room.createGuest(guest);
        });
    }
}
