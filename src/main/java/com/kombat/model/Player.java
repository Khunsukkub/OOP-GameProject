package com.kombat.model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<Minion> minions;
    private String id;
    private int budget;


    public Player(String id, String name, int budget) {
        this.name = name;
        this.budget = budget;
        this.minions = new ArrayList<>();  // ✅ ป้องกัน NullPointerException
    }



    public void increaseBudget(int amount) {
        this.budget += amount;
    }

    public void addMinion(Minion minion) {
        minions.add(minion);
    }

    public boolean hasMinionsLeft() {
        return !minions.isEmpty();
    }

    //waiting for fix
    public String getName() {
        return name;
    }
    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {  // เพิ่ม setter หากต้องการอัปเดตงบประมาณ
        this.budget = budget;
    }

    public List<Minion> getMinions() {  // ✅ ตรวจสอบให้แน่ใจว่าเป็น List<Minion>
        return minions;
    }
}
