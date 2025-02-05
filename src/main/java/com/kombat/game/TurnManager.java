package com.kombat.game;

import com.kombat.model.Player;
import java.util.List;

public class TurnManager {
    private List<Player> players;
    private int currentPlayerIndex = 0;

    public TurnManager(List<Player> players) {
        this.players = players;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        System.out.println("🔄 Turn changed! Now it's " + getCurrentPlayer().getName() + "'s turn.");
    }
}
