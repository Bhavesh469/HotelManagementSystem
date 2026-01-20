import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;   // IMPORTANT
import org.junit.jupiter.api.Test;

import com.hotel.Guest;

public class GuestTest {

    @Test
    void testValidGuest() {
        Guest g = new Guest("Ali", "ali@email.com");
        assertNotNull(g);
    }

    @Test
    void testInvalidGuest() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Guest("", "");
        });

      assertEquals("Guest name cannot be empty.", ex.getMessage());
    }
}
