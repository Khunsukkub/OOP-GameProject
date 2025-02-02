package Eiei;

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

    public String hexState() { //เช็คเจ้าของที่
        if (owner != null) {
            return "noOwner"
        } else {
            return "hasOwner"
        }
    }
}
