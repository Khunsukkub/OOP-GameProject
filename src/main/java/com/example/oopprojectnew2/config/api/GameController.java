package com.example.oopprojectnew2.config.api;



import model.GameState;
import model.Minion;
import model.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

        response.put("turn", 1); // TODO: เปลี่ยนเป็น dynamic ถ้ามีระบบ turn จริง
        response.put("currentPlayer", "Player 1"); // TODO: เปลี่ยนเป็น dynamic

        Map<String, Object> playerData = new HashMap<>();
        for (Player player : state.getPlayers()) {
            Map<String, Object> data = new HashMap<>();
            data.put("money", player.budget);

            List<Map<String, Object>> minionList = new ArrayList<>();
            if (player.ownMinion != null) {
                for (Minion minion : player.ownMinion) {
                    if (minion == null) continue;
                    Map<String, Object> minionData = new HashMap<>();
                    minionData.put("name", minion.name);
                    minionData.put("color", minion.color);
                    minionData.put("cost", minion.spawn_cost);
                    minionList.add(minionData);
                }
            }

            data.put("minions", minionList);
            playerData.put(player.name, data);
        }

        response.put("playerData", playerData);
        return ResponseEntity.ok(response);
    }
}