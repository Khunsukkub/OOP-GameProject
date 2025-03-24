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
import java.util.List;

@RestController
@RequestMapping("/kombat")
public class CreatePlayer {
    Player player1;
    Player player2;

    //หน้านี้ ถอดแบบมาจากหน้าใน canva // ถ้ากด Summit จะทำการสร้าง Player ทั้งสองคน พร้อมกับส่งไปหน้า เลือกจำนวนมินเนี่ยน
    @PostMapping("/createPlayer") //หลังจากกรอกอะไรเสร็จ จะเข้ามาหน้านี้ เพื่อสร้างตัวละคร โดยตัวแปรแบบ JSON คือ {"playerName1" : "Khun" , "playerName2" : "EiEi"}
    public ResponseEntity<List<Player>> createPlayer(@RequestBody PlayerNameRequest playerNames) throws BaseException {
        String playerName1 = playerNames.getPlayerName1();
        String playerName2 = playerNames.getPlayerName2();

        if(playerName1 == null || playerName2 == null || playerName1.isEmpty() || playerName2.isEmpty()) {
            throw UserException.nameNull();
        }

        List<Player> playerList = new ArrayList<>();
        player1 = new Player(playerName1, MainGame.init_budget, 1);
        player2 = new Player(playerName2, MainGame.init_budget, 2);
        playerList.add(player1);
        playerList.add(player2);

        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "/kombat/VoteNumbersMinionSetting") //สร้างตัวเสร็จ จะเข้าไปหน้าโหวตจำนวน Minion
                .body(playerList);
    }

}
