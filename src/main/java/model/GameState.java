package model;

import com.example.oopprojectnew2.config.api.SetGameMode;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private static GameState instance;
    private static List<Player> players = new ArrayList<>();
    static String gameMode;

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

    public int getPlayerCount() {
        return players.size();
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
        if (players.size() < 2) {
            players.add(player);
        } else {
            throw new IllegalStateException("Game already has 2 players.");
        }
    }

    public static void reset() {
        players.clear();
        gameMode = null;
        System.out.println("🧹 Game state reset!");
    }
}
