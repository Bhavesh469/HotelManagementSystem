import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardManager {

    // Map to store bookings: Key = Card ID, Value = List of Bookings for that card
    private Map<String, List<BookingDates>> bookings = new HashMap<>();

    // Inner class to simply hold start and end dates
    private class BookingDates {
        Date checkIn;
        Date checkOut;

        BookingDates(Date in, Date out) {
            this.checkIn = in;
            this.checkOut = out;
        }
    }

    // Method 1: Add a booking to the database
    public void addBooking(String cardId, Date checkIn, Date checkOut) {
        bookings.putIfAbsent(cardId, new ArrayList<>());
        bookings.get(cardId).add(new BookingDates(checkIn, checkOut));
    }

    // Method 2: Check if the card is available (no overlaps)
    public boolean isCardAvailable(String cardId, Date newCheckIn, Date newCheckOut) {
        if (!bookings.containsKey(cardId)) {
            return true; // No previous bookings, so it's available
        }

        List<BookingDates> existingBookings = bookings.get(cardId);

        for (BookingDates booking : existingBookings) {
            // Check for overlap
            // Overlap logic: (StartA < EndB) and (EndA > StartB)
            if (newCheckIn.before(booking.checkOut) && newCheckOut.after(booking.checkIn)) {
                return false; // Overlap found, card is NOT available
            }
        }
        return true;
    }
}