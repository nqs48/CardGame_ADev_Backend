package com.sofka.cardgame.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.values.JugadorId;
import com.sofka.cardgame.values.TableroId;

import java.util.Set;

public class TableroCreado extends DomainEvent {

    private final TableroId tableroId;

    private final Set<JugadorId> jugadorIds;

    public TableroCreado(TableroId tableroId, Set<JugadorId> jugadorIds) {
        super("cardgame.tablerocreado");
        this.tableroId = tableroId;
        this.jugadorIds = jugadorIds;
    }

    public TableroId getTableroId() {
        return this.tableroId;
    }

    public Set<JugadorId> getJugadorIds() {
        return this.jugadorIds;
    }
}
