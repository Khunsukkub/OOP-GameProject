public class Hex {
    public int x, y; // Coordinates for distance calculation
    public Player owner;
    public int cost;
    public double interest;
    private String color;

    public Hex(int x, int y, Player owner) {
        this.x = x;
        this.y = y;
        this.owner = null;
        this.cost = 1000;
        this.interest = 50;
        this.color = "#FFFFFF";
    }

    public void hexState() {
        if (owner != null) {
            System.out.println("Hex is owned by " + owner.name);
        } else {
            System.out.println("Hex is unowned.");
        }
    }
}
