import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.hotel.Main;

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
    // This string simulates a real user typing into the console
    String input = "1\n" +            // Select Action: 1. Make Reservation
                   "1\n" +            // Select City: 1. Karachi
                   "1\n" +            // Select Hotel: 1. Movenpick
                   "1\n" +            // Choose Room Type: 1. Single
                   "20/02/2026\n" +    // Check-in date
                   "22/02/2026\n" +    // Check-out date
                   "1234567890123\n" + // Card Number (min 13 digits)
                   "Ali\n" +           // Guest Name
                   "Karachi Address\n" +// Guest Address
                   "3\n";              // Select Action: 3. Exit System

    provideInput(input);
    ByteArrayOutputStream output = captureOutput();

    Main.main(new String[]{});

    String consoleOutput = output.toString();
    
    // Verify key milestones are present in the output
    // Line 49: Check for the hotel name instead of city selection
assertTrue(consoleOutput.contains("Hotel: Movenpick"));

// Line 50: Check for the successful status
assertTrue(consoleOutput.contains("Completed Successfully"));

// Line 51: Check for the masked card number
assertTrue(consoleOutput.contains("Card Used: 1234XXXXXXXX"));
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