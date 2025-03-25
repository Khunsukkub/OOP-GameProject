// MinionRequest.java
package com.example.kombat.oopprojectapi.request;

import lombok.Getter;

@Getter
public class MMinionRequest {
    private String minionName;
    private double hp;
    private double def;
    private int spawnCost;
    private String strategy;

    public void MinionRequest(String minionName, double hp, double def, int spawnCost, String strategy) {
        this.minionName = minionName;
        this.hp = hp;
        this.def = def;
        this.spawnCost = spawnCost;
        this.strategy = strategy;
    }
}