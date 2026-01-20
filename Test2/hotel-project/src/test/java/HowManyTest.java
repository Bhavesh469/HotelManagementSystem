import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.hotel.HowMany;

class HowManyTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10})
    void validRoomCount_success(int count) {
        // Arrange
        HowMany hm = new HowMany(count);

        // Act
        int result = hm.getNumber();

        // Assert
        assertEquals(count, result);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -5})
    void invalidRoomCount_throwException(int count) {
        // Arrange + Act + Assert
       IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class,
                        () -> new HowMany(-3));

        assertEquals("Number of rooms must be greater than 0.", ex.getMessage());
    }
}
