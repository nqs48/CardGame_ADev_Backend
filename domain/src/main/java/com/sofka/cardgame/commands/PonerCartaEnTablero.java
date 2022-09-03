package com.sofka.cardgame.commands;

import co.com.sofka.domain.generic.Command;

public class PonerCartaEnTablero extends Command {

    private String juegoId;

    public String getJuegoId() {
        return juegoId;
    }

    public void setJuegoId(String juegoId) {
        this.juegoId = juegoId;
    }
}
