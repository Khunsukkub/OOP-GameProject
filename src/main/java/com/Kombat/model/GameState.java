package com.Kombat.model;

import java.util.List;

public class GameState {
    private List<Player> players;
    private int currentTurn;

    public GameState(List<Player> players) {
        this.players = players;
        this.currentTurn = 1;
    }

    public void nextTurn() {
        currentTurn++;
    }

    public boolean isGameOver() {
        return players.stream().anyMatch(p -> !p.hasMinionsLeft());
    }
}
