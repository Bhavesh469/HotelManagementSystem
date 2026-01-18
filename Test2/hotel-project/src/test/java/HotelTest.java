import com.hotel.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
//import src.Hotel;
//import src.Room;
//import src.RoomType;

class HotelTest {

    @Test
    void suggestRoomByType_success() {
        // Arrange
        Hotel hotel = new Hotel("Serena");
        RoomType single = new RoomType("Single", 100);
        hotel.addRoom(new Room(101, single));

        // Act
        Room room = hotel.suggestRoomByType("Single");

        // Assert
        assertNotNull(room);
    }

    @Test
    void suggestRoomByType_notAvailable_returnNull() {
        // Arrange
        Hotel hotel = new Hotel("Serena");

        // Act
        Room room = hotel.suggestRoomByType("Single");

        // Assert
        assertNull(room);
    }
}
