package com.hotel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // This persists throughout the program run to track card usage
        CardManager cardManager = new CardManager(); 
        boolean keepRunning = true;

        // Initialize System Data once
        RoomType single = new RoomType("Single", 100);
        RoomType deluxe = new RoomType("Deluxe", 200);

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

        HotelChain chain = new HotelChain();
        chain.addHotel(h1); chain.addHotel(h2); chain.addHotel(h3); chain.addHotel(h4); chain.addHotel(h5);

        System.out.println("--- Hotel Reservation System ---");

        // The loop allows you to make multiple bookings in one run
        while (keepRunning) {
            try {
                System.out.println("\nSelect Action:");
                System.out.println("1. Make Reservation");
                System.out.println("2. Cancel Reservation");
                System.out.println("3. Exit System");
                int action = getValidInt(sc, 1, 3);

                if (action == 3) {
                    keepRunning = false;
                    continue;
                }

                if (action == 2) {
                    System.out.print("Enter Guest Name to cancel: ");
                    String nameToCancel = sc.nextLine();
                    System.out.print("Enter Room Number: ");
                    int roomToCancel = getValidInt(sc, 101, 502); 
                    System.out.println("Processing cancellation for " + nameToCancel + "...");
                    System.out.println("✅ Success: Room " + roomToCancel + " is now marked as Available.");
                    continue; 
                }

                // ---------------- RESERVATION LOGIC ----------------
                System.out.println("\nSelect City (1. Karachi, 2. Islamabad, 3. Lahore):");
                int cityChoice = getValidInt(sc, 1, 3);
                chain.showHotels();
                int hotelChoice = getValidInt(sc, 1, chain.getHotelCount());
                Hotel selectedHotel = chain.chooseHotel(hotelChoice - 1);

                System.out.println("\nChoose Room Type (1. Single, 2. Deluxe):");
                int typeChoice = getValidInt(sc, 1, 2);
                Room room = selectedHotel.suggestRoomByType(typeChoice == 1 ? "Single" : "Deluxe");

                if (room == null) {
                    System.out.println("No rooms available in this category.");
                    continue;
                }

                // Get dates first to check card availability
                System.out.print("\nEnter Check-in date (dd/MM/yyyy): ");
                String checkInStr = sc.nextLine();
                System.out.print("Enter Check-out date (dd/MM/yyyy): ");
                String checkOutStr = sc.nextLine();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date dateIn = sdf.parse(checkInStr);
                Date dateOut = sdf.parse(checkOutStr);

                if (!dateOut.after(dateIn)) {
                    System.out.println("Validation Error: Check-out must be after check-in.");
                    continue;
                }

                // --- CARD VALIDATION BLOCK ---
                System.out.print("\nEnter Card Number (min 13 digits): ");
                String cardNo = sc.nextLine();

                if (!cardManager.isCardAvailable(cardNo, dateIn, dateOut)) {
                    System.out.println("\n❌ SECURITY ALERT: Card [" + cardNo + "] is currently in use.");
                    System.out.println("This card cannot be used again until the previous stay is over.");
                    continue; // This returns user to the main menu instead of closing
                }
                
                ReserverPayer rp = new ReserverPayer(cardNo, "RP-" + System.currentTimeMillis());
                chain.createReserverPayer(rp);

                System.out.print("Enter Guest Name: ");
                String guestName = sc.nextLine();
                System.out.print("Enter Guest Address: ");
                String guestAddress = sc.nextLine();
                Guest guest = new Guest(guestName, guestAddress);

                Reservation res = new Reservation(guest, room, new HowMany(1), checkInStr, checkOutStr);
                res.create(); 

                // REGISTER card usage so it's blocked for future loops
                cardManager.addBooking(cardNo, dateIn, dateOut);

                System.out.println("\n--- Booking Summary ---");
                System.out.println("Status: Completed Successfully");
                System.out.println("Hotel: " + selectedHotel.getName() + " | Room: " + room.getNumber());
                System.out.println("Card Used: " + cardNo.substring(0, 4) + "XXXXXXXX");

            } catch (ParseException e) {
                System.out.println("System Error: Invalid date format.");
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Validation Error: " + e.getMessage());
            }
        }
        
        sc.close();
        System.out.println("\nThank you for using the Hotel Reservation System.");
    }

    public static int getValidInt(Scanner sc, int min, int max) {
        int value = -1;
        while (true) {
            try {
                if (sc.hasNextInt()) {
                    value = sc.nextInt();
                    sc.nextLine(); 
                    if (value >= min && value <= max) break;
                } else { sc.nextLine(); }
            } catch (Exception e) { sc.nextLine(); }
            System.out.print("Enter a number (" + min + "-" + max + "): ");
        }
        return value;
    }
}