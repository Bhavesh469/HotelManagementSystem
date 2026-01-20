import com.hotel.*;
import java.text.ParseException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ReservationTest {

    @Test
    void createReservation_success() throws ParseException {
        // Arrange
        Guest guest = new Guest("Ali", "Karachi");
        RoomType type = new RoomType("Single", 100);
        Room room = new Room(101, type);
        HowMany hm = new HowMany(1);

        // FIX: Use "/" instead of "-" to match the SimpleDateFormat ("dd/MM/yyyy")
        Reservation res = new Reservation(guest, room, hm, "01/01/2025", "05/01/2025");

        // Act
        res.create();

        // Assert
        assertFalse(room.isAvailable());
    }

    @Test
    void createReservation_nullData_throwException() {
        // Arrange + Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Reservation(null, null, null, null, null);
        });
    }
}   