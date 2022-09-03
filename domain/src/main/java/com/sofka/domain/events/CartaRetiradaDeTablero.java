package com.sofka.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.domain.values.Carta;
import com.sofka.domain.values.JugadorId;
import com.sofka.domain.values.TableroId;

public class CartaRetiradaDeTablero extends DomainEvent {
    public CartaRetiradaDeTablero(TableroId tableroId, JugadorId jugadorId, Carta carta) {
        super("");
    }
}
