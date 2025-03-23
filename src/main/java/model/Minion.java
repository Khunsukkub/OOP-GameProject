package model;

public class Minion {
    public String name;
    public String color;      // ✅ เพิ่ม
    public String code;       // ✅ เพิ่ม
    public int spawn_cost;
    public double init_hp;
    private double hp;
    private double base_damage = 1;
    private double defense_factor;
    private String strategy;

    public Minion(String name, double init_hp , double defense_factor , int spawn_cost , String strategy) {
        this.name = name;
        this.init_hp = init_hp;
        this.defense_factor = defense_factor;
        this.spawn_cost = spawn_cost;
        this.strategy = strategy;
    }


    public double getHP() {
        return hp;
    }
}
