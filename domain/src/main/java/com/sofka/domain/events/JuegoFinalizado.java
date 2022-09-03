package com.sofka.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.domain.values.JugadorId;

public class JuegoFinalizado extends DomainEvent {
    public JuegoFinalizado(JugadorId jugadorId, String alias) {
        super("");
    }
}
