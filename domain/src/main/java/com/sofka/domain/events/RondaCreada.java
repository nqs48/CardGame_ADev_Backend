package com.sofka.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.domain.values.Ronda;

public class RondaCreada extends DomainEvent {
    public RondaCreada(Ronda ronda, Integer tiempo) {
        super("");
    }
}
