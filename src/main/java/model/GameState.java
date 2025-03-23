package model;

import com.example.oopprojectnew2.config.api.SetGameMode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GameState {
    private static GameState instance;
    private List<Player> players = new ArrayList<>();
    String gameMode;

    public List<Player> getPlayers() {
        return players;
    }
    public Player getPlayerByNumber(int number) {
        for (Player player : players) {
            if (player.Number == number) {
                return player;
            }
        }
        throw new IllegalArgumentException("Player not found with number: " + number);
    }
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


}