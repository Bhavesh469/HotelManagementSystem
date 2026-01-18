import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Room Types
        RoomType single = new RoomType("Single", 100);
        RoomType deluxe = new RoomType("Deluxe", 200);

        // Ask City
        System.out.println("Select City:");
        System.out.println("1. Karachi");
        System.out.println("2. Islamabad");
        System.out.println("3. Lahore");
        int cityChoice = sc.nextInt();
        sc.nextLine();  // clear buffer

        // Hotels (Karachi Example)
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

        // Hotel Chain
        HotelChain chain = new HotelChain();
        chain.addHotel(h1);
        chain.addHotel(h2);
        chain.addHotel(h3);
        chain.addHotel(h4);
        chain.addHotel(h5);

        // Reserver / Payer
        System.out.print("Enter Card Number: ");
        String card = sc.nextLine();
        ReserverPayer rp = new ReserverPayer(card, "RP001");
        chain.createReserverPayer(rp);

        // Choose Hotel
        System.out.println("\nChoose Hotel:");
        chain.showHotels();
        int choice = sc.nextInt();
        sc.nextLine();

        Hotel selectedHotel = chain.chooseHotel(choice - 1);

        // Choose Room Type
        System.out.println("\nChoose Room Type:");
        System.out.println("1. Single");
        System.out.println("2. Deluxe");
        int typeChoice = sc.nextInt();

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

        // Guest Info
        sc.nextLine();
        System.out.print("Guest Name: ");
        String name = sc.nextLine();

        System.out.print("Guest Address: ");
        String address = sc.nextLine();

        Guest guest = new Guest(name, address);
        guest.create();

        // HowMany
        System.out.print("How many rooms? ");
        int count = sc.nextInt();
        HowMany howMany = new HowMany(count);

        // Reservation
        Reservation res = new Reservation(guest, room, howMany);
        res.create();

        System.out.println("\nPayment done using card: " + rp.getCard());
        System.out.println("Booking Completed Successfully!");
    }
}
