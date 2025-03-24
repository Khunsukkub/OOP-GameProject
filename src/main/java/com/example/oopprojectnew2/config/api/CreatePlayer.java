package com.example.oopprojectnew2.config.api;

import exception.BaseException;
import exception.UserException;
import model.GameState;
import model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game/api")
public class CreatePlayer {

    public static class CreatePlayerRequest {
        private String name;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

    @PostMapping("/createPlayer")
    public ResponseEntity<Void> createPlayer(@RequestBody CreatePlayerRequest request) throws BaseException {
        String playerName = request.getName();

        System.out.println("📥 Received name: " + playerName);

        if (playerName == null || playerName.trim().isEmpty()) {
            throw UserException.nameNull();
        }

        GameState gameState = GameState.getInstance();

        // ตรวจสอบชื่อซ้ำ
        boolean alreadyExists = gameState.getPlayers().stream()
                .anyMatch(p -> p.name.equalsIgnoreCase(playerName));
        if (alreadyExists) {
            System.out.println("⚠️ Duplicate name: " + playerName);
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        // ตรวจสอบจำนวนผู้เล่น
        if (gameState.getPlayerCount() >= 2) {
            System.out.println("🚫 Already 2 players in game");
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Player player = new Player(playerName, 1000.0, gameState.getPlayerCount() + 1);
        gameState.addPlayer(player);
        System.out.println("✅ Player added: " + playerName);

        return ResponseEntity.ok().build();
    }
}
