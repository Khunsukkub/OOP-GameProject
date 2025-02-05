package com.Kombat.controller;

import com.Kombat.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private IGameService gameService;

    @PostMapping("/start")
    public String startGame() {
        gameService.startGame();
        return "Game Started!";
    }
}
