package com.sofka.cardgame.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.Juego;
import com.sofka.cardgame.commands.FinalizarRondaCommand;
import com.sofka.cardgame.events.RondaIniciada;
import com.sofka.cardgame.gateway.JuegoDomainEventRepository;
import com.sofka.cardgame.values.JuegoId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class IniciarCuentaRegresivaUseCase extends UseCaseForEvent<RondaIniciada>{

    private final JuegoDomainEventRepository repository;
    private final FinalizarRondaUseCase finalizarRondaUseCase;

    public IniciarCuentaRegresivaUseCase(JuegoDomainEventRepository repository, FinalizarRondaUseCase finalizarRondaUseCase){
        this.repository = repository;
        this.finalizarRondaUseCase = finalizarRondaUseCase;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<RondaIniciada> rondaIniciada) {
        AtomicInteger acumulador = new AtomicInteger(0);
        var finalizarCommand = new FinalizarRondaCommand();
        return rondaIniciada.flatMapMany((event) -> repository
                .obtenerEventosPor(event.aggregateRootId())
                .collectList()
                .flatMapMany(events -> {
                    var juego = Juego.from(JuegoId.of(event.aggregateRootId()), events);
                    finalizarCommand.setJuegoId(event.aggregateRootId());
                    var tiempo = juego.tablero().tiempo();
                    var tableroId = juego.tablero().identity();
                    return Flux.interval(Duration.ofSeconds(1))
                            .onBackpressureBuffer(1)
                            .take(tiempo)
                            .flatMap(t -> {
                                juego.markChangesAsCommitted();
                                var nuevoTiempo = tiempo - acumulador.getAndIncrement();
                                juego.actualizarTiempoDeTablero(tableroId, nuevoTiempo );

                                if(nuevoTiempo == 1){
                                    return finalizarRondaUseCase.apply(Mono.just(finalizarCommand))
                                            .mergeWith(Flux.fromIterable(juego.getUncommittedChanges()));
                                }
                                return Flux.fromIterable(juego.getUncommittedChanges());
                            });
                }));
    }
}
