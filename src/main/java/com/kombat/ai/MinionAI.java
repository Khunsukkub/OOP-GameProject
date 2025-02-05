package com.kombat.ai;

import com.kombat.battle.BattleManager;
import com.kombat.model.Minion;
import com.kombat.service.IMinionService;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Random;
//takeTurn() → ให้ AI ตัดสินใจว่าจะ โจมตี หรือเคลื่อนที่
//isEnemyInRange() → ตรวจสอบว่าศัตรูอยู่ในระยะยิงหรือไม่
//moveToward() → เคลื่อนที่เข้าไปใกล้ศัตรู
@Component  // ✅ บอก Spring ให้สร้าง Bean สำหรับคลาสนี้
public class MinionAI {

    private final IMinionService minionService;
    private final BattleManager battleManager;
    private final Random random = new Random();

    public MinionAI(IMinionService minionService, BattleManager battleManager) {
        this.minionService = minionService;
        this.battleManager = battleManager;
    }

    public void takeTurn(Minion minion, List<Minion> enemies) {
        Minion target = battleManager.findNearestEnemy(minion, enemies);
        if (target != null && isEnemyInRange(minion, target)) {
            int attackPower = random.nextInt(10) + 5;
            battleManager.attack(minion, target, attackPower);
        } else {
            moveToward(minion, target);
        }
    }

    private boolean isEnemyInRange(Minion minion, Minion target) {
        return Math.abs(target.getRow() - minion.getRow()) <= 1 &&
                Math.abs(target.getCol() - minion.getCol()) <= 1;
    }

    private void moveToward(Minion minion, Minion target) {
        if (target == null) return;
        if (Math.abs(target.getRow() - minion.getRow()) > Math.abs(target.getCol() - minion.getCol())) {
            minionService.moveMinion(minion, target.getRow() > minion.getRow() ? "down" : "up");
        } else {
            minionService.moveMinion(minion, target.getCol() > minion.getCol() ? "right" : "left");
        }
    }
}

