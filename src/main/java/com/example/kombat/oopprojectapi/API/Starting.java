package com.example.kombat.oopprojectapi.API;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kombat")
public class Starting {

    @GetMapping("/starting") // เอา /kombat/starting ไปฝังไว้ใน ปุ่ม start
    public ResponseEntity<Void> start() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "/kombat/gameMode") //มันจะส่งไปที่ local บลาๆ /kombat/gameMode
                .build();
    }
}
