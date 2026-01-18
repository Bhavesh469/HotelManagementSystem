public class ReserverPayer {
    private String creditCardDetails;
    private String id;

    public ReserverPayer(String creditCardDetails, String id) {
        if (creditCardDetails == null || creditCardDetails.length() < 14) {
            throw new IllegalArgumentException("Invalid card number.");
        }
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty.");
        }

        this.creditCardDetails = creditCardDetails;
        this.id = id;
    }

    public void create() {
        System.out.println("Reserver/Payer created with ID: " + id);
    }

    public String getCard() {
        return creditCardDetails;
    }
}
