package com.sofka.cardgame.gateway;

import com.sofka.cardgame.gateway.model.CartaMaestra;
import reactor.core.publisher.Flux;

public interface ListaDeCartaService {

    Flux<CartaMaestra> obtenerCartasDeMarvel();
}
