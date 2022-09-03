package com.sofka.cardgame.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.values.Carta;
import com.sofka.cardgame.values.JugadorId;
import com.sofka.cardgame.values.TableroId;

public class CartaRetiradaDeTablero extends DomainEvent {

    private final TableroId tableroId;

    private final JugadorId jugadorId;

    private final Carta carta;
    public CartaRetiradaDeTablero(TableroId tableroId, JugadorId jugadorId, Carta carta) {
        super("cardgame.cartaretiradadetablero");
        this.tableroId= tableroId;
        this.jugadorId= jugadorId;
        this.carta=carta;

    }

    public TableroId getTableroId(){
        return this.tableroId;
    }

    public JugadorId getJugadorId(){
        return this.jugadorId;
    }

    public Carta getCarta() {
        return this.carta;
    }
}
