package com.sofka.cardgame;

import co.com.sofka.domain.generic.EventChange;
import com.sofka.cardgame.entities.Jugador;
import com.sofka.cardgame.events.JuegoCreado;
import com.sofka.cardgame.events.JugadorAgregado;

import java.util.HashMap;
import java.util.HashSet;

public class JuegoEventChange extends EventChange {
    public JuegoEventChange(Juego juego) {
        apply((JuegoCreado event) -> {
            juego.jugadores= new HashMap<>();
            juego.jugadorPrincipal= event.getJugadorPrincipalId();
        });
        apply((JugadorAgregado event) -> {
            juego.jugadores.put(event.getIdentity(), new Jugador(event.getIdentity(), event.getAlias(), event.getMazo()));
        });
    }
}
