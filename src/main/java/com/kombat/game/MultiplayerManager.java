package com.kombat.game;

import com.kombat.model.Player;
import org.springframework.stereotype.Service;
import java.util.*;
// เก็บข้อมูลของผู้เล่นที่เข้าร่วมเกม
// รองรับการเพิ่มผู้เล่นใหม่


@Service
public class MultiplayerManager {

    private final Map<String, Player> players = new HashMap<>();

    public void addPlayer(String playerId, String name) {
        if (!players.containsKey(playerId)) {
            players.put(playerId, new Player(playerId, name, 100));
        }
    }

    public Player getPlayer(String playerId) {  // ✅ เอา static ออก
        return players.get(playerId);
    }

    public Collection<Player> getAllPlayers() {
        return players.values();
    }
}
