package com.Kombat.service;

import com.Kombat.model.GameState;
import com.Kombat.model.Player;
import org.springframework.stereotype.Service;

@Service
public class GameService implements IGameService {
    private GameState gameState;

    @Override
    public void startGame() {
        System.out.println("Game Started!");
    }

    @Override
    public void processTurn(Player currentPlayer) {
        System.out.println("Processing turn for: " + currentPlayer.getName());
        currentPlayer.increaseBudget(10);
    }
}
