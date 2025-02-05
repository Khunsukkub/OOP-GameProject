package com.kombat.game;

import com.kombat.model.Minion;
import com.kombat.service.MinionService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class AutoBattleManager {
    private final MinionService minionService;
    private final Random random = new Random();

    public AutoBattleManager(MinionService minionService) {
        this.minionService = minionService;
    }

    public void startAutoBattle(List<Minion> minions) {
        while (!isGameOver(minions)) {
            for (Minion minion : minions) {
                HexDirection direction = HexDirection.values()[random.nextInt(6)];
                minionService.moveMinion(minion, direction);
            }
        }
    }

    private boolean isGameOver(List<Minion> minions) {
        return minions.stream().allMatch(m -> m.getHp() <= 0);
    }
}
