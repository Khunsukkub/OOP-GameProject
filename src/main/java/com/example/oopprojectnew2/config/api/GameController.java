package com.example.oopprojectnew2.config.api;

import model.GameState;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/game/api")
public class GameController {

    // ✔ ใช้เช็คว่าผู้เล่นครบ 2 หรือยัง
    @GetMapping("/checkPlayers")
    public ResponseEntity<Map<String, Boolean>> checkPlayers() {
        boolean ready = GameState.getInstance().getPlayers().size() == 2;
        Map<String, Boolean> response = new HashMap<>();
        response.put("ready", ready);
        return ResponseEntity.ok(response);
    }

    // ✅ NEW: reset GameState เมื่อจบเกมหรืออยากเริ่มใหม่
    @PostMapping("/reset")
    public ResponseEntity<Void> resetGameState() {
        GameState.reset(); // <-- เราจะเพิ่ม method นี้ใน GameState
        System.out.println("🔄 GameState reset");
        return ResponseEntity.ok().build();
    }
}
