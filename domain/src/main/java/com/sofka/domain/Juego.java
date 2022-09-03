package com.sofka.domain;

import co.com.sofka.domain.generic.AggregateEvent;
import com.sofka.domain.entities.Jugador;
import com.sofka.domain.values.Ronda;
import com.sofka.domain.entities.Tablero;
import com.sofka.domain.values.JuegoId;
import com.sofka.domain.values.JugadorId;
import com.sofka.domain.values.TableroId;

import java.util.Map;
import java.util.Set;

public class Juego extends AggregateEvent<JuegoId> {

    protected Tablero tablero;
    protected Map<JugadorId, Jugador> jugadores;
    protected Ronda ronda;
    protected Jugador ganador;

    public Juego(JuegoId juegoId) {
        super(juegoId);
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

    public void terminarRonda(TableroId tableroId, Set<JugadorId> jugadorIds){
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

    public void asignarCartasAGanador(JugadorId ganadorId, Integer puntos, Set<Carta> cartasApuesta){
        appendChange(new CartasAsignadasAGanador(ganadorId, puntos, cartasApuesta)).apply();
    }

    public void finalizarJuego(JugadorId jugadorId, String alias){
        appendChange(new JuegoFinalizado(jugadorId,alias)).apply();
    }


















}
