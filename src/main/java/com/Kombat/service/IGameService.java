package com.Kombat.service;

import com.Kombat.model.Player;

public interface IGameService {
    void startGame();
    void processTurn(Player currentPlayer);
}
