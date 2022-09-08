package com.sofka.cardgame.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.values.TableroId;

public class TiempoActualizadoDeTablero extends DomainEvent {
    private final TableroId tableroId;
    private final Integer tiempo;
    public TiempoActualizadoDeTablero(TableroId tableroId, Integer tiempo) {
        super("cardgame.tiempocambiadodeltablero");
        this.tableroId = tableroId;
        this.tiempo = tiempo;
    }

    public TableroId getTableroId() {
        return this.tableroId;
    }

    public Integer getTiempo() {
        return this.tiempo;
    }
}
