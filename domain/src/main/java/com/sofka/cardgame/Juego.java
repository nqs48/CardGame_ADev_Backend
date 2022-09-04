package com.sofka.cardgame;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.entities.Jugador;
import com.sofka.cardgame.events.*;
import com.sofka.cardgame.values.*;
import com.sofka.cardgame.entities.Tablero;
import com.sofka.cardgame.events.JuegoCreado;
import com.sofka.cardgame.events.JugadorAgregado;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Juego extends AggregateEvent<JuegoId> {

    protected Tablero tablero;
    protected Map<JugadorId, Jugador> jugadores;
    protected Ronda ronda;
    protected Jugador ganador;
    protected JugadorId jugadorPrincipal;

    public Juego(JuegoId juegoId, JugadorId uid, JugadorFactory jugadorFactory) {
        super(juegoId);
        appendChange(new JuegoCreado(uid)).apply();
        jugadorFactory.getJugadores().forEach(jugador -> {
            appendChange(new JugadorAgregado(jugador.identity(), jugador.alias(), jugador.mazo())).apply();
        });
        subscribe(new JuegoEventChange(this));
    }

    private Juego(JuegoId id){
        super(id);
        subscribe(new JuegoEventChange(this));
    }

    public static Juego from(JuegoId juegoId, List<DomainEvent> events){
        var juego = new Juego(juegoId);
        events.forEach(juego::applyEvent);
        return juego;
    }

    //Obtener Atributos del Juego
    public Ronda ronda() {
        return this.ronda;
    }

    public Jugador ganador() {
        return this.ganador;
    }

    public Tablero tablero() {
        return this.tablero;
    }

    public Map<JugadorId, Jugador> jugadores() {
        return jugadores;
    }


    //Comportamientos del Juego
    public void crearTablero(){
        var id = new TableroId();
        appendChange(new TableroCreado(id, jugadores.keySet())).apply();
    }

    public void crearRonda(Ronda ronda, Integer tiempo){
        appendChange(new RondaCreada(ronda, tiempo)).apply();
    }

    public void iniciarRonda(){
        appendChange(new RondaIniciada()).apply();
    }

    public void finalizarRonda(TableroId tableroId, Set<JugadorId> jugadorIds){
        appendChange(new RondaTerminada(tableroId,jugadorIds)).apply();
    }

    public void actualizarTiempoDeTablero(TableroId tableroId, Integer tiempo){
        appendChange(new TiempoActualizadoDeTablero(tableroId, tiempo)).apply();
    }

    public void ponerCartaEnTablero(TableroId tableroId, JugadorId jugadorId, Carta carta){
        appendChange(new CartaRetiradaDeMazo(jugadorId, carta)).apply();
        appendChange(new CartaPuestaEnTablero(tableroId, jugadorId, carta)).apply();
    }

    public void retirarCartaDeTablero(TableroId tableroId, JugadorId jugadorId, Carta carta){
        appendChange(new CartaRetiradaDeTablero(tableroId, jugadorId, carta)).apply();
    }

    public void asignarCartasAGanador(JugadorId ganadorId, Integer puntos, Set<Carta> cartasApostadas){
        appendChange(new CartasAsignadasAGanador(ganadorId, puntos, cartasApostadas)).apply();
    }

    public void finalizarJuego(JugadorId jugadorId, String alias){
        appendChange(new JuegoFinalizado(jugadorId,alias)).apply();
    }


















}
