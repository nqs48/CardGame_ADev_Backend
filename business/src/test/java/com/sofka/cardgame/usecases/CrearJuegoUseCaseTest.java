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

    @InjectMocks
    private CrearJuegoUseCase useCase;

    @Mock
    private ListaDeCartaService listaDeCartaService;

    @Test
    void crearJuegoHappyPass() {

        //arrange
        var juegoId = JuegoId.of("J01");
        var jugadores = new HashMap<String,String>();
        jugadores.put("j01","Jugador01");
        jugadores.put("j02","Jugador02");
        var comando = new CrearJuegoCommand(juegoId.value(),jugadores,"j01");

        when(listaDeCartaService.obtenerCartasDeMarvel()).thenReturn(cartasJuego());

        StepVerifier.create(useCase.apply(Mono.just(comando)))
                .expectNextMatches(domainEvent->{
                    var evento = (JuegoCreado) domainEvent;
                    return "J01".equals(evento.aggregateRootId())
                            && "j01".equals(evento.getJugadorPrincipalId().value());
                })
                .expectNextMatches(eventoDominio->{
                    var evento = (JugadorAgregado) eventoDominio;
                    return "j01".equals(evento.getIdentity().value())
                            && "Jugador01".equals(evento.getAlias());
                })
                .expectNextMatches(eventoDominio->{
                    var evento = (JugadorAgregado) eventoDominio;
                    return "j02".equals(evento.getIdentity().value())
                            && "Jugador02".equals(evento.getAlias());
                })
                .expectComplete()
                .verify();


    }

    private Flux<CartaMaestra> cartasJuego() {

        return Flux.just(
                new CartaMaestra("C01","Carta 1"),
                new CartaMaestra("C02","Carta 2"),
                new CartaMaestra("C03","Carta 3"),
                new CartaMaestra("C04","Carta 4"),
                new CartaMaestra("C05","Carta 5"),
                new CartaMaestra("C06","Carta 6"),
                new CartaMaestra("C07","Carta 7"),
                new CartaMaestra("C08","Carta 8"),
                new CartaMaestra("C09","Carta 9"),
                new CartaMaestra("C010","Carta 10")
        );
    }



}