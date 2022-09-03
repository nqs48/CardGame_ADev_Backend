package com.sofka.cardgame;

import co.com.sofka.domain.generic.EventChange;
import com.sofka.cardgame.entities.Jugador;
import com.sofka.cardgame.entities.Tablero;
import com.sofka.cardgame.events.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class JuegoEventChange extends EventChange {
    public JuegoEventChange(Juego juego) {
        apply((JuegoCreado event) -> {
            juego.jugadores= new HashMap<>();
            juego.jugadorPrincipal= event.getJugadorPrincipalId();
        });

        apply((JugadorAgregado event) -> {
            juego.jugadores.put(event.getIdentity(), new Jugador(event.getIdentity(), event.getAlias(), event.getMazo()));
        });

        apply((TableroCreado event) -> {
            juego.tablero= new Tablero(event.getTableroId(), event.getJugadoresId());
        });

        apply((RondaCreada event) -> {
            if(Objects.isNull(juego.tablero)){
                throw new IllegalArgumentException("Primero debes crear un tablero");
            }
            juego.ronda= event.getRonda();
            juego.tablero.ajustarTiempo(event.getTiempo());
        });

        apply((TiempoActualizadoDeTablero event)->{
            juego.tablero.ajustarTiempo(event.getTiempo());
        });

        apply((CartaPuestaEnTablero event) -> {
            juego.tablero.adicionarPartida(event.getJugadorId(),event.getCarta());
        });

        apply((CartaRetiradaDeTablero event) ->{
            juego.tablero.retirarCarta(event.getJugadorId(),event.getCarta());
        });

        apply((CartaRetiradaDeMazo event) -> {
           juego.jugadores.get(event.getJugadorId()).retirarCartaDeMazo(event.getCarta());
        });

        apply((RondaIniciada event) -> {
            juego.ronda = juego.ronda.iniciarRonda();
            juego.tablero.habilitarApuesta();
        });

        apply((RondaTerminada event) -> {
            juego.ronda= juego.ronda.terminarRonda();
            juego.tablero.inhabilitarApuesta();
        });

        apply((CartasAsignadasAGanador event) -> {
            var jugador =  juego.jugadores.get(event.getGanadorId());
            event.getCartasApuesta().forEach(jugador::agregarCartaAMazo);
        });

        apply((JuegoFinalizado event) ->{
            juego.ganador= juego.jugadores.get(event.getJugadorId());
        });



    }
}
