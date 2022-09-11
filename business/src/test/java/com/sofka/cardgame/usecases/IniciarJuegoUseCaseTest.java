package com.sofka.cardgame.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.commands.CrearRondaCommand;
import com.sofka.cardgame.commands.IniciarJuegoCommand;
import com.sofka.cardgame.events.JuegoCreado;
import com.sofka.cardgame.events.TableroCreado;
import com.sofka.cardgame.gateway.JuegoDomainEventRepository;
import com.sofka.cardgame.values.JuegoId;
import com.sofka.cardgame.values.JugadorId;
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

    @InjectMocks
    private IniciarJuegoUseCase useCase;

    @Mock
    private JuegoDomainEventRepository repository;


    @Test
    void iniciarJuegoHappyPass() {

        //Arrange
        var juegoId = JuegoId.of("JuegoId-001");
        var comando = new IniciarJuegoCommand(juegoId.value());

        when(repository.obtenerEventosPor(juegoId.value())).thenReturn(eventos());

        StepVerifier.create(useCase.apply(Mono.just(comando)))
                .expectNextMatches(eventoDominio->{
                    var evento = (TableroCreado) eventoDominio;
                    return "JuegoId-001".equals(evento.aggregateRootId());
                })
                .expectComplete()
                .verify();


    }

    private Flux<DomainEvent> eventos() {
        return Flux.just(
                new JuegoCreado(JugadorId.of("Jugador01")));
    }


}