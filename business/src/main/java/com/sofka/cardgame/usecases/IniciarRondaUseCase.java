package com.sofka.cardgame.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.Juego;
import com.sofka.cardgame.commands.IniciarRondaCommand;
import com.sofka.cardgame.gateway.JuegoDomainEventRepository;
import com.sofka.cardgame.values.JuegoId;
import com.sofka.cardgame.values.Ronda;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class IniciarRondaUseCase extends UseCaseForCommand<IniciarRondaCommand> {

    private final JuegoDomainEventRepository repository;

    public IniciarRondaUseCase(JuegoDomainEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<IniciarRondaCommand> iniciarRondaCommand) {
        return iniciarRondaCommand.flatMapMany(comando -> repository.obtenerEventosPor(
                comando.getJuegoId()).collectList().flatMapIterable(evento -> {
            var juego = Juego.from(JuegoId.of(comando.getJuegoId()), evento);
            juego.iniciarRonda();
            return juego.getUncommittedChanges();
        }));
    }
}

