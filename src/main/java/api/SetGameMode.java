package api;

import model.GameState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class SetGameMode {

    public enum GameMode {
        PlayerVSPlayer,
        PlayerVSBot,
        BotVSBot;
    }

    @PostMapping("/api/gameMode")
    public ResponseEntity<Void> setGameMode(@RequestBody GameMode gameMode) {
        GameState gameState = getGameState();
        gameState.setGameState(gameMode);

        // ส่ง HTTP redirect ไปยังหน้าใหม่
        if(gameMode == GameMode.PlayerVSPlayer) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", "/roomSearching")
                    .build();
        }

        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "/createPlayer")
                .build();
    }

    private GameState getGameState() {
        return GameState.getInstance();
    }
}