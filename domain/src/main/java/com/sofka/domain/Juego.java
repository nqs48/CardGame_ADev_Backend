package com.sofka.domain;

import co.com.sofka.domain.generic.AggregateEvent;
import com.sofka.domain.entities.Jugador;
import com.sofka.domain.entities.Tablero;
import com.sofka.domain.values.Estado;
import com.sofka.domain.values.JuegoId;

public class Juego extends AggregateEvent<JuegoId> {

    protected Tablero tablero;
    protected Set<Jugador> jugadores;
    protected Estado estado;
    protected Jugador ganador;







}
