import java.util.ArrayList;
import java.util.Date;

public class CardManager {
    private static class CardBooking {
        String cardNumber;
        Date checkIn;
        Date checkOut;

        CardBooking(String cardNumber, Date checkIn, Date checkOut) {
            this.cardNumber = cardNumber;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
        }
    }

    private ArrayList<CardBooking> bookings = new ArrayList<>();

    public boolean isCardAvailable(String cardNumber, Date checkIn, Date checkOut) {
        for (CardBooking cb : bookings) {
            if (cb.cardNumber.equals(cardNumber)) {
                // Check overlapping dates
                if (!(checkOut.before(cb.checkIn) || checkIn.after(cb.checkOut))) {
                    return false;
                }
            }
        }
        return true;
    }

    public void addBooking(String cardNumber, Date checkIn, Date checkOut) {
        bookings.add(new CardBooking(cardNumber, checkIn, checkOut));
    }
}
