package com.Kombat.Strategy;

import java.util.*;
import java.util.regex.*;

public class StrategyParser {

    private Map<String, Integer> variables = new HashMap<>();
    private Queue<String> commands = new LinkedList<>();

    public StrategyParser(String strategyCode) {
        parseStrategy(strategyCode);
    }

    private void parseStrategy(String code) {
        // ลบคอมเมนต์ออก
        code = code.replaceAll("#.*", "").trim();

        // ใช้ regex แยกคำสั่ง
        Pattern pattern = Pattern.compile(
                "(\\w+)\\s*=\\s*([^;]+)|" +  // Assignment (ตัวแปร)
                        "move\\s+(\\w+)|" +           // Move (เคลื่อนที่)
                        "shoot\\s+(\\w+)\\s+(\\d+)|" +// Shoot (โจมตี)
                        "if\\s*\\(([^)]+)\\)\\s*then\\s*([^}]+)\\s*else\\s*([^}]+)|" + // If-Else
                        "while\\s*\\(([^)]+)\\)\\s*\\{([^}]+)\\}" // While-Loop
        );

        Matcher matcher = pattern.matcher(code);

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                commands.add("ASSIGN " + matcher.group(1) + " " + matcher.group(2));  // x = 5
            } else if (matcher.group(3) != null) {
                commands.add("MOVE " + matcher.group(3));  // move up
            } else if (matcher.group(4) != null) {
                commands.add("SHOOT " + matcher.group(4) + " " + matcher.group(5));  // shoot right 10
            } else if (matcher.group(6) != null) {
                commands.add("IF " + matcher.group(6) + " THEN " + matcher.group(7) + " ELSE " + matcher.group(8)); // If-Else
            } else if (matcher.group(9) != null) {
                commands.add("WHILE " + matcher.group(9) + " DO " + matcher.group(10)); // While-Loop
            }
        }
    }

    public void execute() {
        while (!commands.isEmpty()) {
            executeCommand(commands.poll());
        }
    }

    private void executeCommand(String command) {
        String[] parts = command.split(" ");

        switch (parts[0]) {
            case "ASSIGN":
                variables.put(parts[1], evaluateExpression(parts[2]));
                break;
            case "MOVE":
                System.out.println("Moving " + parts[1]);
                break;
            case "SHOOT":
                System.out.println("Shooting " + parts[1] + " with power " + parts[2]);
                break;
            case "IF":
                boolean condition = evaluateExpression(parts[1]) > 0;
                executeCommand(condition ? parts[3] : parts[5]);
                break;
            case "WHILE":
                while (evaluateExpression(parts[1]) > 0) {
                    executeCommand(parts[3]);
                }
                break;
        }
    }

    private int evaluateExpression(String expr) {
        try {
            return Integer.parseInt(expr);
        } catch (NumberFormatException e) {
            return variables.getOrDefault(expr, 0);
        }
    }

    public static void main(String[] args) {
        String strategy = """
            x = 3
            while (x > 0) { 
                move up
                x = x - 1
            }
            if (budget - 100) then move left else move right
            """;

        StrategyParser parser = new StrategyParser(strategy);
        parser.execute();
    }
}
