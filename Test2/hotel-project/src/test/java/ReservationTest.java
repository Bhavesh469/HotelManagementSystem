import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.hotel.*;
import java.text.ParseException; // <--- FIX 1: Import this

class ReservationTest {

    @Test
    // FIX 2: Add "throws ParseException" here so Java knows date parsing might happen
    void createReservation_success() throws ParseException {
        // Arrange
        Guest guest = new Guest("Ali", "Karachi");
        RoomType type = new RoomType("Single", 100);
        Room room = new Room(101, type);
        HowMany hm = new HowMany(1);

        // FIX 3: Ensure you use 5 arguments (Guest, Room, HowMany, DateString, DateString)
        Reservation res = new Reservation(guest, room, hm, "01-01-2025", "05-01-2025");

        // Act
        res.create();

        // Assert
        assertFalse(room.isAvailable());
    }

    @Test
    void createReservation_nullData_throwException() {
        // Arrange + Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // FIX 4: Pass 5 arguments here too (even if they are null)
            new Reservation(null, null, null, null, null);
        });
    }
}