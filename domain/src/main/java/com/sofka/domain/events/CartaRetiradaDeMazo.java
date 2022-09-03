package com.sofka.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.domain.values.Carta;
import com.sofka.domain.values.JugadorId;

public class CartaRetiradaDeMazo extends DomainEvent {
    public CartaRetiradaDeMazo(JugadorId jugadorId, Carta carta) {
        super("");
    }
}
