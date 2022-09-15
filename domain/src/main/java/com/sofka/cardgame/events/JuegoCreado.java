package com.sofka.cardgame.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.values.JugadorId;

public class JuegoCreado extends DomainEvent {

    private final JugadorId jugadorPrincipalId;

    public JuegoCreado(JugadorId jugadorPrincipalId) {
        super("cardgame.juegocreado");
        this.jugadorPrincipalId=jugadorPrincipalId;
    }

    public JugadorId getJugadorPrincipalId() {
        return this.jugadorPrincipalId;
    }
}
