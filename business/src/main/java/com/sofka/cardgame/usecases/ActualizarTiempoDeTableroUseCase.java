package com.sofka.cardgame.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.commands.ActualizarTiempoDeTableroCommand;
import com.sofka.cardgame.commands.CrearRondaCommand;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ActualizarTiempoDeTableroUseCase extends UseCaseForCommand<ActualizarTiempoDeTableroCommand>{

    @Override
    public Flux<DomainEvent> apply(Mono<ActualizarTiempoDeTableroCommand> actualizarTiempoDeTableroCommandMono) {
        return null;
    }
}
