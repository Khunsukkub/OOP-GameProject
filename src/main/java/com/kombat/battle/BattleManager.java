package com.kombat.battle;

import com.kombat.model.Minion;
import org.springframework.stereotype.Service;
import java.util.List;
//attack() → ให้มินเนี่ยนทำการโจมตีศัตรู
//findNearestEnemy() → ค้นหาศัตรูที่อยู่ใกล้ที่สุด

@Service
public class BattleManager {

    public void attack(Minion attacker, Minion target, int attackPower) {
        if (target == null) {
            System.out.println("❌ No enemy found!");
            return;
        }

        // คำนวณดาเมจโดยพิจารณา defenseFactor
        int damage = Math.max(1, attackPower - target.getDefenseFactor());
        target.setHp(Math.max(0, target.getHp() - damage));

        System.out.println("🔥 " + attacker.getName() + " attacked " + target.getName() + " for " + damage + " damage");

        if (target.getHp() <= 0) {
            System.out.println("💀 " + target.getName() + " has been defeated!");
        }
    }

    public Minion findNearestEnemy(Minion minion, List<Minion> enemies) {
        Minion closest = null;
        int minDistance = Integer.MAX_VALUE;

        for (Minion enemy : enemies) {
            int distance = Math.abs(enemy.getRow() - minion.getRow()) + Math.abs(enemy.getCol() - minion.getCol());
            if (distance < minDistance) {
                minDistance = distance;
                closest = enemy;
            }
        }
        return closest;
    }
}
