package com.sofka.cardgame.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.Juego;
import com.sofka.cardgame.commands.IniciarRondaCommand;
import com.sofka.cardgame.values.JuegoId;
import com.sofka.cardgame.values.Ronda;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class IniciarRondaUseCase extends UseCaseForCommand<IniciarRondaCommand> {


    @Override
    public Flux<DomainEvent> apply(Mono<IniciarRondaCommand> iniciarRondaCommandMono) {
        return null;
    }
}

