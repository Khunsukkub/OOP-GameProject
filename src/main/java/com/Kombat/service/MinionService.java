package com.Kombat.service;

import com.Kombat.model.Minion;
import org.springframework.stereotype.Service;

@Service
public class MinionService implements IMinionService {
    @Override
    public void moveMinion(Minion minion, String direction) {
        System.out.println(minion.getName() + " moves " + direction);
    }

    @Override
    public void attackMinion(Minion attacker, Minion target, int attackPower) {
        attacker.attack(target, attackPower);
    }
}
