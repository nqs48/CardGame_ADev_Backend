package com.sofka.cardgame.application.adapters.repo;

import co.com.sofka.domain.generic.DomainEvent;

public class MongoJuegoDomainEventRepository implements JuegoDomainEventRepository {

    private final EventStoreRepository repository;

    public MongoJuegoDomainEventRepository(EventStoreRepository repository) {
        this.repository = repository;
    }


    @Override
    public Flux<DomainEvent> obtenerEventosPor(String id) {
        return repository.getEventsBy("game", id);
    }
}
