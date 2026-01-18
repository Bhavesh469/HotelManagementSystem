import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.hotel.RoomType;

class RoomTypeTest {

    @ParameterizedTest
    @CsvSource({
        "Single, 100",
        "Deluxe, 200"
    })
    void validRoomType_success(String kind, double cost) {
        // Arrange
        RoomType rt = new RoomType(kind, cost);

        // Act
        String result = rt.getKind();

        // Assert
        assertEquals(kind, result);
    }

    @ParameterizedTest
    @CsvSource({
        "'', 100",
        "Single, -50"
    })
    void invalidRoomType_throwException(String kind, double cost) {
        // Arrange + Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new RoomType(kind, cost);
        });
    }
}
