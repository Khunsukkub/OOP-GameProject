package com.kombat.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Minion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int hp;
    private int defenseFactor;
    private int row;
    private int col;

    public Minion() {}

    public Minion(String name, int hp, int defenseFactor, int row, int col) {
        this.name = name;
        this.hp = hp;
        this.defenseFactor = defenseFactor;
        this.row = row;
        this.col = col;
    }
}
