import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.hotel.Guest;

import static org.junit.jupiter.api.Assertions.*;

public class GuestTest {

    @ParameterizedTest
    @CsvSource({
        "Ali, Karachi",
        "Sara, Lahore"
    })
    void createGuest_validInput_success(String name, String address) {
        // Arrange
        Guest guest = new Guest(name, address);

        // Act
        guest.create();

        // Assert
        assertEquals(name, guest.getName());
        assertEquals(address, guest.getAddress());
    }

    @ParameterizedTest
    @CsvSource({
        "'', Karachi",
        "Ali, ''"
    })
    void createGuest_invalidInput_throwException(String name, String address) {
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Guest(name, address);
        });
    }
}
