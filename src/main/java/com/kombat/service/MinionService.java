package com.kombat.service;

import com.kombat.game.HexDirection;
import com.kombat.model.Minion;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MinionService implements IMinionService {

    private final List<Minion> minions = new ArrayList<>();

    @Override
    public void moveMinion(Minion minion, String direction) {
        switch (direction.toLowerCase()) {
            case "up": minion.setRow(minion.getRow() - 1); break;
            case "down": minion.setRow(minion.getRow() + 1); break;
            case "left": minion.setCol(minion.getCol() - 1); break;
            case "right": minion.setCol(minion.getCol() + 1); break;
        }
    }

    @Override
    public void attackMinion(Minion attacker, Minion target, int attackPower) {
        int damage = Math.max(1, attackPower - target.getDefenseFactor());
        target.setHp(Math.max(0, target.getHp() - damage));
    }

    @Override
    public void addMinion(Minion minion) {
        minions.add(minion);
    }

    @Override
    public List<Minion> getAllMinions() {
        return minions;
    }
    public void moveMinion(Minion minion, HexDirection direction) {
        switch (direction) {
            case UP_LEFT -> { minion.setRow(minion.getRow() - 1); minion.setCol(minion.getCol() - 1); }
            case UP_RIGHT -> { minion.setRow(minion.getRow() - 1); minion.setCol(minion.getCol() + 1); }
            case LEFT -> { minion.setCol(minion.getCol() - 2); }
            case RIGHT -> { minion.setCol(minion.getCol() + 2); }
            case DOWN_LEFT -> { minion.setRow(minion.getRow() + 1); minion.setCol(minion.getCol() - 1); }
            case DOWN_RIGHT -> { minion.setRow(minion.getRow() + 1); minion.setCol(minion.getCol() + 1); }
        } //อัปเดต moveMinion() ให้รองรับ 6 ทิศทางของ Hexagonal Grid
 }}
