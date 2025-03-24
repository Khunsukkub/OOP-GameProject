package com.example.kombat.oopprojectapi.model;

import lombok.Getter;

public class Player {
    public String name;
    public double budget = 0;
    public Hex[] ownHex = new Hex[Hex.total];
    public Minion[] ownMinion = new Minion[MainGame.max_spawns];
    private int MinionNumber = 0;
    private int HexNumber = 0;
    public int Number = 0;

    public Player(String name, double Budget, int playerNumber) {
        this.name = name;
        this.budget = Budget;
        this.Number = playerNumber;
    }

    public int getPlayerNumber() {
        return Number;
    }

    public void addMinionList (Minion minion) {
        MinionState.addMinion(Number,minion);
    }

    public void addMinion (Minion minion) {
        ownMinion[MinionNumber] = minion;
        MinionNumber++;
    }

    public void addHex (Hex hex) {
        ownHex[HexNumber] = hex;
        HexNumber++;
    }

    public int getMinionNumber() {
        return MinionNumber;
    }

    public int getHexNumber() {
        return HexNumber;
    }

}
