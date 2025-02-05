package com.kombat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo3ApplicationTests {

    @Test
    void contextLoads() {
        // ✅ ตรวจสอบว่า Spring Boot โหลด Context ได้
        System.out.println("✅ Spring Boot Application Context Loaded Successfully!");
    }
}
