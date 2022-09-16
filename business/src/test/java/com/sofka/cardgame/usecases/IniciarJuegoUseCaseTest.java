package com.sofka.cardgame.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.commands.CrearRondaCommand;
import com.sofka.cardgame.commands.IniciarJuegoCommand;
import com.sofka.cardgame.events.JuegoCreado;
import com.sofka.cardgame.events.JugadorAgregado;
import com.sofka.cardgame.events.RondaCreada;
import com.sofka.cardgame.events.TableroCreado;
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
class IniciarJuegoUseCaseTest {

    @Mock
    private JuegoDomainEventRepository repository;
    @InjectMocks
    private IniciarJuegoUseCase useCase;

    @Test
    void iniciarJuegoHappyPass(){
        //ARRANGE
        var command = new IniciarJuegoCommand();
        command.setJuegoId("Juego-001");

        //ASSERT & ACT
        when(repository.obtenerEventosPor("Juego-001"))
                .thenReturn(history());

        StepVerifier
                .create(useCase.apply(Mono.just(command)))
                .expectNextMatches(domainEvent -> {
                    var event = (TableroCreado) domainEvent;
                    return event.aggregateRootId().equals("Juego-001");
                })
                .expectNextMatches(domainEvent -> {
                    var event = (RondaCreada) domainEvent;
                    return event.aggregateRootId().equals("Juego-001");
                })
                .expectComplete()
                .verify();
    }


    private Flux<DomainEvent> history(){
        var evento = new JuegoCreado(JugadorId.of("Jugador-001"));

        var evento2 = new JugadorAgregado(
                JugadorId.of("Jugador-001"), "Nestea",
                new Mazo(Set.of(
                        new Carta(CartaMaestraId.of("Carta-001"), 10, true, true,"www.google.com"),
                        new Carta(CartaMaestraId.of("Carta-002"), 20, true, true,"www.google.com"),
                        new Carta(CartaMaestraId.of("Carta-003"), 30, true, true,"www.google.com"),
                        new Carta(CartaMaestraId.of("Carta-004"), 40, true, true,"www.google.com"),
                        new Carta(CartaMaestraId.of("Carta-005"), 50, true, true,"www.google.com")
                )));


        var evento3 = new JugadorAgregado(JugadorId.of("Jugador-002"), "Nestea T.",
                new Mazo(Set.of(
                        new Carta(CartaMaestraId.of("Carta-006"), 60, true, true, "www.google.com"),
                        new Carta(CartaMaestraId.of("Carta-007"), 70, true, true,"www.google.com"),
                        new Carta(CartaMaestraId.of("Carta-008"), 80, true, true,"www.google.com"),
                        new Carta(CartaMaestraId.of("Carta-009"), 90, true, true,"www.google.com"),
                        new Carta(CartaMaestraId.of("Carta-010"), 100, true, true,"www.google.com")

                )));


        var evento4 = new TableroCreado(TableroId.of("Tablero-001"),
                Set.of(JugadorId.of("Jugador-001"), JugadorId.of("Jugador-002")));


        var evento5 = new RondaCreada(new Ronda(1, evento4.getJugadorIds()), 80);


        evento.setAggregateRootId("Juego-001");
        evento2.setAggregateRootId("Juego-001");
        evento3.setAggregateRootId("Juego-001");
        evento4.setAggregateRootId("Juego-001");
        evento5.setAggregateRootId("Juego-001");

        return Flux.just(evento, evento2, evento3, evento4, evento5);
    }


}