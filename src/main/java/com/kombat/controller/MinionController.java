package com.kombat.controller;

import com.kombat.model.Minion;
import com.kombat.service.IMinionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/minion")
public class MinionController {

    private final IMinionService minionService;

    public MinionController(IMinionService minionService) {
        this.minionService = minionService;
    }

    @PostMapping("/add")
    public String addMinion(@RequestBody Minion minion) {
        minionService.addMinion(minion);
        return "✅ Minion added!";
    }

    @GetMapping("/list")
    public List<Minion> getAllMinions() {
        return minionService.getAllMinions();
    }
}
