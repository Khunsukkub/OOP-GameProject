package com.example.kombat.oopprojectapi.request;

import com.example.kombat.oopprojectapi.model.Hex;
import com.example.kombat.oopprojectapi.model.Player;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameStateDTO {
    private int currentTurn;
    private int maxTurns;
    private double initBudget;
    private double maxBudget;
    private int maxSpawns;
    private int spawnLefts;
    private Player currentPlayer;
    private Hex[][] map;

    // Constructor
    public GameStateDTO(int currentTurn, int maxTurns, double initBudget, double maxBudget,
                        int maxSpawns, int spawnLefts, Player currentPlayer, Hex[][] map) {
        this.currentTurn = currentTurn;
        this.maxTurns = maxTurns;
        this.initBudget = initBudget;
        this.maxBudget = maxBudget;
        this.maxSpawns = maxSpawns;
        this.spawnLefts = spawnLefts;
        this.currentPlayer = currentPlayer;
        this.map = map;
    }
}
