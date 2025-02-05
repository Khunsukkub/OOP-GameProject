package com.kombat.service;

import com.kombat.model.Minion;
import java.util.List;

public interface IMinionService {
    void moveMinion(Minion minion, String direction);
    void attackMinion(Minion attacker, Minion target, int attackPower);
    List<Minion> getAllMinions();
    void addMinion(Minion minion);
}
