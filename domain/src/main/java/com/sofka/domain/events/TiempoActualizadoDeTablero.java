package com.sofka.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.domain.values.TableroId;

public class TiempoActualizadoDeTablero extends DomainEvent {
    public TiempoActualizadoDeTablero(TableroId tableroId, Integer tiempo) {
        super("");
    }
}
