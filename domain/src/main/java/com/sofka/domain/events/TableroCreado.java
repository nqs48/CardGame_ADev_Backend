package com.sofka.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.domain.values.JugadorId;
import com.sofka.domain.values.TableroId;

import java.util.Set;

public class TableroCreado extends DomainEvent {
    public TableroCreado(TableroId id, Set<JugadorId> jugadorIds) {
        super("");
    }
}
