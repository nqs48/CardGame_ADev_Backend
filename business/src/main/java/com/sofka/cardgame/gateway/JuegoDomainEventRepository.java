package com.sofka.cardgame.gateway;

import co.com.sofka.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;

public interface JuegoDomainEventRepository{

    Flux<DomainEvent> obtenerEventosPor(String id);
}
