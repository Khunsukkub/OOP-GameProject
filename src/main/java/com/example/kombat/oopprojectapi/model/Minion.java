package com.example.kombat.oopprojectapi.model;

public class Minion {
    public String name;
    public double init_hp;
    private double hp;
    private double base_damage = 1;
    private double defense_factor;
    private String strategy;
    public int spawn_cost;
    private Player minion_owner;

    private void doStrategy(String strategy) {
        //new MinionStrategyParser(strategy);
    }

    public Minion(String name, double init_hp , double defense_factor , int spawn_cost , String strategy) {
        this.name = name;
        this.init_hp = init_hp;
        this.defense_factor = defense_factor;
        this.spawn_cost = spawn_cost;
        this.strategy = strategy;
    }

    public void setMinionOwner(Player player) {
        this.minion_owner = player;
    }

    public double getHP() {
        return hp;
    }

}
