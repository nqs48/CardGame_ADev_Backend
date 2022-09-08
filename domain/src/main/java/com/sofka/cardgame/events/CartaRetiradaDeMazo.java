package com.sofka.cardgame.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.values.Carta;
import com.sofka.cardgame.values.JugadorId;

public class CartaRetiradaDeMazo extends DomainEvent {
    private final JugadorId jugadorId;
    private final Carta carta;

    public CartaRetiradaDeMazo(JugadorId jugadorId, Carta carta) {
        super("cardgame.cartaretiradademazo");
        this.jugadorId= jugadorId;
        this.carta=carta;
    }

    public JugadorId getJugadorId() {
        return jugadorId;
    }
    public Carta getCarta() {
        return carta;
    }
}
