package com.sofka.domain.entities;

import co.com.sofka.domain.generic.Entity;
import co.com.sofka.domain.generic.ValueObject;
import com.sofka.domain.values.JugadorId;

import java.util.Set;

public class Ronda implements ValueObject<Ronda.Props> {

    private final Set<JugadorId> jugadores;
    private final Integer numero;
    private final Boolean estaIniciada;

    public Ronda(Set<JugadorId> jugadores, Integer numero) {
        this.jugadores = jugadores;
        this.numero = numero;
        this.estaIniciada = false;
    }

    public Ronda(Set<JugadorId> jugadores, Integer numero, Boolean estaIniciada) {
        this.jugadores = jugadores;
        this.numero = numero;
        this.estaIniciada = estaIniciada;
    }

    @Override
    public Props value(){
        return new Props(){
            @Override
            public Set<JugadorId> jugadores(){return jugadores;}

            @Override
            public Integer numero(){return numero;}

            @Override
            public Boolean estaIniciada(){
                return estaIniciada;
            }
        };
    }

    public interface Props {
        Set<JugadorId> jugadores();

        Integer numero();

        Boolean estaIniciada();
    }
}
