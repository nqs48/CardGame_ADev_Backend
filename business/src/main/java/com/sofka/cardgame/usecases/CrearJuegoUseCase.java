package com.sofka.cardgame.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.Juego;
import com.sofka.cardgame.JugadorFactory;
import com.sofka.cardgame.commands.CrearJuegoCommand;
import com.sofka.cardgame.gateway.ListaDeCartaService;
import com.sofka.cardgame.gateway.model.CartaMaestra;
import com.sofka.cardgame.values.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class CrearJuegoUseCase extends UseCaseForCommand<CrearJuegoCommand> {

    private final ListaDeCartaService listaDeCartaService;

    private final Integer limitCards = 5;

    public CrearJuegoUseCase(ListaDeCartaService listaDeCartaService) {
        this.listaDeCartaService = listaDeCartaService;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<CrearJuegoCommand> input) {
        return listaDeCartaService.obtenerCartasDeMarvel().collectList()
                .flatMapMany(cartas -> input.flatMapIterable(command -> {

                    if (command.getJugadores().size() < 2){
                        throw new IllegalArgumentException("Para jugar deben haber minimo dos jugadores");
                    }

                    var factory = new JugadorFactory();
                    command.getJugadores()
                            .forEach((id, alias) ->
                                    factory.agregarJugador(JugadorId.of(id), alias, generarMazo(cartas))
                            );
                    var juego = new Juego(
                            JuegoId.of(command.getJuegoId()),
                            JugadorId.of(command.getJugadorPrincipalId()),
                            factory
                    );
                    return juego.getUncommittedChanges();
                }));

    }




    private Mazo generarMazo(List<CartaMaestra> cartas) {
        Collections.shuffle(cartas);
        var lista = cartas.stream().limit(5)
                .map(carta -> new Carta(CartaMaestraId.of(carta.getId()), carta.getPoder(), false, true, carta.getUri()))
                .collect(Collectors.toList());
        cartas.removeIf(cartaMaestra -> lista.stream().anyMatch(carta -> {
            var id = carta.value().cartaId().value();
            return cartaMaestra.getId().equals(id);
        }));
        return new Mazo(new HashSet<>(lista));
    }

}
