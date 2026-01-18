import com.hotel.ReserverPayer;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ReserverPayerTest {

    @Test
    void validCard_success() {
        // FIX: Use a 16-digit number to pass the validation check
        String validCard = "1111222233334444"; 
        ReserverPayer rp = new ReserverPayer(validCard, "Ali");
        
        // Assert
        assertEquals(validCard, rp.getCard());
    }

    @Test
    void invalidCard_throwsException() {
        // This test ensures the defensive programming works!
        // It expects an error when the card is too short
        assertThrows(IllegalArgumentException.class, () -> {
            new ReserverPayer("123", "Ali"); // Invalid (too short)
        });
    }
}