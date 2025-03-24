package model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;

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
    public List<Minion> getMinions() {
        List<Minion> list = new ArrayList<>();
        for (Minion minion : ownMinion) {
            if (minion != null) list.add(minion);
        }
        return list;
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
    public int getPlayerNumber() {
        return Number;
    }


    public int getMinionNumber() {
        return MinionNumber;
    }

    public int getHexNumber() {
        return HexNumber;
    }

}
