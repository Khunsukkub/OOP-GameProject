package com.example.kombat.oopprojectapi.API;

import com.example.kombat.oopprojectapi.model.*;
import com.example.kombat.oopprojectapi.request.GameStateDTO;
import com.example.kombat.oopprojectapi.request.PurchaseResponse;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.kombat.oopprojectapi.model.Map.map;

@RestController
@RequestMapping("/kombat")
public class Board {

    //กำหนด เขตของผู้เล่นแรกเริ่ม และ แสดงข้อมูลโดยรวมของเกม
    @GetMapping(value = "/MainGame", produces = "application/json")
    public ResponseEntity<GameStateDTO> InitMap() {
        Controller.initializeMap();
        GameStateDTO gameState = new GameStateDTO(
                MainGame.current_turn,
                MainGame.max_turns,
                MainGame.init_budget,
                MainGame.max_budget,
                MainGame.max_spawns,
                MainGame.spawn_lefts,
                MainGame.current_player,
                Map.getInstance().map
        );
        return ResponseEntity.status(HttpStatus.OK).body(gameState);
    }

    @GetMapping("/board/{row}/{col}/status") //Pre-condition ROW COl เริ่มต้น (0,0) -> (7,7)
    public ResponseEntity<String> hexStatus(@PathVariable int row, @PathVariable int col) {
        // ค้นหา Hex ตามตำแหน่ง row และ col (สมมติว่าเรามีวิธีในการค้นหาตำแหน่งใน map)
        Hex hex = Map.getInstance().getHexAt(row, col);

        if (hex == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Hex not found at position: (" + row + ", " + col + ")");
        }

        // เรียกใช้ hexStatus() เพื่อดึงข้อมูลสถานะ
        String status = hex.hexStatus();

        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    // ฟังก์ชันที่จะเช็ค Hex ที่สามารถซื้อได้
    @GetMapping("/availableHexes/{playerId}")
    public ResponseEntity<List<Hex>> getAvailableHexes(@PathVariable int playerId) {
        Player player = getPlayerById(playerId);  // สมมุติว่าเรามีฟังก์ชันเพื่อดึง Player ตาม playerId
        List<Hex> availableHexes = new ArrayList<>();

        // ตรวจสอบทุก Hex ในแผนที่
        for (int i = 0; i < Hex.totalInRow; i++) {
            for (int j = 0; j < Hex.totalInCol; j++) {
                Hex hex = map[i][j];

                // เช็คว่า Hex นี้สามารถซื้อได้
                if (Map.canBeBoughtByPlayer(hex, player)) {
                    availableHexes.add(hex);  // ถ้าซื้อได้ก็เก็บ Hex นี้
                }
            }
        }

        // ถ้าไม่มี Hex ที่สามารถซื้อได้
        if (availableHexes.isEmpty()) {
            return ResponseEntity.noContent().build();  // ถ้าไม่พบ Hex ที่สามารถซื้อได้, ส่ง HTTP 204 No Content
        }

        // ส่งข้อมูล Hex ที่สามารถซื้อได้กลับไปยัง frontend
        return ResponseEntity.ok(availableHexes);  // ส่งข้อมูล Hex ในรูปแบบ JSON (200 OK)
    }

    //ฟังก์ชันที่ใช้ในการ ซื้อ Hex
    @PostMapping("/{playerId}/buyHex/{row}/{col}")
    public ResponseEntity<PurchaseResponse> buyHex(@PathVariable int playerId, @PathVariable int row, @PathVariable int col) {
        Player player = getPlayerById(playerId);
        Hex hex = Map.getInstance().getHexAt(row, col);

        PurchaseResponse response = Controller.buySelectedHex(player, hex);

        if ("ซื้อสำเร็จ".equals(response.getMessage())) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    //ฟังก์ชันที่ใช้ในการ ซื้อ Minion
    @PostMapping("/{playerId}/buyMinion/{minionName}")
    public ResponseEntity<PurchaseResponse> buyMinion(@PathVariable String minionName , @PathVariable int playerId) {
        Minion minion = getMinionByName(minionName);
        Player player = getPlayerById(playerId);

        PurchaseResponse response = Controller.buyMinion(player, minion);

        if ("ซื้อสำเร็จ".equals(response.getMessage())) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    //Func ในการวางมินเนี่ยนหลังจากซื้อเสร็จทันที
    @PostMapping("/{playerId}/Deploy/{minionName}/{row}/{col}")
    public ResponseEntity<String> deployMinion(@PathVariable String minionName, @PathVariable int row, @PathVariable int col, @PathVariable int playerId) {
        Minion minion = getMinionByName(minionName);
        Hex hex = Map.getInstance().getHexAt(row, col);

        hex.setMinion(minion);

        return ResponseEntity.ok("วางสำเร็จ");
    }

    //

    // ฟังก์ชันช่วยในการดึง Minion จาก String
    private Minion getMinionByName(String minionName) {
        for (Minion m : MainGame.minionList) {
            if(m.name == minionName) {
                return m;
            }
        }
        return null;
    }

    // ฟังก์ชันช่วยในการดึง Player จาก ID
    private Player getPlayerById(int playerId) {
        // ค้นหา Player จาก ID (สมมุติว่า playerList มีข้อมูลทั้งหมดของผู้เล่น)
        for (Player p : CreatePlayer.playerList) {
            if (p.getPlayerNumber() == playerId) {
                return p;
            }
        }
        return null;  // หากไม่พบ player ก็คืนค่า null
    }

}
