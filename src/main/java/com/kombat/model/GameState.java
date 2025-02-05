package com.kombat.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameState {
    private boolean isRunning;
    private String winner;

    public GameState() {
        this.isRunning = false;
        this.winner = null;
    }
}
