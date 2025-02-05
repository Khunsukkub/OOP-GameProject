package com.kombat.strategy;

import com.kombat.model.Minion;
import com.kombat.service.IMinionService;

import java.util.*;

public class StrategyExecutor {
    private final Minion minion;
    private final IMinionService minionService;
    private final Queue<String> commands;

    public StrategyExecutor(Minion minion, IMinionService minionService, StrategyParser parser) {
        this.minion = minion;
        this.minionService = minionService;
        this.commands = parser.getCommands();
    }

    public void execute() {
        while (!commands.isEmpty()) {
            executeCommand(commands.poll());
        }
    }

    private void executeCommand(String command) {
        String[] parts = command.split(" ");

        switch (parts[0]) {
            case "MOVE":
                minionService.addMinion(minion); // จำลองการเคลื่อนที่
                break;
            case "SHOOT":
                System.out.println(minion.getName() + " shoots " + parts[1] + " with power " + parts[2]);
                break;
        }
    }
}

//execute() → ดึงคำสั่งจาก StrategyParser มาทำงาน
//✅ executeCommand() → รันคำสั่งที่ได้รับ
//
