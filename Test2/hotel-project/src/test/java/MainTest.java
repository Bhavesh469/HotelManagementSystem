import com.hotel.Main;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MainTest {

    // Helper method to simulate User Input
    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    // Helper method to capture System.out output
    private ByteArrayOutputStream captureOutput() {
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        return testOut;
    }

    @Test
    void testMain_HappyPath_SuccessfulBooking() {
        // 1. Simulate the exact keystrokes a user would make:
        // City(1) -> Extra Enter -> Card -> Hotel(1) -> RoomType(1) -> Name -> Address -> Count(1) -> DateIn -> DateOut
        // Note: Your Main.java has an extra sc.nextLine() after city, so we add an extra \n in the input string.
        String input = "1\n" +                  // Select Karachi
                       "\n" +                   // Consumed by the extra sc.nextLine() in your Main.java
                       "1234567890123456\n" +   // Card Number
                       "1\n" +                  // Select Hotel 1 (Movenpick)
                       "1\n" +                  // Select Room Type (Single)
                       "Ali\n" +                // Guest Name
                       "Clifton, Karachi\n" +   // Address
                       "1\n" +                  // How many rooms
                       "10/01/2026\n" +         // Check-in
                       "15/01/2026\n";          // Check-out

        provideInput(input);
        ByteArrayOutputStream output = captureOutput();

        // 2. Run the Main method
        Main.main(new String[]{});

        // 3. Verify the output contains success messages
        String consoleOutput = output.toString();
        
        // Check key milestones
        assertTrue(consoleOutput.contains("City selected: Karachi"), "Should select Karachi");
        assertTrue(consoleOutput.contains("Suggested Room"), "Should find a room");
        assertTrue(consoleOutput.contains("Payment done"), "Should process payment");
        assertTrue(consoleOutput.contains("Booking Completed Successfully"), "Final success message missing");
    }

    @Test
    void testGetValidInt_ValidInput() {
        // Arrange
        String input = "5\n"; // User types 5
        Scanner sc = new Scanner(input);

        // Act
        int result = Main.getValidInt(sc, 1, 10);

        // Assert
        assertEquals(5, result);
    }

    @Test
    void testGetValidInt_InvalidThenValidInput() {
        // Arrange: User types "abc" (invalid), then "100" (out of range), then "2" (valid)
        String input = "abc\n100\n2\n";
        Scanner sc = new Scanner(input);

        // Act
        // This will print error messages to console, but eventually return 2
        int result = Main.getValidInt(sc, 1, 5);

        // Assert
        assertEquals(2, result);
    }
}