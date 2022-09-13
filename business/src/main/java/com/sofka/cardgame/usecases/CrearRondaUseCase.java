package com.sofka.cardgame.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.Juego;
import com.sofka.cardgame.commands.CrearRondaCommand;
import com.sofka.cardgame.events.RondaTerminada;
import com.sofka.cardgame.gateway.JuegoDomainEventRepository;
import com.sofka.cardgame.values.JuegoId;
import com.sofka.cardgame.values.JugadorId;
import com.sofka.cardgame.values.Ronda;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CrearRondaUseCase extends UseCaseForEvent<RondaTerminada> {

    private final JuegoDomainEventRepository repository;

    public CrearRondaUseCase(JuegoDomainEventRepository repository) {
        this.repository = repository;
    }

    public Integer TIEMPO= 60;

    @Override
    public Flux<DomainEvent> apply(Mono<RondaTerminada> rondaTerminada) {
        return rondaTerminada.flatMapMany((event) -> repository
                .obtenerEventosPor(event.aggregateRootId())
                .collectList()
                .flatMapIterable(events -> {
                    var juego = Juego.from(JuegoId.of(event.aggregateRootId()), events);
                    var jugadores = new HashSet<>(event.getJugadorIds());
                    var ronda = juego.ronda();
                    if(Objects.isNull(ronda)){
                        throw new IllegalArgumentException("Debe existir la primera ronda");
                    }
                    juego.crearRonda(ronda.incrementarRonda(jugadores), TIEMPO);
                    return juego.getUncommittedChanges();
                }));
    }

}

