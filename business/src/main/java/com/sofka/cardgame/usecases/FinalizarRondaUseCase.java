package com.sofka.cardgame.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.Juego;
import com.sofka.cardgame.commands.FinalizarRondaCommand;
import com.sofka.cardgame.gateway.JuegoDomainEventRepository;
import com.sofka.cardgame.values.Carta;
import com.sofka.cardgame.values.JuegoId;
import com.sofka.cardgame.values.JugadorId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class FinalizarRondaUseCase extends UseCaseForCommand<FinalizarRondaCommand> {

    private final JuegoDomainEventRepository repository;

    public FinalizarRondaUseCase(JuegoDomainEventRepository repository){
        this.repository = repository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<FinalizarRondaCommand> finalizarRondaCommand) {
        return finalizarRondaCommand.flatMapMany((command) -> repository
                .obtenerEventosPor(command.getJuegoId())
                .collectList()
                .flatMapIterable(events -> {

                    var juego = Juego.from(JuegoId.of(command.getJuegoId()), events);
                    TreeMap<Integer, String> partidaOrdenada = new TreeMap<>((t1, t2) -> t2 - t1);
                    Set<Carta> cartasEnTablero = new HashSet<>();
                    juego.tablero().partida().forEach((jugadorId, cartas) -> {
                        cartas.stream()
                                .map(c -> c.value().poder())
                                .reduce(Integer::sum)
                                .ifPresent(puntos -> {
                                    partidaOrdenada.put(puntos, jugadorId.value());
                                    cartasEnTablero.addAll(cartas);
                                });

                    });

                    var competidores = partidaOrdenada.values()
                            .stream()
                            .map(JugadorId::of)
                            .collect(Collectors.toSet());
                    var partida =  partidaOrdenada.firstEntry();
                    var ganadorId = partida.getValue();
                    var puntos = partida.getKey();

                    juego.asignarCartasAGanador(JugadorId.of(ganadorId), puntos, cartasEnTablero);
                    juego.finalizarRonda(juego.tablero().identity(), competidores);

                    return juego.getUncommittedChanges();
                }));
    }
}
