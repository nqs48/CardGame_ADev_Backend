package com.sofka.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.domain.values.Carta;
import com.sofka.domain.values.JugadorId;

import java.util.Set;

public class CartasAsignadasAGanador extends DomainEvent {
    public CartasAsignadasAGanador(JugadorId ganadorId, Integer puntos, Set<Carta> cartasApostadas) {
        super("");
    }
}
