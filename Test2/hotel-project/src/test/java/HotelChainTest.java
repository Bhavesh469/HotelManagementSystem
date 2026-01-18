import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.hotel.Hotel;
import com.hotel.HotelChain;

class HotelChainTest {

    @Test
    void chooseHotel_validIndex_success() {
        // Arrange
        HotelChain chain = new HotelChain();
        chain.addHotel(new Hotel("PC"));

        // Act
        Hotel hotel = chain.chooseHotel(0);

        // Assert
        assertNotNull(hotel);
    }

    @Test
    void chooseHotel_invalidIndex_throwException() {
        // Arrange
        HotelChain chain = new HotelChain();

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            chain.chooseHotel(5);
        });
    }
}
