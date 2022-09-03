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


}
