package com.sofka.cardgame.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.commands.FinalizarRondaCommand;
import com.sofka.cardgame.events.*;
import com.sofka.cardgame.gateway.JuegoDomainEventRepository;
import com.sofka.cardgame.values.*;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class IniciarCuentaRegresivaUseCaseTest {

    @InjectMocks
    private IniciarCuentaRegresivaUseCase useCase;

    @Mock
    private JuegoDomainEventRepository repository;

    @Mock
    private FinalizarRondaUseCase useCase2;

    @Test
    void iniciarCuentaRegresivaHappyPass(){

        //arrange
        var event = new RondaIniciada();
        event.setAggregateRootId("Juego-001");

        //act & assert
        when(repository.obtenerEventosPor("Juego-001"))
                .thenReturn(history());

        var command = new FinalizarRondaCommand();
        command.setJuegoId("Juego-001");

        when(useCase2.apply(any())).thenReturn(history02());

        StepVerifier
                .create(useCase.apply(Mono.just(event)))
                .expectNextMatches(domainEvent -> {
                    var event2 = (TiempoActualizadoDeTablero) domainEvent;
                    return event2.getTableroId().equals(TableroId.of("Tablero-001"));
                })
                .expectNextMatches(domainEvent -> {
                    var event2 = (TiempoActualizadoDeTablero) domainEvent;
                    return event2.getTableroId().equals(TableroId.of("Tablero-001"));
                })
                .expectNextMatches(domainEvent -> {
                    var event2 = (JuegoCreado) domainEvent;
                    return event2.aggregateRootId().equals("Juego-001");
                })
                .expectNextMatches(domainEvent -> {
                    var event3 = (JugadorAgregado) domainEvent;
                    return  event3.getJugadorId().value().equals("Jugador-001");
                })
                .expectNextMatches(domainEvent -> {
                    var event3 = (JugadorAgregado) domainEvent;
                    return  event3.getJugadorId().value().equals("Jugador-002");
                })
                .expectNextMatches(domainEvent -> {
                    var event3 = (TableroCreado) domainEvent;
                    return  event3.getTableroId().value().equals("Tablero-001");
                })
                .expectNextMatches(domainEvent -> {
                    var event3 = (RondaCreada) domainEvent;
                    return  event3.aggregateRootId().equals("Juego-001");
                })
                .expectNextMatches(domainEvent -> {
                    var event3 = (RondaIniciada) domainEvent;
                    return  event3.aggregateRootId().equals("Juego-001");
                })
                .expectNextMatches(domainEvent -> {
                    var event3 = (CartaPuestaEnTablero) domainEvent;
                    return  event3.aggregateRootId().equals("Juego-001");
                })
                .expectNextMatches(domainEvent -> {
                    var event3 = (CartaRetiradaDeMazo) domainEvent;
                    return  event3.aggregateRootId().equals("Juego-001");
                })
                .expectNextMatches(domainEvent -> {
                    var event3 = (CartaPuestaEnTablero) domainEvent;
                    return  event3.aggregateRootId().equals("Juego-001");
                })
                .expectNextMatches(domainEvent -> {
                    var event3 = (CartaRetiradaDeMazo) domainEvent;
                    return  event3.aggregateRootId().equals("Juego-001");
                })
                .expectNextMatches(domainEvent -> {
                    var event2 = (TiempoActualizadoDeTablero) domainEvent;
                    return event2.getTableroId().equals(TableroId.of("Tablero-001"));
                })
                .expectComplete()
                .verify();

    }

    private Flux<DomainEvent> history02() {
        var event = new JuegoCreado(JugadorId.of("Jugador-001"));

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

        var evento3 = new TableroCreado(TableroId.of("Tablero-001"), Set.of(JugadorId.of("Jugador-001"), JugadorId.of("Jugador-002")));
        var evento4 = new RondaCreada(new Ronda(1, evento3.getJugadorIds()), 60);
        var evento5 = new RondaIniciada();
        var event7 = new CartaPuestaEnTablero(evento3.getTableroId(), evento1.getJugadorId(), new Carta(CartaMaestraId.of("card-001"), 1, false, true,"www.google.com"));
        var event8 = new CartaRetiradaDeMazo(evento1.getJugadorId(), new Carta(CartaMaestraId.of("card-001"), 1, false, true,"www.google.com"));
        var event9 = new CartaPuestaEnTablero(evento3.getTableroId(), evento2.getJugadorId(), new Carta(CartaMaestraId.of("card-006"), 6, false, true, "www.google.com"));
        var event10 = new CartaRetiradaDeMazo(evento2.getJugadorId(), new Carta(CartaMaestraId.of("card-006"), 6, false, true, "www.google.com"));

        event.setAggregateRootId("Juego-001");
        evento1.setAggregateRootId("Juego-001");
        evento2.setAggregateRootId("Juego-001");
        evento3.setAggregateRootId("Juego-001");
        evento4.setAggregateRootId("Juego-001");
        evento5.setAggregateRootId("Juego-001");
        event7.setAggregateRootId("Juego-001");
        event8.setAggregateRootId("Juego-001");
        event9.setAggregateRootId("Juego-001");
        event10.setAggregateRootId("Juego-001");

        return Flux.just(event, evento1, evento2, evento3, evento4, evento5, event7, event8, event9, event10);
    }

    private Flux<DomainEvent> history(){

        var evento = new JuegoCreado(JugadorId.of("Jugador-001"));

        var evento2 = new TableroCreado(TableroId.of("Tablero-001"),Set.of(JugadorId.of("Jugador-001")));

        var evento3 = new RondaCreada(new Ronda(1, Set.of(JugadorId.of("Jugador-001"), JugadorId.of("Jugador-002"))), 3);

        evento.setAggregateRootId("Juego-001");
        evento2.setAggregateRootId("Juego-001");
        evento3.setAggregateRootId("Juego-001");

        return Flux.just(evento, evento2, evento3);
    }

}