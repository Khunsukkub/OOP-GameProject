package com.example.kombat.oopprojectapi.API;

import com.example.kombat.oopprojectapi.exception.BaseException;
import com.example.kombat.oopprojectapi.exception.MinionException;
import com.example.kombat.oopprojectapi.model.Controller;
import com.example.kombat.oopprojectapi.model.Hex;
import com.example.kombat.oopprojectapi.model.MainGame;
import com.example.kombat.oopprojectapi.model.Minion;
import com.example.kombat.oopprojectapi.request.MMinionRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/kombat")
public class MinionSettingController {
    int nextId = 0;
    private int totalMinions = Integer.parseInt(MainGame.getNumberOfMinion());
    private static List<Minion> tempMinionSetting = new ArrayList<>() {
    };

    public static List<Minion> getAllTempMinionSetting() {
        return tempMinionSetting;
    }

    @PostMapping("/VoteNumbersMinionSetting") // ส่งมาเป็น ตัวเลขตรงๆ ไปเลย ไม่ต้องมี "" หรือ {} แต่เป็นแบบ JSON 5 6 7 อะไรก็ได้
    public ResponseEntity<Void> numbersMinionSetting(@RequestBody String numberOfMinion) throws BaseException {

        MainGame.setNumberOfMinion(Integer.parseInt(numberOfMinion));

        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "/kombat/MinionSetting/0") //จะส่งไปที่ ตั้งค่า Minion ตัวที่ 1
                .build();
    }


    // ตั้งค่า Minion ไปทีละตัวตามจำนวนที่ตั้งค่าไปด้านบน
//    {
//        "minionName": "MinionA",
//            "hp": 100,
//            "def": 10,
//            "spawnCost": 5,
//            "strategy": "aggressive"
//    }

    @PostMapping("/MinionSetting/{id}")
    public ResponseEntity<Map<String, Object>> minionSetting(@RequestBody MMinionRequest minionRequest) throws BaseException {

        // ตรวจสอบค่าว่าง
        if (minionRequest.getMinionName().isEmpty()) throw MinionException.nameNull();
        if (minionRequest.getHp() <= 0) throw MinionException.hpNull();
        if (minionRequest.getDef() < 0) throw MinionException.defNull();
        if (minionRequest.getSpawnCost() < 0) throw MinionException.spawnCostNull();

        // สร้าง Minion โดยต้องตั้งค่า Name , HP , DEF , Spawncost : Strategy แล้วแต่จะตั้งไม่ตั้ง
        addMinionForPlayer(new Minion(
                minionRequest.getMinionName(),
                minionRequest.getHp(),
                minionRequest.getDef(),
                minionRequest.getSpawnCost(),
                minionRequest.getStrategy()
        ));

        // ส่งกลับข้อมูลเป็น JSON
        Map<String, Object> response = new HashMap<>();
        response.put("nextId", nextId+1);
        response.put("message", "Minion " + (nextId) +" saved successfully");

        return ResponseEntity.ok(response);
    }

    //กดปุ่ม Next แล้วจะเลื่อนไปยัง ตั้งค่า Minion ตัวต่อไป
    @GetMapping("/MinionSetting/{id}/Next") // {id} หมายุถึงมันจะดึง ข้อมูลในช่องหมายเลขมินเนี่ยนมาเป็น input id
    public ResponseEntity<Void> nextMinionSetting(@PathVariable int id) throws BaseException {
        nextId = (id + 1) % (totalMinions + 1); //การ if-else แบบไม่ต้อง if-else ถ้า id ปัจจุบันมันเท่ากับ จำนวนสูงสุดที่ตั้งค่าได้แล้วจะวนไป 0
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "/kombat/MinionSetting/" + nextId) //จะส่งไปตั้งค่ามินเนี่ยนหมายเลขถัดไป
                .build();
    }


    // เอาอันนี้ไปใส่ในปุ่ม Summit เพื่อเข้าเริ่มเกม
    @GetMapping("/MinionSettingSummit")
    public ResponseEntity<Map<String, Object>> minionSettingSummit() {
        // บันทึก Minions ที่ตั้งค่า
        MainGame.minionList = tempMinionSetting.toArray(new Minion[0]);

        // สร้างแผนที่ Hex ขนาด 8x8
        Hex[][] hexGrid = new Hex[Hex.totalInRow][Hex.totalInCol];

        // กำหนดค่าให้กับ Hex แต่ละตัวในแผนที่
        for (int row = 0; row < Hex.totalInRow; row++) {
            for (int col = 0; col < Hex.totalInCol; col++) {
                hexGrid[row][col] = new Hex(row, col); // สร้าง Hex ที่มีตำแหน่ง row, col
            }
        }

        // เก็บแผนที่ Hex ในเกม (เพิ่มใน MainGame หรือที่เหมาะสม)
        Controller.setMap(hexGrid);

        Map<String, Object> response = new HashMap<>();
        response.put("minionList", MainGame.minionList);
        response.put("redirectUrl", "/kombat/MainGame");

        // ส่ง `minionList` ไปยังหน้าบ้าน (frontend) เป็น List ของ Minion
        return ResponseEntity.ok(response); // ส่งข้อมูล + URL กลับไป
    }


    public void addMinionForPlayer(Minion minion) {
        tempMinionSetting.add(minion);
    }
}