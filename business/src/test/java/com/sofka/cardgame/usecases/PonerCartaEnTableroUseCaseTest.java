package com.sofka.cardgame.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.commands.PonerCartaEnTableroCommand;
import com.sofka.cardgame.events.*;
import com.sofka.cardgame.gateway.JuegoDomainEventRepository;
import com.sofka.cardgame.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PonerCartaEnTableroUseCaseTest {

    @Mock
    private JuegoDomainEventRepository repository;

    @InjectMocks
    private PonerCartaEnTableroUseCase useCase;

    @Test
    void ponerCarta() {
        //arrange
        var command = new PonerCartaEnTableroCommand();
        command.setCartaId("cartaMaestraId-001");
        command.setJuegoId("juegoId-001");
        command.setJugadorId("jugadorId-001");
        when(repository.obtenerEventosPor("juegoId-001")).thenReturn(history());

        StepVerifier.create(useCase.apply(Mono.just(command)))//act
                .expectNextMatches(domainEvent -> {
                    var event = (CartaPuestaEnTablero) domainEvent;
                    Assertions.assertEquals("jugadorId-001", event.getJugadorId().value());
                    return "cartaMaestraId-001".equals(event.getCarta().value().cartaId().value());
                })
                .expectNextMatches(domainEvent -> {
                    var event = (CartaRetiradaDeMazo) domainEvent;
                    Assertions.assertEquals("jugadorId-001", event.getJugadorId().value());
                    return "cartaMaestraId-001".equals(event.getCarta().value().cartaId().value());                    })
                .expectComplete()
                .verify();
    }

    private Flux<DomainEvent> history() {
        var jugadorId = JugadorId.of("jugadorId-001");
        var jugador2Id = JugadorId.of("jugadorId-002");
        var cartas = Set.of(new Carta(
                CartaMaestraId.of("cartaMaestraId-001"),
                20,
                false, true
        ));
        var ronda = new Ronda(Set.of(jugadorId, jugador2Id),1);
        return Flux.just(
                new JuegoCreado(jugadorId),
                new JugadorAgregado(jugadorId, "raul", new Mazo(cartas)),
                new TableroCreado(new TableroId(), Set.of(jugadorId, jugador2Id)),
                new RondaCreada(ronda, 30),
                new RondaIniciada()
        );
    }

}