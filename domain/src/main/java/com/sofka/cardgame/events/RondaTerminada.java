package com.sofka.cardgame.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.values.JugadorId;
import com.sofka.cardgame.values.TableroId;

import java.util.Set;

public class RondaTerminada extends DomainEvent {

    private final TableroId tableroId;

    private final Set<JugadorId> jugadorIds;

    public RondaTerminada(TableroId tableroId, Set<JugadorId> jugadorIds) {
        super("cardgame.rondaterminada");
        this.tableroId= tableroId;
        this.jugadorIds=jugadorIds;
    }

    public TableroId getTableroId() {
        return this.tableroId;
    }

    public Set<JugadorId> getJugadorIds() {
        return this.jugadorIds;
    }
}
