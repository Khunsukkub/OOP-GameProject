package com.example.oopprojectnew2.config.api;



import model.GameState;
import model.Minion;
import model.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/game/api")
public class GameController {

    @GetMapping("/checkPlayers")
    public ResponseEntity<Map<String, Boolean>> checkPlayers() {
        boolean ready = GameState.getInstance().getPlayers().size() == 2;
        Map<String, Boolean> response = new HashMap<>();
        response.put("ready", ready);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset")
    public ResponseEntity<Void> resetGameState() {
        GameState.reset();
        System.out.println("🔄 GameState reset");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/game-state")
    public ResponseEntity<Map<String, Object>> getGameState() {
        GameState state = GameState.getInstance();
        Map<String, Object> response = new HashMap<>();

        response.put("turn", 1); // ดึงจาก MainGame ถ้ามีระบบ turn จริง
        response.put("currentPlayer", "Player 1"); // สามารถปรับเป็น dynamic ได้

        Map<String, Object> playerData = new HashMap<>();
        for (Player player : state.getPlayers()) {
            Map<String, Object> data = new HashMap<>();
            data.put("money", player.budget);
            data.put("minions", player.getMinions().stream().map(minion -> Map.of(
                    "name", minion.name,
                    "color", minion.color,
                    "cost", minion.spawn_cost
            )).collect(Collectors.toList()));
            playerData.put(player.name, data);
        }

        response.put("playerData", playerData);
        return ResponseEntity.ok(response);
    }
}