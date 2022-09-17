package com.sofka.cardgame.commands;

import co.com.sofka.domain.generic.Command;
import com.sofka.cardgame.values.JugadorId;

public class AgregarJugadorAdicionalCommand extends Command {

    private String juegoId;
    private String jugadorId;
    private String alias;

    public AgregarJugadorAdicionalCommand() {
    }

    public AgregarJugadorAdicionalCommand(String jugadorId, String alias) {
        this.jugadorId = jugadorId;
        this.alias = alias;
    }

    public String getjuegoId() {
        return juegoId;
    }

    public String getJugadorId() {
        return jugadorId;
    }

    public String getAlias() {
        return alias;
    }
}
