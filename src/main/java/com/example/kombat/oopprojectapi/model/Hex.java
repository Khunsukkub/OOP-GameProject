package com.example.kombat.oopprojectapi.model;

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

    public Hex(int row, int col) {
        this.row = row;
        this.col = col;
        int playerNumber = 0;
        boolean ownerState = false;
    }

    public String hexStatus() {
        StringBuilder status = new StringBuilder();

        status.append("Position: (").append(row).append(", ").append(col).append("), ");
        status.append("Price: ").append(hex_price).append(", ");
        status.append("Player Number: ").append(playerNumber).append(", ");
        status.append("Owner State: ").append(ownerState).append(", ");
        status.append("Minion State: ").append(minionState).append(", ");

        if (minionState && minion != null) {
            status.append("Minion: ").append(minion.name).append(", ");
        }

        return status.toString();
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
