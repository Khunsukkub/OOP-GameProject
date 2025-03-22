package api;

import exception.BaseException;
import exception.UserException;
import model.GameState;
import model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class CreatePlayer {

    @PostMapping("/api/createPlayer")
    public ResponseEntity<Void> createPlayer(@RequestBody String playerName) throws BaseException {

        if(playerName == null || playerName.isEmpty()) throw UserException.nameNull();

        // สร้าง Player ใหม่
        Player player = new Player(playerName, 1000.0, GameState.getInstance().getPlayers().size() + 1);

        // เพิ่ม Player ไปยัง GameState
        GameState gameState = GameState.getInstance();
        gameState.addPlayer(player);

        // ตรวจสอบว่ามีผู้เล่นครบสองคนหรือยัง
        if (gameState.getPlayers().size() == 2) {
            // ถ้าครบสองคนแล้ว ไปที่หน้าถัดไป
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", "/NumbersMinionSetting")
                    .build();
        }

        // ถ้ายังไม่ครบสองคน ให้ส่งกลับไปที่หน้าเดิม
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "/waitingForPlayer")
                .build();
    }
}
