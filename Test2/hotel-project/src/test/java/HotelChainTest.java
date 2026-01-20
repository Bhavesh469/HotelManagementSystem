import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        assertEquals("PC", hotel.getName());
    }

  @Test
void chooseHotel_invalidIndex_throwException() {
    // Arrange
    HotelChain chain = new HotelChain();

    // Change IndexOutOfBoundsException to IllegalArgumentException 
    // to match what your HotelChain.java is actually throwing
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
        chain.chooseHotel(99); 
    });

    // Verify the error message
    assertEquals("Invalid hotel selection.", ex.getMessage());
}
}