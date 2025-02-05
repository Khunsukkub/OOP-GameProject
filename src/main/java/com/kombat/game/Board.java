package com.kombat.game;

import com.kombat.model.Minion;
import java.util.HashMap;
import java.util.Map;

//**กำหนดพิกัด (row, col) ตามรูปแบบ Hexagonal Grid และเช็คพื้นที่ว่าง

public class Board {
    private static final int SIZE = 8;
    private final Map<String, Minion> grid = new HashMap<>();

    public boolean isTileOccupied(int row, int col) {
        return grid.containsKey(row + "," + col);
    }

    public void placeMinion(int row, int col, Minion minion) {
        grid.put(row + "," + col, minion);
    }

    public Minion getMinionAt(int row, int col) {
        return grid.get(row + "," + col);
    }
}
