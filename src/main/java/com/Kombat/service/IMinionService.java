package com.Kombat.service;

import com.Kombat.model.Minion;

public interface IMinionService {
    void moveMinion(Minion minion, String direction);
    void attackMinion(Minion attacker, Minion target, int attackPower);
}
