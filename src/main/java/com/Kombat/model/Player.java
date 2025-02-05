package com.Kombat.model;

import java.util.List;

public class Player {
    private String name;
    private int budget;
    private List<Minion> minions;

    public Player(String name, int budget) {
        this.name = name;
        this.budget = budget;
    }

    public void increaseBudget(int amount) {
        this.budget += amount;
    }

    public void addMinion(Minion minion) {
        minions.add(minion);
    }

    public boolean hasMinionsLeft() {
        return !minions.isEmpty();
    }

    //dont understand wait for fix
    public String getName() {
        return this.name;
    }
}
