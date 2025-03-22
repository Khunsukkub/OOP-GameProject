package api;

import exception.BaseException;
import exception.MinionException;
import model.MainGame;
import model.Minion;
import model.MinionState;
import model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/game")
public class MinionSetting {

    @PostMapping("/api/NumbersMinionSetting")
    public ResponseEntity<Void> NumbersMinionSetting(@RequestBody int NumberOfMinion) throws BaseException {

        MainGame.setNumberOfMinion(NumberOfMinion);

            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", "/MinionSetting")
                    .build();

    }

    @PostMapping("/api/MinionSetting/{id}")
    public ResponseEntity<Void> MinionSetting(@PathVariable String id, @RequestBody String minionName , String HP , String DEF , String spawnCost, String strategy , String playerNumber) throws BaseException {
        if(minionName.isEmpty()) throw MinionException.nameNull();
        if(HP.isEmpty()) throw MinionException.hpNull();
        if(DEF.isEmpty()) throw MinionException.defNull();

        int currentId = Integer.parseInt(id);
        int totalMinions = Integer.parseInt(MainGame.getNumberOfMinion());
        int nextId = (currentId % totalMinions)+1;

        double hp = Double.parseDouble(HP);
        double def = Double.parseDouble(DEF);
        int spawncost = Integer.parseInt(spawnCost);

//        List<Minion> minionWantedList = new ArrayList<>(totalMinions);
//        minionWantedList.add(new Minion(minionName,hp,def,spawncost,strategy));

        //------ตอนนี้ติดปัญหาที่จะ สร้าง API ยังไงให้ Minion ตัวนั้นๆ สามารถถูกแก้ไขได้จนกว่าจะ Summit------//

        // Redirect ไปยัง "/api/MinionSetting/{nextId}"
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "/game/api/MinionSetting/" + nextId)
                .build();
    }
}
