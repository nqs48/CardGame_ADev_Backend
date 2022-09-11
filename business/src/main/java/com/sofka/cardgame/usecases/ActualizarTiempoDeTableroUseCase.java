package com.sofka.cardgame.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.Juego;
import com.sofka.cardgame.commands.ActualizarTiempoDeTableroCommand;
import com.sofka.cardgame.commands.CrearRondaCommand;
import com.sofka.cardgame.gateway.JuegoDomainEventRepository;
import com.sofka.cardgame.values.JuegoId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ActualizarTiempoDeTableroUseCase extends UseCaseForCommand<ActualizarTiempoDeTableroCommand>{


    private final JuegoDomainEventRepository repository;

    public ActualizarTiempoDeTableroUseCase(JuegoDomainEventRepository repository) {
        this.repository = repository;
    }
    @Override
    public Flux<DomainEvent> apply(Mono<ActualizarTiempoDeTableroCommand> actualizarTiempoDeTableroCommandMono) {
        return actualizarTiempoDeTableroCommandMono.flatMapMany(comando ->
                repository.obtenerEventosPor(comando.getJuegoId().value())
                        .collectList()
                        .flatMapIterable(evento -> {
                            var juego = Juego
                                    .from(JuegoId.of(comando.getJuegoId().value()), evento);

                            juego.actualizarTiempoDeTablero(comando.getTableroId(), comando.getTiempo());
                            return juego.getUncommittedChanges();
                        }));
    }


}
