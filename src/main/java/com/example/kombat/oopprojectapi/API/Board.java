package com.example.kombat.oopprojectapi.API;

import com.example.kombat.oopprojectapi.model.Controller;
import com.example.kombat.oopprojectapi.model.MainGame;
import com.example.kombat.oopprojectapi.model.Map;
import com.example.kombat.oopprojectapi.request.GameStateDTO;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kombat")
public class Board {

    //กำหนด board ของแต่ละฝั่งว่ามี
    @GetMapping(value = "/MainGame", produces = "application/json")
    public ResponseEntity<GameStateDTO> InitMap() {
        Controller.initializeMap();
        GameStateDTO gameState = new GameStateDTO(
                MainGame.current_turn,
                MainGame.max_turns,
                MainGame.init_budget,
                MainGame.max_budget,
                MainGame.max_spawns,
                MainGame.spawn_lefts,
                MainGame.current_player,
                Map.getInstance().map
        );
        return ResponseEntity.status(HttpStatus.OK).body(gameState);
    }

    @GetMapping("/board/{row}{col}/")
    public ResponseEntity<Void> hexSelected() {
        return null;
    }

}
