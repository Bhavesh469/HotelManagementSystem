public class Guest {
    private String name;
    private String address;

    public Guest(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void create() {
        System.out.println("Guest created: " + name);
    }

    public String getName() {
        return name;
    }
}
