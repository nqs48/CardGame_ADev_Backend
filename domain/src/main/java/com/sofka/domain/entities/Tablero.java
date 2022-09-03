package com.sofka.domain.entities;

import co.com.sofka.domain.generic.Entity;
import com.sofka.domain.values.Carta;
import com.sofka.domain.values.JugadorId;
import com.sofka.domain.values.TableroId;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Tablero extends Entity<TableroId> {

    private Integer tiempo;

    private Boolean estaHabilitado;
    private final Map<JugadorId, Set<Carta>> partida;


    public Tablero(TableroId tableroId, Set<JugadorId> jugadoresId) {
        super(tableroId);
        this.estaHabilitado = false;
        this.partida = new HashMap<>();
        jugadoresId.forEach(jugadorId -> partida.put(jugadorId, new HashSet<>()));
    }

    public Integer tiempo() {
        return this.tiempo;
    }

    //Comportamientos
    public void ajustarTiempo(Integer tiempo){
        this.tiempo = tiempo;
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
