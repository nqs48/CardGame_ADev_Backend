package com.sofka.cardgame.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Carta implements ValueObject<Carta.Props>, Comparable<Carta> {

    private final String cartaId;
    private final Boolean estaOculta;
    private final Boolean estaHabilitada;
    private final Integer poder;

    private final String uri;

    public Carta(CartaMaestraId cartaId, Integer poder, Boolean estaOculta, Boolean estaHabilitada, String uri) {
        this.cartaId = cartaId.value();
        this.poder = poder;
        this.estaOculta = estaOculta;
        this.estaHabilitada = estaHabilitada;
        this.uri= uri;

    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Carta carta = (Carta) object;
        return Objects.equals(cartaId, carta.cartaId) && Objects.equals(estaOculta, carta.estaOculta) && Objects.equals(estaHabilitada, carta.estaHabilitada) && Objects.equals(poder, carta.poder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartaId, estaOculta, estaHabilitada, poder);
    }

    @Override
    public int compareTo(Carta carta) {
        return  this.poder - carta.poder;
    }


    @Override
    public Props value(){
        return new Props(){
            @Override
            public String cartaId(){
                return cartaId;
            }

            @Override
            public Integer poder(){
                return poder;
            }

            @Override
            public Boolean estaOculta(){
                return estaOculta;
            }

            @Override
            public Boolean estaHabilitada(){
                return estaHabilitada;
            }

            @Override
            public String uri() {
                return uri;
            }
        };
    }

    public interface Props{
        String cartaId();
        Integer poder();
        Boolean estaOculta();
        Boolean estaHabilitada();
        String uri();

    }


}


