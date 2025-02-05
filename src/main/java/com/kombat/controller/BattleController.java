package com.kombat.controller;

import com.kombat.battle.BattleManager;
import com.kombat.model.Minion;
import com.kombat.service.IMinionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/battle")
public class BattleController<AttackRequest> {

    private final IMinionService minionService;
    private final BattleManager battleManager;

    public BattleController(IMinionService minionService, BattleManager battleManager) {
        this.minionService = minionService;
        this.battleManager = battleManager;
    }

    @PostMapping("/attack")
    public String attackMinion(@RequestBody AttackRequest request) {
        Minion attacker = minionService.getAllMinions().stream()
                .filter(m -> m.getName().equals(request.getAttacker()))
                .findFirst()
                .orElse(null);

        Minion target = minionService.getAllMinions().stream()
                .filter(m -> m.getName().equals(request.getTarget()))
                .findFirst()
                .orElse(null);

        if (attacker == null || target == null) {
            return "❌ Attacker or Target not found!";
        }

        battleManager.attack(attacker, target, request.getAttackPower());
        return "✅ Attack executed!";
    }
}
