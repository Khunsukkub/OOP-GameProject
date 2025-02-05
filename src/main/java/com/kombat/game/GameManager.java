package com.kombat.game;

import com.kombat.ai.MinionAI;
import com.kombat.config.GameConfig;
import com.kombat.model.Minion;
import com.kombat.model.Player;
import com.kombat.service.IMinionService;
import com.kombat.websocket.GameWebSocketHandler;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class GameManager {

    private final IMinionService minionService;
    private final MinionAI minionAI;
    private final GameWebSocketHandler webSocketHandler;
    private final MultiplayerManager multiplayerManager;

    @Autowired
    public GameManager(IMinionService minionService, MinionAI minionAI,
                       GameWebSocketHandler webSocketHandler, MultiplayerManager multiplayerManager) {
        this.minionService = minionService;
        this.minionAI = minionAI;
        this.webSocketHandler = webSocketHandler;
        this.multiplayerManager = multiplayerManager;
    }

    public void executeMultiplayerTurn() {
        Map<String, Player> players = (Map<String, Player>) multiplayerManager.getAllPlayers();

        for (Player player : players.values()) {
            List<Minion> enemies = minionService.getAllMinions();
            for (Minion minion : player.getMinions()) {
                minionAI.takeTurn(minion, enemies);
                webSocketHandler.sendGameUpdate("Player " + player.getName() + " moved Minion " + minion.getName());
            }
        }

        System.out.println("✅ Multiplayer Turn Completed!");
    }
    public void checkEndgame() {
        List<Minion> allMinions = minionService.getAllMinions();
        int maxRounds = GameConfig.getInt("maxRounds");

        if (allMinions.isEmpty()) {
            System.out.println("🎉 Game Over! No minions left.");
        } else if (currentRound >= maxRounds) {
            System.out.println("🎉 Game Over! Checking final scores...");
            Player winner = determineWinner();
            System.out.println("🏆 Winner: " + (winner != null ? winner.getName() : "Draw!"));
        }
    }

    private Player determineWinner() {
        Collection<Player> players = multiplayerManager.getAllPlayers();  // ✅ เปลี่ยนเป็น Collection

        return players.stream()
                .max(Comparator.comparingInt(p -> (p.getMinions() != null ? p.getMinions().size() : 0) + p.getBudget())) // ✅ เช็ค null ก่อนเรียก size()
                .orElse(null);
    }
    private int currentRound = 0;  // ✅ เพิ่มตัวแปรเก็บจำนวนรอบ

    public void nextRound() {
        currentRound++;
    }
    public void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        webSocketHandler.sendGameUpdate("🔄 Turn changed! Now it's " + getCurrentPlayer().getName());
    }

    public void checkEndgame() {
        List<Minion> allMinions = minionService.getAllMinions();
        if (allMinions.isEmpty()) {
            webSocketHandler.sendGameUpdate("🎉 Game Over! No minions left.");
        }
    }
}
