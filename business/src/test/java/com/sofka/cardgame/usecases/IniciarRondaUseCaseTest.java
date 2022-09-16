package com.sofka.cardgame.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.commands.IniciarRondaCommand;
import com.sofka.cardgame.events.JuegoCreado;
import com.sofka.cardgame.events.RondaCreada;
import com.sofka.cardgame.events.RondaIniciada;
import com.sofka.cardgame.events.TableroCreado;
import com.sofka.cardgame.gateway.JuegoDomainEventRepository;
import com.sofka.cardgame.values.JugadorId;
import com.sofka.cardgame.values.Ronda;
import com.sofka.cardgame.values.TableroId;
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
class IniciarRondaUseCaseTest {

    @Mock
    private JuegoDomainEventRepository repository;

    @InjectMocks
    private IniciarRondaUseCase useCase;

    @Test
    void iniciarRondaHappyPass(){

        //arrange
        var command = new IniciarRondaCommand();
        command.setJuegoId("Juego-001");

        when(repository.obtenerEventosPor("Juego-001"))
                .thenReturn(history());

        //act & assert
        StepVerifier
                .create(useCase.apply(Mono.just(command)))
                .expectNextMatches(domainEvent -> {
                    var event = (RondaIniciada) domainEvent;
                    return event.aggregateRootId().equals("Juego-001");
                })
                .expectComplete()
                .verify();
    }

    private Flux<DomainEvent> history(){

        var evento = new JuegoCreado(JugadorId.of("Jugador-001"));

        var evento2 = new TableroCreado(TableroId.of("Tablero-001"), Set.of(JugadorId.of("Jugador-001"), JugadorId.of("Jugador-002")));

        var evento3 = new RondaCreada(new Ronda( 1, evento2.getJugadorIds()), 2);

        evento.setAggregateRootId("Juego-001");
        evento2.setAggregateRootId("Juego-001");
        evento3.setAggregateRootId("Juego-001");


        return Flux.just(evento, evento2, evento3);
    }


}