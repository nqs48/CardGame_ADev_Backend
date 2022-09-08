package com.sofka.cardgame.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.values.Carta;
import com.sofka.cardgame.values.JugadorId;
import com.sofka.cardgame.values.TableroId;

public class CartaPuestaEnTablero extends DomainEvent {
    private final TableroId tableroId;
    private final JugadorId jugadorId;
    private final Carta carta;

    public CartaPuestaEnTablero(TableroId tableroId, JugadorId jugadorId, Carta carta) {
        super("cardgame.ponercartaentablero");
        this.tableroId= tableroId;
        this.jugadorId = jugadorId;
        this.carta = carta;
    }

    public TableroId getTableroId() {
        return tableroId;
    }

    public JugadorId getJugadorId() {
        return jugadorId;
    }

    public Carta getCarta() {
        return carta;
    }
}
