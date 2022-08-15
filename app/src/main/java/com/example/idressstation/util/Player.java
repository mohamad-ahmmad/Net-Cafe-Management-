package com.example.idressstation.util;

public class Player {
    private String name,game,ps;

    public Player(String name, String game, String ps) {
        this.name = name;
        this.game = game;
        this.ps = ps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }
}
