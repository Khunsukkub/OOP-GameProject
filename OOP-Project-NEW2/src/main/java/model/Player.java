package model;

import lombok.Getter;

public class Player {
    public String name;
    public double budget = 0;
    public Hex[] ownHex = new Hex[Hex.total];
    public Minion[] ownMinion = new Minion[MainGame.max_spawns];
    public int current_Minion = 0;
    public int Number = 0;

    public Player(String name, double Budget, int playerNumber) {
        this.name = name;
        this.budget = Budget;
        this.Number = playerNumber;
    }

    public void addMinion (Minion minion) {
        ownMinion[current_Minion] = minion;
        current_Minion++;
    }

}

