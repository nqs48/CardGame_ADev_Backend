package com.sofka.cardgame.entities;

import co.com.sofka.domain.generic.Entity;
import com.sofka.cardgame.values.Carta;
import com.sofka.cardgame.values.JugadorId;
import com.sofka.cardgame.values.TableroId;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Tablero extends Entity<TableroId> {

    private Integer tiempoEnSegundos;
    private Boolean estaHabilitado;
    private final Map<JugadorId, Set<Carta>> partida;


    public Tablero(TableroId tableroId, Set<JugadorId> jugadorIds) {
        super(tableroId);
        this.partida = new HashMap<>();
        this.estaHabilitado = false;
        jugadorIds.forEach(jugadorId -> partida.put(jugadorId, new HashSet<>()));
    }

    public Integer tiempo() {
        return tiempoEnSegundos;
    }

    //Comportamientos
    public void ajustarTiempo(Integer tiempo){
        this.tiempoEnSegundos = tiempo;
    }

    public void adicionarPartida(JugadorId jugadorId, Carta carta){
        partida.getOrDefault(jugadorId, new HashSet<>()).add(carta);
    }

    public void retirarCarta(JugadorId jugadorId, Carta carta){
        partida.getOrDefault(jugadorId, new HashSet<>()).remove(carta);
    }

    public void habilitarApuesta(){
        this.estaHabilitado = true;
    }

    public void inhabilitarApuesta(){
        this.estaHabilitado = false;
    }

    public void reiniciarPartida(){
        partida.clear();
    }

    public Boolean estaHabilitado() {
        return this.estaHabilitado;
    }

    public Map<JugadorId, Set<Carta>> partida() {
        return this.partida;
    }

}
