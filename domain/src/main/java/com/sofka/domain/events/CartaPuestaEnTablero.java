package com.sofka.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.domain.values.Carta;
import com.sofka.domain.values.JugadorId;
import com.sofka.domain.values.TableroId;

public class CartaPuestaEnTablero extends DomainEvent {
    public CartaPuestaEnTablero(TableroId tableroId, JugadorId jugadorId, Carta carta) {
        super("");
    }
}
