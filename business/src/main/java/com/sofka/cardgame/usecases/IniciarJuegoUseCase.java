package com.sofka.cardgame.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.Juego;
import com.sofka.cardgame.commands.IniciarJuegoCommand;
import com.sofka.cardgame.gateway.JuegoDomainEventRepository;
import com.sofka.cardgame.values.JuegoId;
import com.sofka.cardgame.values.Ronda;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class IniciarJuegoUseCase extends UseCaseForCommand<IniciarJuegoCommand> {

    private final JuegoDomainEventRepository repository;

    public final Integer TIEMPO= 80;

    public IniciarJuegoUseCase(JuegoDomainEventRepository repository){
        this.repository = repository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<IniciarJuegoCommand> iniciarJuegoCommand) {
        return iniciarJuegoCommand.flatMapMany((command) -> repository
                .obtenerEventosPor(command.getJuegoId())
                .collectList()
                .flatMapIterable(events -> {
                    var juego = Juego.from(JuegoId.of(command.getJuegoId()), events);
                    juego.crearTablero();
                    juego.crearRonda(new Ronda(juego.jugadores().keySet(),1),  TIEMPO);
                    return juego.getUncommittedChanges();
                }));
    }
}
