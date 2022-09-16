package com.sofka.cardgame.usecases;

import com.sofka.cardgame.commands.CrearJuegoCommand;
import com.sofka.cardgame.events.JuegoCreado;
import com.sofka.cardgame.events.JugadorAgregado;
import com.sofka.cardgame.gateway.JuegoDomainEventRepository;
import com.sofka.cardgame.gateway.ListaDeCartaService;
import com.sofka.cardgame.gateway.model.CartaMaestra;
import com.sofka.cardgame.values.JuegoId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CrearJuegoUseCaseTest {

    @Mock
    private ListaDeCartaService service;
    @InjectMocks
    private CrearJuegoUseCase useCase;
    @Test
    void crearJuegoHappyPass() {
        var command = new CrearJuegoCommand();
        command.setJuegoId("Juego-001");
        command.setJugadores(new HashMap<>());
        command.getJugadores().put("01", "Nestea");
        command.getJugadores().put("GGGG", "NesteaTwo");
        command.setJugadorPrincipalId("01");

        when(service.obtenerCartasDeMarvel()).thenReturn(mazo());

        StepVerifier.create(useCase.apply(Mono.just(command)))
                .expectNextMatches(domainEvent -> {
                    var event = (JuegoCreado) domainEvent;
                    return event.getJugadorPrincipalId().value().equals("01") && event.aggregateRootId().equals("Juego-001");
                })
                .expectNextMatches(domainEvent -> {
                    var event = (JugadorAgregado) domainEvent;
                    assert event.getMazo().value().cantidad().equals(5);
                    return event.getJugadorId().value().equals("01") && event.getAlias().equals("Nestea");
                })
                .expectNextMatches(domainEvent -> {
                    var event = (JugadorAgregado) domainEvent;
                    assert event.getMazo().value().cantidad().equals(5);
                    return event.getJugadorId().value().equals("GGGG") && event.getAlias().equals("NesteaTwo");
                })
                .expectComplete()
                .verify();
    }

    private Flux<CartaMaestra> mazo(){
        return Flux.just(
                new CartaMaestra("Master-001", "name-001"),
                new CartaMaestra("Master-002", "name-002"),
                new CartaMaestra("Master-003", "name-003"),
                new CartaMaestra("Master-004", "name-004"),
                new CartaMaestra("Master-005", "name-005"),
                new CartaMaestra("Master-006", "name-006"),
                new CartaMaestra("Master-007", "name-007"),
                new CartaMaestra("Master-008", "name-008"),
                new CartaMaestra("Master-009", "name-009"),
                new CartaMaestra("Master-0010", "name-010")

        );
    }



}