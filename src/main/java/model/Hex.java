package model;

public class Hex {
    public int playerNumber;
    public boolean ownerState = false;
    public Minion minion;
    public boolean minionState = false;
    public int row;
    public int col;
    private double hex_price = 1000;

    public static int totalInRow = 8;
    public static int totalInCol = 8;
    public static int total = totalInRow*totalInCol;

    Hex (int row , int col) {
        this.row = row;
        this.col = col;
        int playerNumber = 0;
        boolean ownerState = false;
    }

    public boolean isOwner() {
        return ownerState;
    }
    public int getOwner() {
        return playerNumber;
    }

    public void setOwner(Player player) {
        playerNumber = player.Number;
        ownerState = true;
    }

    public void setMinion(Minion minion) {
        minionState = true;
        this.minion = minion;
    }



    public boolean hasMinion() {return minionState;}
    public double getHexPrice() {return hex_price;}
}
