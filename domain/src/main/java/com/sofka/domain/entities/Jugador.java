package com.sofka.domain.entities;

import co.com.sofka.domain.generic.Entity;
import com.sofka.domain.values.JugadorId;
import com.sofka.domain.values.Mazo;

import java.util.Objects;

public class Jugador extends Entity<JugadorId> {

    private final String email;
    private Mazo mazo;


    public Jugador(JugadorId entityId, String email) {
        super(entityId);
        this.email = Objects.requireNonNull(email);
    }



}
