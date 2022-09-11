package com.sofka.cardgame.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.Juego;
import com.sofka.cardgame.commands.PonerCartaEnTableroCommand;
import com.sofka.cardgame.gateway.JuegoDomainEventRepository;
import com.sofka.cardgame.values.Carta;
import com.sofka.cardgame.values.JuegoId;
import com.sofka.cardgame.values.JugadorId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.logging.Logger;

public class PonerCartaEnTableroUseCase extends UseCaseForCommand<PonerCartaEnTableroCommand>{

    private final Logger log = Logger.getLogger(PonerCartaEnTableroUseCase.class.getCanonicalName());
    private final JuegoDomainEventRepository repository;

    private final Integer cartLimitTurn=1;

    public PonerCartaEnTableroUseCase(JuegoDomainEventRepository repository) {
        this.repository = repository;
    }


    @Override
    public Flux<DomainEvent> apply(Mono<PonerCartaEnTableroCommand> ponerCartaEnTablero) {
        return ponerCartaEnTablero.flatMapMany((command) -> repository
                .obtenerEventosPor(command.getJuegoId())
                .collectList()
                .flatMapIterable(events -> {
                    var juego = Juego.from(JuegoId.of(command.getJuegoId()), events);
                    var tableroId = juego.tablero().identity();
                    var jugadorId = JugadorId.of(command.getJugadorId());
                    var cartasDelJugador = juego.jugadores().get(jugadorId).mazo().value().cartas();
                    var cartaSeleccionado = seleccionarCarta(command.getCartaId(), cartasDelJugador);

                    var cantidad = (long) juego.tablero().partida()
                            .get(jugadorId).size();
                    if(cantidad > cartLimitTurn) {
                        throw new IllegalArgumentException("En tu turno solo puedes poner una carta en el tablero");
                    }
                    juego.ponerCartaEnTablero(tableroId, jugadorId, cartaSeleccionado);
                    return juego.getUncommittedChanges();
                }));
    }

    private Carta seleccionarCarta(String cartaId, Set<Carta> cartasDelJugador) {
        return cartasDelJugador
                .stream()
                .filter(c -> c.value().cartaId().value().equals(cartaId))
                .findFirst()
                .orElseThrow();
    }
}
