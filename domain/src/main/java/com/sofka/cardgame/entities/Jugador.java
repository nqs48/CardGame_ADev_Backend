package com.sofka.cardgame.entities;

import co.com.sofka.domain.generic.Entity;
import com.sofka.cardgame.values.Carta;
import com.sofka.cardgame.values.JugadorId;
import com.sofka.cardgame.values.Mazo;

import java.util.Objects;

public class Jugador extends Entity<JugadorId> {

    private final String email;
    private Mazo mazo;


    public Jugador(JugadorId entityId, String email, Mazo mazo) {
        super(entityId);
        this.email = Objects.requireNonNull(email);
        this.mazo = Objects.requireNonNull(mazo);
        if(mazo.value().cantidad() <= 0){
            throw new IllegalArgumentException("El mazo debe tener cartas");
        }
    }

    public void agregarCartaAMazo(Carta carta){
        mazo = mazo.nuevaCarta(carta);
    }

    public void retirarCartaDeMazo(Carta carta){
        mazo = mazo.retirarCarta(carta);
    }

    public String alias() {
        return this.email;
    }

    public Mazo mazo() {
        return this.mazo;
    }



}
