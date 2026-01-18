import static org.junit.jupiter.api.Assertions.*;
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
        assertThrows(IllegalArgumentException.class, () -> {
            new HowMany(count);
        });
    }
}
