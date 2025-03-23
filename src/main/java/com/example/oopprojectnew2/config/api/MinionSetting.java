package com.example.oopprojectnew2.config.api;

import exception.BaseException;
import exception.MinionException;
import model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game/api")
public class MinionSetting {

    @PostMapping("/player/minions")
    public ResponseEntity<String> receiveMinions(@RequestBody MinionBatchRequest request) {
        String playerNumber = request.getPlayer();
        List<MinionDTO> minions = request.getMinions();

        System.out.println("📥 Player " + playerNumber + " is submitting " + minions.size() + " minions");

        try {
            int playerNum = Integer.parseInt(playerNumber);
            Player player = GameState.getInstance().getPlayerByNumber(playerNum);

            for (MinionDTO m : minions) {
                System.out.println("✅ Minion: " + m.getName() +
                        " | DEF: " + m.getDefense() +
                        " | Color: " + m.getColor() +
                        " | Code: " + m.getCode() +
                        " | Cost: " + m.getCost());

                // ปรับให้ตรงกับ constructor ของ Minion
                Minion minion = new Minion(
                        m.getName(),
                        20.0, // ค่า HP คงที่
                        m.getDefense(),
                        m.getCost(),
                        m.getCode() // ใช้ code เป็น strategy
                );
                player.addMinion(minion);
            }

            return ResponseEntity.ok("✅ Minions received successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("❌ Invalid player number or error: " + e.getMessage());
        }
    }

    @PostMapping("/NumbersMinionSetting")
    public ResponseEntity<Void> NumbersMinionSetting(@RequestBody int NumberOfMinion) throws BaseException {
        MainGame.setNumberOfMinion(NumberOfMinion);
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "/MinionSetting")
                .build();
    }

    @PostMapping("/MinionSetting/{id}")
    public ResponseEntity<Void> MinionSetting(
            @PathVariable String id,
            @RequestParam String minionName,
            @RequestParam String HP,
            @RequestParam String DEF,
            @RequestParam String spawnCost,
            @RequestParam String strategy,
            @RequestParam String playerNumber) throws BaseException {

        if (minionName.isEmpty()) throw MinionException.nameNull();
        if (HP.isEmpty()) throw MinionException.hpNull();
        if (DEF.isEmpty()) throw MinionException.defNull();

        int currentId = Integer.parseInt(id);
        int totalMinions = Integer.parseInt(MainGame.getNumberOfMinion());
        int nextId = (currentId % totalMinions) + 1;

        // ตรงนี้ยังไม่เก็บ minion แต่สามารถต่อยอดได้
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "/game/api/MinionSetting/" + nextId)
                .build();
    }

    // ===== DTO ภายใน =====
    public static class MinionBatchRequest {
        private String player;
        private List<MinionDTO> minions;

        public String getPlayer() { return player; }
        public void setPlayer(String player) { this.player = player; }

        public List<MinionDTO> getMinions() { return minions; }
        public void setMinions(List<MinionDTO> minions) { this.minions = minions; }
    }

    public static class MinionDTO {
        private String name;
        private String color;
        private int defense;
        private String code;
        private int cost;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getColor() { return color; }
        public void setColor(String color) { this.color = color; }

        public int getDefense() { return defense; }
        public void setDefense(int defense) { this.defense = defense; }

        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }

        public int getCost() { return cost; }
        public void setCost(int cost) { this.cost = cost; }
    }
}
