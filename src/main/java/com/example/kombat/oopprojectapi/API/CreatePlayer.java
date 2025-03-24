package com.example.kombat.oopprojectapi.API;

import com.example.kombat.oopprojectapi.exception.BaseException;
import com.example.kombat.oopprojectapi.exception.UserException;
import com.example.kombat.oopprojectapi.model.MainGame;
import com.example.kombat.oopprojectapi.model.Player;
import com.example.kombat.oopprojectapi.request.PlayerNameRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kombat")
public class CreatePlayer {
    Player player1;
    Player player2;
    public static List<Player> playerList = new ArrayList<>();

    //หน้านี้ ถอดแบบมาจากหน้าใน canva // ถ้ากด Summit จะทำการสร้าง Player ทั้งสองคน พร้อมกับส่งไปหน้า เลือกจำนวนมินเนี่ยน
    @PostMapping("/createPlayer") //หลังจากกรอกอะไรเสร็จ จะเข้ามาหน้านี้ เพื่อสร้างตัวละคร โดยตัวแปรแบบ JSON คือ {"playerName1" : "Khun" , "playerName2" : "EiEi"}
    public ResponseEntity<Map<String, Object>> createPlayer(@RequestBody PlayerNameRequest playerNames) throws BaseException {
        String playerName1 = playerNames.getPlayerName1();
        String playerName2 = playerNames.getPlayerName2();

        if(playerName1 == null || playerName2 == null || playerName1.isEmpty() || playerName2.isEmpty()) {
            throw UserException.nameNull();
        }

        player1 = new Player(playerName1, MainGame.init_budget, 1);
        player2 = new Player(playerName2, MainGame.init_budget, 2);
        playerList.add(player1);
        playerList.add(player2);

        Map<String, Object> response = new HashMap<>();
        response.put("PlayerList", playerList);
        response.put("redirectUrl", "/kombat/VoteNumbersMinionSetting");

        return ResponseEntity.ok(response); // ส่งข้อมูล + URL กลับไป
    }


//    @PostMapping("/createPlayer")
//    public ResponseEntity<Map<String, Object>> createPlayer(@RequestBody Map<String, String> request) throws BaseException {
//        String playerName = request.get("playerName"); // รับชื่อผู้เล่นแต่ละคน
//
//        if(playerName == null || playerName.isEmpty()) {
//            throw UserException.nameNull();
//        }
//
//        // สร้าง Player ใหม่ (สมมุติ generatePlayerId() เป็นฟังก์ชันสร้างไอดีที่ไม่ซ้ำ)
//        Player newPlayer = new Player(playerName, MainGame.init_budget, playerList.size()+1);
//
//        // เก็บ player ลงใน lobby (ซึ่งเป็น global variable หรือจัดการใน database)
//        playerList.add(newPlayer);
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("player", newPlayer);
//
//        // ถ้า lobby เต็ม (ครบ 2 คน) ส่ง URL ไปให้เริ่มเกม
//        if(playerList.size() == 2) {
//            response.put("redirectUrl", "/kombat/VoteNumbersMinionSetting");
//        }
//
//        return ResponseEntity.ok(response);
//    }


}
