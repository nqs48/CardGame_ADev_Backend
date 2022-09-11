package com.sofka.cardgame.application.adapters.service;


import com.sofka.cardgame.gateway.ListaDeCartaService;
import com.sofka.cardgame.gateway.model.CartaMaestra;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class DataConsultarCartaMaestraService implements ListaDeCartaService {

    private final ReactiveMongoTemplate template;

    public DataConsultarCartaMaestraService(ReactiveMongoTemplate template) {
        this.template = template;
    }

    @Override
    public Flux<CartaMaestra> obtenerCartasDeMarvel() {
        return template.findAll(CartaMaestra.class, "cards");
    }
}
