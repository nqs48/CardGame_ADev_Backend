package com.sofka.cardgame.application.adapters.repo;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.generic.Query;
import com.sofka.cardgame.application.generic.EventStoreRepository;
import com.sofka.cardgame.application.generic.StoredEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

public class MongoEventStoreRepository implements EventStoreRepository {

    private final ReactiveMongoTemplate template;

    private final StoredEvent.EventSerializer eventSerializer;

    public MongoEventStoreRepository(ReactiveMongoTemplate template, StoredEvent.EventSerializer eventSerializer) {
        this.template = template;
        this.eventSerializer = eventSerializer;
    }

    @Override
    public Flux<DomainEvent> getEventsBy(String aggregateName, String aggregateRootId) {
        var query = new Query(Criteria.where("aggregateRootId").is(aggregateRootId));
        return template.find(query, DocumentEventStored.class, aggregateName)
                .sort(Comparator.comparing(event -> event.getStoredEvent().getOccurredOn()))
                .map(storeEvent -> storeEvent.getStoredEvent().deserializeEvent(eventSerializer));
    }


    @Override
    public Mono<Void> saveEvent(String aggregateName, String aggregateRootId, StoredEvent storedEvent) {
        var eventStored = new DocumentEventStored();
        eventStored.setAggregateRootId(aggregateRootId);
        eventStored.setStoredEvent(storedEvent);
        return template.save(eventStored, aggregateName).then();
    }
}
