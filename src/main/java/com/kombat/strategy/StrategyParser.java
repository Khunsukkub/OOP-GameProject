package com.kombat.strategy;

import com.kombat.model.Minion;
import com.kombat.service.IMinionService;
import java.util.*;
//ใช้ BattleManager ในการ ค้นหาศัตรูและโจมตี
//executeCommand("SHOOT") → ถ้าศัตรูอยู่ในระยะ ก็ให้ยิง
//รองรับการตรวจจับศัตรูที่ใกล้ที่สุด อัตโนมัติ
public class StrategyParser {

    private final Minion minion;
    private final IMinionService minionService;
    private final Queue<String> commands = new LinkedList<>();

    public StrategyParser(Minion minion, IMinionService minionService, String strategyCode) {
        this.minion = minion;
        this.minionService = minionService;
        parseStrategy(strategyCode);
    }

    private void parseStrategy(String strategyCode) {
    }

    public void execute(List<Minion> enemies) {
        while (!commands.isEmpty()) {
            executeCommand(commands.poll(), enemies);
        }
    }

    private void executeCommand(String command, List<Minion> enemies) {
        String[] parts = command.split(" ");

        switch (parts[0].toUpperCase()) {
            case "MOVE":
                minionService.moveMinion(minion, parts[1]);
                break;
            case "SHOOT":
                int attackPower = Integer.parseInt(parts[2]);
                if (!enemies.isEmpty()) {
                    minionService.attackMinion(minion, enemies.get(0), attackPower);
                }
                break;
        }
    }

    //wating for fix
    public Queue<String> getCommands() {return commands;}
}



//ใช้ BattleManager ในการ ค้นหาศัตรูและโจมตี
//executeCommand("SHOOT") → ถ้าศัตรูอยู่ในระยะ ก็ให้ยิง
//รองรับการตรวจจับศัตรูที่ใกล้ที่สุด อัตโนมัติ