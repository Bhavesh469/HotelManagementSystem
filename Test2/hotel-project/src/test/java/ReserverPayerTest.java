import com.hotel.ReserverPayer;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ReserverPayerTest {

    @Test
    void validCard_success() {
        // Arrange
        ReserverPayer rp = new ReserverPayer("444014185223", "RP01");

        // Act
        String card = rp.getCard();

        // Assert
        assertNotNull(card);
    }

    @Test
    void invalidCard_throwException() {
        // Arrange + Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ReserverPayer("123", "RP01");
        });
    }
}
