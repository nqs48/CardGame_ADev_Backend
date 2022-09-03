package com.sofka.cardgame.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.values.JugadorId;
import com.sofka.cardgame.values.TableroId;

import java.util.Set;

public class TableroCreado extends DomainEvent {

    private final TableroId tableroId;

    private final Set<JugadorId> jugadoresId;

    public TableroCreado(TableroId tableroId, Set<JugadorId> jugadoresId) {
        super("cardgame.tablerocreado");
        this.tableroId = tableroId;
        this.jugadoresId = jugadoresId;
    }

    public TableroId getTableroId() {
        return this.tableroId;
    }

    public Set<JugadorId> getJugadoresId() {
        return this.jugadoresId;
    }
}
