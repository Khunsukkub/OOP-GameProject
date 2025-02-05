package com.kombat.controller;


import com.kombat.game.HexDirection;
import com.kombat.game.MultiplayerManager;
import com.kombat.game.GameManager;
import com.kombat.model.Minion;
import com.kombat.model.Player;
import com.kombat.service.IGameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    private final IGameService gameService;
    private final GameManager gameManager;

    public GameController(IGameService gameService, GameManager gameManager) {
        this.gameService = gameService;
        this.gameManager = gameManager;
    }

    @GetMapping("/multiplayerTurn")
    public String executeMultiplayerTurn() {
        gameManager.executeMultiplayerTurn();
        return "✅ Multiplayer Turn Executed!";
    }

    @GetMapping("/start")
    public String startGame() {
        gameService.startGame();
        return "✅ Game Started!";
    }

    @GetMapping("/state")
    public String getGameState() {
        return gameService.getGameState();
    }
    @PostMapping("/game/buyTile")
    public String buyTile(@RequestParam String playerId, @RequestParam int row, @RequestParam int col) {
        Player player = MultiplayerManager.getPlayer(playerId);
        if (player.getBudget() >= 100) {
            board.placeMinion(row, col, null);
            player.setBudget(player.getBudget() - 100);
            return "✅ Tile bought!";
        } else {
            return "❌ Not enough budget!";
        }
    }

    @PostMapping("/game/createMinion")
    public String createMinion(@RequestParam String playerId, @RequestParam int row, @RequestParam int col) {
        Player player = multiplayerManager.getPlayer(playerId);
        if (board.isTileOccupied(row, col)) {
            return "❌ Tile is already occupied!";
        }
        if (player.getBudget() >= 200) {
            Minion minion = new Minion("Warrior", 100, 5, row, col);
            board.placeMinion(row, col, minion);
            player.setBudget(player.getBudget() - 200);
            return "✅ Minion created!";
        } else {
            return "❌ Not enough budget!";
        }
    }
    @PostMapping("/game/moveMinion")
    public String moveMinion(@RequestParam String minionId, @RequestParam HexDirection direction) {
        Minion minion = minionService.getMinionById(minionId);
        if (minion == null) return "❌ Minion not found!";

        minionService.moveMinion(minion, direction);
        return "✅ Minion moved!";
    }
    @PostMapping("/game/selectMinions")
    public String selectMinions(@RequestParam String playerId, @RequestBody List<String> minionTypes) {
        Player player = multiplayerManager.getPlayer(playerId);
        if (player == null) return "❌ Player not found!";

        List<Minion> minions = minionFactory.createMinions(minionTypes);
        player.setMinions(minions);

        return "✅ Minions selected!";
    }
    @PostMapping("/game/startAutoMode")
    public String startAutoMode() {
        List<Minion> allMinions = minionService.getAllMinions();
        autoBattleManager.startAutoBattle(allMinions);
        return "✅ Auto Mode Started!";
    }
}
