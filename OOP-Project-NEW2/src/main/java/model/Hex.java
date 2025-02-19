package model;

public class Hex {
    public static int playerNumber;
    public static boolean ownerState = false;
    public static Minion minion;
    public static boolean minionState = false;
    public int row;
    public int col;

    public static int totalInRow = 8;
    public static int totalInCol = 8;
    public static int total = totalInRow*totalInCol;

    Hex (int row , int col) {
        this.row = row;
        this.col = col;
        int playerNumber = 0;
        boolean ownerState = false;
    }

    public static boolean isOwner() {
        return ownerState;
    }
    public static String getOwner() {
        return playerNumber + "";
    }

    public static void setOwner(Player player) {
        playerNumber = player.Number;
        ownerState = true;
    }

    public static boolean hasMinion() {return minionState;}
}

