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
    void ponerCartaHappyPass(){

        //arrange
        var command = new PonerCartaEnTableroCommand();
        command.setJuegoId("Juego-001");
        command.setJugadorId("Jugador-001");
        command.setCartaId("card-001");

        when(repository.obtenerEventosPor("Juego-001")).thenReturn(history());

        //act & assert
        StepVerifier.create(useCase.apply(Mono.just(command)))
                .expectNextMatches(domainEvent -> {
                    var event = (CartaPuestaEnTablero) domainEvent;
                    assert event.getTableroId().value().equals("Board-001");
                    return event.getCarta().value().cartaId().value().equals("card-001");
                })
                .expectNextMatches(domainEvent -> {
                    var event = (CartaRetiradaDeMazo) domainEvent;
                    assert event.getJugadorId().value().equals("Jugador-001");
                    return event.getCarta().value().cartaId().value().equals("card-001");
                })
                .expectComplete()
                .verify();
    }

    private Flux<DomainEvent> history() {
        var evento = new JuegoCreado(JugadorId.of("Jugador-001"));

        var evento1 = new JugadorAgregado(JugadorId.of("Jugador-001"), "Nestea", new Mazo(Set.of(
                new Carta(CartaMaestraId.of("card-001"), 1, false, true,"www.google.com"),
                new Carta(CartaMaestraId.of("card-002"), 2, false, true, "www.google.com"),
                new Carta(CartaMaestraId.of("card-003"), 3, false, true, "www.google.com"),
                new Carta(CartaMaestraId.of("card-004"), 4, false, true, "www.google.com"),
                new Carta(CartaMaestraId.of("card-005"), 5, false, true, "www.google.com")
        )));

        var evento2 = new JugadorAgregado(JugadorId.of("Jugador-002"), "Nestea T", new Mazo(Set.of(
                new Carta(CartaMaestraId.of("card-006"), 6, false, true, "www.google.com"),
                new Carta(CartaMaestraId.of("card-007"), 7, false, true, "www.google.com"),
                new Carta(CartaMaestraId.of("card-008"), 8, false, true,"www.google.com"),
                new Carta(CartaMaestraId.of("card-009"), 9, false, true,"www.google.com"),
                new Carta(CartaMaestraId.of("card-0010"), 10, false, true,"www.google.com")

        )));

        var evento3 = new TableroCreado(TableroId.of("Board-001"), Set.of(JugadorId.of("Jugador-001"), JugadorId.of("Jugador-002")));
        var evento4 = new RondaCreada(new Ronda(1, evento3.getJugadorIds()), 60);
        var evento5 = new RondaIniciada();

        evento.setAggregateRootId("Juego-001");
        evento1.setAggregateRootId("Juego-001");
        evento2.setAggregateRootId("Juego-001");
        evento3.setAggregateRootId("Juego-001");
        evento4.setAggregateRootId("Juego-001");
        evento5.setAggregateRootId("Juego-001");


        return Flux.just(evento, evento1, evento2, evento3, evento4, evento5);
    }

}