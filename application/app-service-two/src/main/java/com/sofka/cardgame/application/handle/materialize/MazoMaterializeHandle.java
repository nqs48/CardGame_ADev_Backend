package com.sofka.cardgame.application.handle.materialize;

import co.com.sofka.domain.generic.DomainEvent;


import com.sofka.cardgame.events.JugadorAgregado;
import org.bson.Document;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.Instant;


@Configuration
public class MazoMaterializeHandle {

    private static final String COLLECTION_VIEW = "mazoview";

    private final ReactiveMongoTemplate template;

    public MazoMaterializeHandle(ReactiveMongoTemplate template) {
        this.template = template;
    }



    @EventListener
    public void handleJugadorAgregado(JugadorAgregado event) {

        var mazo = event.getMazo();
        var data = new Document();
        var cartas = mazo.value().cartas();
        data.put("juegoId", event.aggregateRootId());
        data.put("cantidad", event.getMazo().value().cantidad());
        data.put("fecha", Instant.now());
        data.put("jugadorId", event.getIdentity().value());

        data.put("cartas", cartas);
        template.save(data, COLLECTION_VIEW).block();

    }

    private Query getFilterByAggregateId(DomainEvent event) {
        return new Query(
                Criteria.where("_id").is(event.aggregateRootId())
        );
    }
}
