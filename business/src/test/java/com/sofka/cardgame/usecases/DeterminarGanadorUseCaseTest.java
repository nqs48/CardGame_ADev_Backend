package com.sofka.cardgame.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.events.JuegoCreado;
import com.sofka.cardgame.events.JuegoFinalizado;
import com.sofka.cardgame.events.JugadorAgregado;
import com.sofka.cardgame.events.RondaTerminada;
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
class DeterminarGanadorUseCaseTest {

    @InjectMocks
    private DeterminarGanadorUseCase useCase;

    @Mock
    private JuegoDomainEventRepository repository;

    @Test
    void determinarGanador(){

        var event = new RondaTerminada(TableroId.of("Tablero-001"), Set.of(JugadorId.of("Jugador-001"), JugadorId.of("Jugador-002")));
        event.setAggregateRootId("Juego-001");

        when(repository.obtenerEventosPor("Juego-001"))
                .thenReturn(history());

        StepVerifier.create(useCase.apply(Mono.just(event)))
                .expectNextMatches(domainEvent ->{
                    var event2 = (JuegoFinalizado) domainEvent;
                    return event2.getJugadorId().equals(JugadorId.of("Jugador-001")) && event2.getAlias().equals("Nestea");
                })
                .expectComplete()
                .verify();
    }



    private Flux<DomainEvent> history() {
        var event = new JuegoCreado(JugadorId.of("Jugador-001"));

        var evento2 = new JugadorAgregado(
                JugadorId.of("Jugador-001"), "Nestea",
                new Mazo(Set.of(
                        new Carta(CartaMaestraId.of("Carta-001"), 10, true, true,"www.google.com"),
                        new Carta(CartaMaestraId.of("Carta-002"), 20, true, true,"www.google.com"),
                        new Carta(CartaMaestraId.of("Carta-003"), 30, true, true,"www.google.com"),
                        new Carta(CartaMaestraId.of("Carta-004"), 40, true, true,"www.google.com"),
                        new Carta(CartaMaestraId.of("Carta-005"), 50, true, true,"www.google.com")
                )));


        event.setAggregateRootId("Juego-001");
        evento2.setAggregateRootId("Jugador-001");

        return Flux.just(event, evento2);
    }
}