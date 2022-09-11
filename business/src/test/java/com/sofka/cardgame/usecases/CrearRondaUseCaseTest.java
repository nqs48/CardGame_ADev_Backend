package com.sofka.cardgame.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.commands.CrearRondaCommand;
import com.sofka.cardgame.events.JuegoCreado;
import com.sofka.cardgame.events.RondaCreada;
import com.sofka.cardgame.events.TableroCreado;
import com.sofka.cardgame.gateway.JuegoDomainEventRepository;
import com.sofka.cardgame.values.JuegoId;
import com.sofka.cardgame.values.JugadorId;
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
class CrearRondaUseCaseTest {

    @InjectMocks
    private CrearRondaUseCase useCase;

    @Mock
    private JuegoDomainEventRepository repository;


    @Test
    void crearRondaHappyPass() {

        //arrange
        var juegoId = JuegoId.of("JuegoId-001");
        var jugadores = Set.of("jugadorId-001", "jugadorId-002");
        var comando = new CrearRondaCommand(juegoId.value(), jugadores);

        when(repository.obtenerEventosPor(juegoId.value())).thenReturn(eventos());

        StepVerifier.create(useCase.apply(Mono.just(comando)))
                .expectNextMatches(eventoDominio ->{
                    var evento = (RondaCreada) eventoDominio;

                    return "JuegoId-001".equals(evento.aggregateRootId());
                })
                .expectComplete()
                .verify();
    }

    private Flux<DomainEvent> eventos() {
        var evento = new JuegoCreado(JugadorId.of("jugadorId-001"));
        var jugadores = Set.of(JugadorId.of("jugadorId-001"), JugadorId.of("jugadorId-002"));
        var evento2 = new TableroCreado(TableroId.of("tableroId-001"),jugadores);
        return Flux.just(
                evento,
                evento2
        );
    }


}