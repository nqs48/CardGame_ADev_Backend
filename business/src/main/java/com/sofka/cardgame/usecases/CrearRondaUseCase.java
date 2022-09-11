package com.sofka.cardgame.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.Juego;
import com.sofka.cardgame.commands.CrearRondaCommand;
import com.sofka.cardgame.gateway.JuegoDomainEventRepository;
import com.sofka.cardgame.values.JuegoId;
import com.sofka.cardgame.values.JugadorId;
import com.sofka.cardgame.values.Ronda;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CrearRondaUseCase extends UseCaseForCommand<CrearRondaCommand> {

    private final JuegoDomainEventRepository repository;

    public CrearRondaUseCase(JuegoDomainEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<CrearRondaCommand> crearRondaCommand) {
        return crearRondaCommand.flatMapMany((command) ->  repository
                .obtenerEventosPor(command.getJuegoId())
                .collectList()
                .flatMapIterable(events -> {
                    var juego = Juego.from(JuegoId.of(command.getJuegoId()), events);
                    var jugadores = command.getJugadores().stream()
                            .map(JugadorId::of).collect(Collectors.toSet());

                    if (juego.ronda() == null) {
                        juego.crearRonda(new Ronda(jugadores,1), command.getTiempo());
                    }
                    juego.ronda().incrementarRonda(jugadores);
                    return juego.getUncommittedChanges();
                }));
    }

}

