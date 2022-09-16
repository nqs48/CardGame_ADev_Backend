package com.sofka.cardgame.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.commands.CrearRondaCommand;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CrearRondaUseCaseTest {

    @InjectMocks
    private CrearRondaUseCase useCase;

    @Mock
    private JuegoDomainEventRepository repository;


    @Test
    void crearRondaHappyPass() {

        //arrange
        var juegoId = JuegoId.of("Juego-001");
        var jugadores = Set.of(JugadorId.of("jugadorId-001"), JugadorId.of("jugadorId-002"));
        var jugadores2 = Set.of("jugadorId-001", "jugadorId-002");

        var command2= new RondaTerminada(new TableroId(), jugadores);
        command2.setAggregateRootId("Juego-001");

        when(repository.obtenerEventosPor("Juego-001"))
                .thenReturn(eventos());

        StepVerifier.create(useCase.apply(Mono.just(command2)))
                .expectNextMatches(eventoDominio ->{
                    var evento = (RondaCreada) eventoDominio;
                    return "Juego-001".equals(evento.aggregateRootId());
                })
                .expectComplete()
                .verify();
    }

    private Flux<DomainEvent> eventos() {
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

        var evento4 = new RondaCreada(new Ronda(1, evento3.getJugadorIds()), 80);

        var evento5 = new RondaIniciada( );


        var evento6 = new RondaTerminada(TableroId.of("Tablero-001"), Set.of(JugadorId.of("Jugador-001"), JugadorId.of("Jugador-002")));

        event.setAggregateRootId("Juego-001");
        evento1.setAggregateRootId("Juego-001");
        evento2.setAggregateRootId("Juego-001");
        evento3.setAggregateRootId("Juego-001");
        evento4.setAggregateRootId("Juego-001");
        evento5.setAggregateRootId("Juego-001");
        evento6.setAggregateRootId("Juego-001");

        return Flux.just(
                event,
                evento1,
                evento2,
                evento3,
                evento4,
                evento5,
                evento6

        );
    }


}