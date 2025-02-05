package com.kombat.controller;

import com.kombat.game.MultiplayerManager;
import com.kombat.model.Player;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
//ให้ผู้เล่นใหม่เข้าร่วมเกมโดยใช้ /multiplayer/join?playerId=123&name=Alice
@RestController
@RequestMapping("/multiplayer")
public class MultiplayerController {

    private final MultiplayerManager multiplayerManager;

    public MultiplayerController(MultiplayerManager multiplayerManager) {
        this.multiplayerManager = multiplayerManager;
    }

    @PostMapping("/join")
    public String joinGame(@RequestParam String playerId, @RequestParam String name) {
        multiplayerManager.addPlayer(playerId, name);
        return "✅ Player " + name + " joined the game!";
    }

    @GetMapping("/players")
    public Collection<Player> getAllPlayers() {
        return multiplayerManager.getAllPlayers();
    }
}
