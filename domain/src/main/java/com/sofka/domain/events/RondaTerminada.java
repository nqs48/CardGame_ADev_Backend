package com.sofka.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.domain.values.JugadorId;
import com.sofka.domain.values.TableroId;

import java.util.Set;

public class RondaTerminada extends DomainEvent {
    public RondaTerminada(TableroId tableroId, Set<JugadorId> jugadorIds) {
        super("");
    }
}
