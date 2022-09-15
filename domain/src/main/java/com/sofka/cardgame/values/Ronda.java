package com.sofka.cardgame.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Set;

public class Ronda implements ValueObject<Ronda.Props> {

    private final Set<JugadorId> jugadores;
    private final Integer numeroRonda;
    private final Boolean estaIniciada;

    public Ronda( Integer numeroRonda, Set<JugadorId> jugadores) {
        this.jugadores = jugadores;
        this.numeroRonda = numeroRonda;
        this.estaIniciada = false;
        if(numeroRonda <= 0){
            throw new IllegalArgumentException("El numero de la ronda debe no puede ser cero o negativo");
        }
        if(jugadores.size() <= 1){
            throw new IllegalArgumentException("La ronda se crear con minimo 2 jugadores");
        }
    }

    public Ronda(Integer numeroRonda, Set<JugadorId> jugadores,  Boolean estaIniciada) {
        this.jugadores = jugadores;
        this.numeroRonda = numeroRonda;
        this.estaIniciada = estaIniciada;
    }

    //Comportamientos de la ronda
    public Ronda iniciarRonda(){
        return new Ronda(this.numeroRonda, this.jugadores,true);
    }

    public Ronda terminarRonda(){
        return new Ronda(this.numeroRonda, this.jugadores,false);
    }

    public Ronda incrementarRonda(Set<JugadorId> jugadores){
        System.out.println("Se inicia nueva ronda");
        return new Ronda(this.numeroRonda + 1, jugadores, false);
    }

    @Override
    public Props value(){
        return new Props(){
            @Override
            public Set<JugadorId> jugadores(){return jugadores;}

            @Override
            public Integer numeroRonda(){return numeroRonda;}

            @Override
            public Boolean estaIniciada(){
                return estaIniciada;
            }
        };
    }

    public interface Props {
        Set<JugadorId> jugadores();

        Integer numeroRonda();

        Boolean estaIniciada();
    }
}
