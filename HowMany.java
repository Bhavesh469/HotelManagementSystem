public class HowMany {
    private int number;

    public HowMany(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Number of rooms must be greater than 0.");
        }
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
