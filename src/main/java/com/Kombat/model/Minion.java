package com.Kombat.model;

public class Minion {
    private String name;
    private int hp;
    private int defenseFactor;
    private int row;
    private int col;

    public Minion(String name, int hp, int defenseFactor, int row, int col) {
        this.name = name;
        this.hp = hp;
        this.defenseFactor = defenseFactor;
        this.row = row;
        this.col = col;
    }

    public void move(String direction) {
        // Logic สำหรับการเคลื่อนที่
    }

    public void attack(Minion target, int attackPower) {
        int damage = Math.max(1, attackPower - target.defenseFactor);
        target.hp = Math.max(0, target.hp - damage);
    }

    public boolean isAlive() {
        return hp > 0;
    }

    //wait for fix
    public boolean getName() {
        return this.name != null;
    }
}
