package com.sofka.cardgame.application.handle.materialize;

import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class BoardMaterializeHandle {

    private static final String COLLECTION_GAME = "gameview";

    private static final String COLLECTION_BOARD = "boardview";

    private final ReactiveMongoTemplate template;

    public BoardMaterializeHandle(ReactiveMongoTemplate template) {
        this.template = template;
    }


    private Query getFilterByAggregateId(DomainEvent event) {
        return new Query(
                Criteria.where("_id").is(event.aggregateRootId())
        );
    }
}
