package com.kombat.service;

import org.springframework.stereotype.Service;
import com.kombat.model.GameState;

@Service
public class GameService implements IGameService {

    private final GameState gameState = new GameState();

    @Override
    public void startGame() {
        gameState.setRunning(true);
        System.out.println("✅ Game Started!");
    }

    @Override
    public String getGameState() {
        return gameState.isRunning() ? "Game is running" : "Game is not running";
    }
}
