package com.example.kombat.oopprojectapi.API;

import com.example.kombat.oopprojectapi.model.GameState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/kombat")
public class GameMode {

    public enum GameModes { // คำการ ที่ใช้เลือกส่ง ดูในนี้
        PlayerVSPlayer,
        PlayerVSBot,
        BotVSBot;
    }

    //หลังจาก กดปุ่ม start จะโผล่มาในหน้า เลือก GameMode
    @PostMapping("/gameMode") //เอาพวกเนี้ย ไปฝังไว้ใน ปุ่มเลือกโหมด ทั้ง 3 ปุ่ม ซึ่งตั้ง เป็น Object ใน JSON แบบ {"PlayerVSPlayer"}
    public ResponseEntity<Void> setGameMode(@RequestBody GameModes gameMode) {
        GameState gameState = getGameState();
        gameState.setGameState(gameMode);


        // ส่ง HTTP redirect ไปยังหน้าใหม่
        if(gameMode == GameModes.PlayerVSPlayer) { //ถ้ากด PlayereVSPlayer จะเข้าหน้านี้ ไปหาห้องแบบ ใส่ ID ห้อง
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", "/kombat/roomSearching")
                    .build();
        }

        return ResponseEntity.status(HttpStatus.FOUND) //เลือกอื่นๆ จะเข้าไปสร้างตัวละคร เลย
                .header("Location", "/kombat/createPlayer")
                .build();
    }

    private GameState getGameState() {
        return GameState.getInstance();
    }

}
