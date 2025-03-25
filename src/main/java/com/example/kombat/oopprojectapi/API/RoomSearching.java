package com.example.kombat.oopprojectapi.API;

import com.example.kombat.oopprojectapi.exception.BaseException;
import com.example.kombat.oopprojectapi.exception.RoomSearchingException;
import com.example.kombat.oopprojectapi.model.GameState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/kombat")
public class RoomSearching {

    @PostMapping("/roomSearching")
    public ResponseEntity<Void> roomSearching(@RequestBody String input) throws BaseException {

        if (input == null) throw RoomSearchingException.idNull();
        if (input == "1234") throw RoomSearchingException.notFound();

        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "/kombat/createPlayer")
                .build();
    }

    @GetMapping("/player-count")
    public ResponseEntity<Integer> playerCount() {
        return ResponseEntity.ok(CreatePlayer.playerList.size());
    }
}
