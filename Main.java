import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CardManager cardManager = new CardManager(); // to track card usage

        try {
            // 1️⃣ Room Types
            RoomType single = new RoomType("Single", 100);
            RoomType deluxe = new RoomType("Deluxe", 200);

            // 2️⃣ Ask City safely
            System.out.println("Select City:");
            System.out.println("1. Karachi");
            System.out.println("2. Islamabad");
            System.out.println("3. Lahore");

            int cityChoice = getValidInt(sc, 1, 3);
            sc.nextLine(); // clear buffer

            String cityName = cityChoice == 1 ? "Karachi" : cityChoice == 2 ? "Islamabad" : "Lahore";
            System.out.println("City selected: " + cityName);

            // 3️⃣ Hotels
            Hotel h1 = new Hotel("Movenpick");
            h1.addRoom(new Room(101, single));
            h1.addRoom(new Room(102, deluxe));

            Hotel h2 = new Hotel("Serena");
            h2.addRoom(new Room(201, single));
            h2.addRoom(new Room(202, deluxe));

            Hotel h3 = new Hotel("Beach Luxury");
            h3.addRoom(new Room(301, single));
            h3.addRoom(new Room(302, deluxe));

            Hotel h4 = new Hotel("Avari Towers");
            h4.addRoom(new Room(401, single));
            h4.addRoom(new Room(402, deluxe));

            Hotel h5 = new Hotel("Pearl Continental");
            h5.addRoom(new Room(501, single));
            h5.addRoom(new Room(502, deluxe));

            // 4️⃣ Hotel Chain
            HotelChain chain = new HotelChain();
            chain.addHotel(h1);
            chain.addHotel(h2);
            chain.addHotel(h3);
            chain.addHotel(h4);
            chain.addHotel(h5);

            // 5️⃣ Reserver / Payer
            System.out.print("Enter Card Number (min 14 digits): ");
            String card = sc.nextLine();
            ReserverPayer rp = new ReserverPayer(card, "RP001");
            chain.createReserverPayer(rp);

            // 6️⃣ Choose Hotel
            System.out.println("\nChoose Hotel:");
            chain.showHotels();
            int hotelChoice = getValidInt(sc, 1, chain.getHotelCount());
            Hotel selectedHotel = chain.chooseHotel(hotelChoice - 1);

            // 7️⃣ Choose Room Type
            System.out.println("\nChoose Room Type:");
            System.out.println("1. Single");
            System.out.println("2. Deluxe");
            int typeChoice = getValidInt(sc, 1, 2);
            String selectedType = (typeChoice == 1) ? "Single" : "Deluxe";

            // Suggest Room Based on Type
            Room room = selectedHotel.suggestRoomByType(selectedType);
            if (room == null) {
                System.out.println("No " + selectedType + " rooms available.");
                return;
            }

            System.out.println("\nSuggested Room:");
            System.out.println("Room No: " + room.getNumber());
            System.out.println("Type: " + room.getType().getKind());
            System.out.println("Price: $" + room.getType().getCost());

            // 8️⃣ Guest Info
            System.out.print("\nGuest Name: ");
            String name = sc.nextLine();
            System.out.print("Guest Address: ");
            String address = sc.nextLine();
            Guest guest = new Guest(name, address);
            guest.create();

            // 9️⃣ How Many Rooms
            System.out.print("How many rooms? ");
            int count = getValidInt(sc, 1, 5); // max 5 rooms for example
            HowMany howMany = new HowMany(count);

            // 🔹 Check-in / Check-out Dates
            System.out.print("Enter Check-in date (dd/MM/yyyy): ");
            String checkInStr = sc.nextLine();
            System.out.print("Enter Check-out date (dd/MM/yyyy): ");
            String checkOutStr = sc.nextLine();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date checkIn = sdf.parse(checkInStr);
            Date checkOut = sdf.parse(checkOutStr);

            if (!checkOut.after(checkIn)) {
                System.out.println("Check-out date must be after check-in date.");
                return;
            }

            // 🔹 Card availability check
            if (!cardManager.isCardAvailable(card, checkIn, checkOut)) {
                System.out.println("This card is already used for this period. Choose another card or dates.");
                return;
            }

            // 10️⃣ Create reservation
            Reservation res = new Reservation(guest, room, howMany, checkInStr, checkOutStr);
            res.create();

            // 11️⃣ Register card usage
            cardManager.addBooking(card, checkIn, checkOut);

            // 12️⃣ Payment confirmation
            System.out.println("\nPayment done using card: " + rp.getCard());
            System.out.println("Booking Completed Successfully!");

            // 13️⃣ Optional Offer
            long nights = (checkOut.getTime() - checkIn.getTime()) / (1000 * 60 * 60 * 24);
            if (nights >= 2) {
                System.out.println("Special Offer: Stay 2+ nights, get 10% discount!");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    // ✅ Defensive method to read integers
    public static int getValidInt(Scanner sc, int min, int max) {
        int value = -1;
        while (true) {
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value >= min && value <= max) break;
            } else {
                sc.next(); // discard invalid input
            }
            System.out.print("Enter a valid number (" + min + " - " + max + "): ");
        }
        sc.nextLine(); // clear buffer
        return value;
    }
}
