package com.kombat.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttackRequest {
    private String attacker;
    private String target;
    private int attackPower;

    public AttackRequest() {}  // ✅ ต้องมี Constructor เปล่า

    public AttackRequest(String attacker, String target, int attackPower) {
        this.attacker = attacker;
        this.target = target;
        this.attackPower = attackPower;
    }
}
//แก้ไขโดยเพิ่ม @Getter และ @Setter เพื่อให้ใช้งาน request.getAttacker() ได้