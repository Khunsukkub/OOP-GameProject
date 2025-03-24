package com.example.oopprojectnew2.config.api;

import exception.BaseException;
import exception.RoomSearchingException;
import lombok.Getter;
import model.GameState;
import model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game/api")
public class RoomSearching {
    // ใน RoomSearching.java หรือ GameController.java ก็ได้
    @GetMapping("/players")
    public ResponseEntity<List<Player>> getPlayers() {
        return ResponseEntity.ok(GameState.getInstance().getPlayers());
    }
    @GetMapping("/player-count")
    public ResponseEntity<Integer> getPlayerCount() {
        int count = GameState.getInstance().getPlayerCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/reset")
    public ResponseEntity<String> resetGame() {
        GameState.reset();
        return ResponseEntity.ok("Game reset");
    }

    @Getter
    public static class PlayerRequest {
        public String name;
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

    @PostMapping("/roomSearching")
    public ResponseEntity<Void> roomSearching(@RequestBody PlayerRequest request) throws BaseException {
        try {
            String input = request.getName();
            System.out.println("📥 Received name: " + input);

            if (input == null || input.isEmpty()) throw RoomSearchingException.idNull();

            GameState gameState = GameState.getInstance();
            boolean alreadyExists = gameState.getPlayers().stream()
                    .anyMatch(p -> p.name.equalsIgnoreCase(input));

            if (!alreadyExists) {
                if (gameState.getPlayerCount() >= 2) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).build();
                }

                Player player = new Player(input, 1000.0, gameState.getPlayerCount() + 1);
                gameState.addPlayer(player);
                System.out.println("✅ Player added: " + input);
            } else {
                System.out.println("⚠️ Player already exists: " + input);
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
