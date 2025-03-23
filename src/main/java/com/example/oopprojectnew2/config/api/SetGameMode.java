package com.example.oopprojectnew2.config.api;

import model.GameState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game/api")
@CrossOrigin(origins = "http://localhost:3000") // อนุญาตให้ Frontend (localhost:3000) เรียกได้
public class SetGameMode {

    // Enum สำหรับโหมดเกม
    public enum GameMode {
        PlayerVSPlayer,
        PlayerVSBot,
        BotVSBot;
    }

    // DTO สำหรับรับ JSON { "mode": "..." } จาก Frontend
    public static class GameModeRequest {
        private String mode;
        public String getMode() { return mode; }
        public void setMode(String mode) { this.mode = mode; }
    }

    @PostMapping("/gameMode")
    public ResponseEntity<String> setGameMode(@RequestBody GameModeRequest request) {
        String modeStr = request.getMode();
        GameMode gameMode;

        try {
            gameMode = GameMode.valueOf(modeStr);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        GameState gameState = getGameState(); // ✅ ตรงนี้ไม่แดงแล้ว
        gameState.setGameState(gameMode);

        String redirectUrl = (gameMode == GameMode.PlayerVSPlayer)
                ? "/roomSearching"
                : "/createPlayer";

        return ResponseEntity.ok(redirectUrl);
    }

    // ✅ เพิ่ม method นี้ด้านล่าง
    private GameState getGameState() {
        return GameState.getInstance();
    }
}
