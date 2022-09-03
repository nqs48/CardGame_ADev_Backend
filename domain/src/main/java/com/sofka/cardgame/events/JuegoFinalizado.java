package com.sofka.cardgame.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.values.JugadorId;

public class JuegoFinalizado extends DomainEvent {
    private final JugadorId jugadorId;
    private final String alias;

    public JuegoFinalizado(JugadorId jugadorId, String alias) {
        super("cardgame.juegofinalizado");
        this.jugadorId= jugadorId;
        this.alias= alias;
    }
    public JugadorId getJugadorId() {
        return this.jugadorId;
    }

    public String getAlias() {
        return this.alias;
    }

}
