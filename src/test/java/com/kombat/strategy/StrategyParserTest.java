package com.kombat.strategy;

import com.kombat.model.Minion;
import com.kombat.service.IMinionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class StrategyParserTest {

    private IMinionService minionService;
    private Minion minion;
    private StrategyParser strategyParser;

    @BeforeEach
    void setUp() {
        minionService = mock(IMinionService.class); // ใช้ Mockito Mock Service
        minion = new Minion("Warrior", 100, 5, 3, 3);
    }

    @Test
    void testMoveCommand() {
        String strategy = """
            move up
            move left
            """;
        strategyParser = new StrategyParser(minion, minionService, strategy);
        strategyParser.execute(List.of());

        // ตรวจสอบว่า `moveMinion()` ถูกเรียกตามคำสั่ง
        verify(minionService, times(1)).moveMinion(minion, "up");
        verify(minionService, times(1)).moveMinion(minion, "left");
    }

    @Test
    void testShootCommand() {
        Minion enemy = new Minion("Enemy", 50, 2, 4, 3);
        List<Minion> enemies = List.of(enemy);

        String strategy = """
            shoot right 10
            """;
        strategyParser = new StrategyParser(minion, minionService, strategy);
        strategyParser.execute(enemies);

        // ตรวจสอบว่า `attackMinion()` ถูกเรียก
        verify(minionService, times(1)).attackMinion(minion, enemy, 10);
    }

    @Test
    void testStrategyExecutionOrder() {
        Minion enemy = new Minion("Enemy", 50, 2, 4, 3);
        List<Minion> enemies = List.of(enemy);

        String strategy = """
            move up
            shoot down 5
            move right
            """;
        strategyParser = new StrategyParser(minion, minionService, strategy);
        strategyParser.execute(enemies);

        // ตรวจสอบว่า executeCommand ทำงานตามลำดับ
        verify(minionService, times(1)).moveMinion(minion, "up");
        verify(minionService, times(1)).attackMinion(minion, enemy, 5);
        verify(minionService, times(1)).moveMinion(minion, "right");
    }
}
//ช้ @BeforeEach → ตั้งค่า minion และ minionService ก่อนการทดสอบแต่ละเคส
//✅ ใช้ Mockito Mock IMinionService → เพื่อจำลองการเคลื่อนที่และโจมตี
//✅ มี 3 เคสทดสอบหลัก:
//
//testMoveCommand() → ตรวจสอบคำสั่งเคลื่อนที่
//testShootCommand() → ตรวจสอบคำสั่งยิง
//testStrategyExecutionOrder() → ตรวจสอบลำดับการทำงานของคำสั่ง

