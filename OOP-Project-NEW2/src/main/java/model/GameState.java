package model;

import api.SetGameMode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GameState {
    private static GameState instance;
    private List<Player> players = new ArrayList<>();
    String gameMode;

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public void setGameState(SetGameMode.GameMode gameMode) {
        this.gameMode = gameMode.toString();
    }

    public void addPlayer(Player player) {
        if (players.size() < 2) { // จำกัดผู้เล่นที่ 2 คน
            players.add(player);
        } else {
            throw new IllegalStateException("Game already has 2 players.");
        }
    }

    public Collection<Object> getPlayers() {
        return Collections.singleton(players);
    }
}
