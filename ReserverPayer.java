public class ReserverPayer {
    private String creditCardDetails;
    private String id;

    public ReserverPayer(String creditCardDetails, String id) {
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
