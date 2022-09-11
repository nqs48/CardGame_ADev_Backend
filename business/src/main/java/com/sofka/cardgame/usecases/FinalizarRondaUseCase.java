package com.sofka.cardgame.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.Juego;
import com.sofka.cardgame.commands.FinalizarRondaCommand;
import com.sofka.cardgame.gateway.JuegoDomainEventRepository;
import com.sofka.cardgame.values.JuegoId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FinalizarRondaUseCase extends UseCaseForCommand<FinalizarRondaCommand> {

    private final JuegoDomainEventRepository repository;

    public FinalizarRondaUseCase(JuegoDomainEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<FinalizarRondaCommand> finalizarRondaCommandMono) {
        return finalizarRondaCommandMono.flatMapMany(comando->
                repository.obtenerEventosPor(comando.getJuegoId())
                        .collectList()
                        .flatMapIterable(event->{
                            var juego = Juego.from(JuegoId.of(comando.getJuegoId()),event);
                            return juego.getUncommittedChanges();
                        })

        );
    }
}
