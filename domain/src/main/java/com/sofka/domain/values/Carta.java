package com.sofka.domain.values;

import co.com.sofka.domain.generic.ValueObject;

public class Carta implements ValueObject<Carta.Props> {

    private final CartaMaestraId cartaId;
    private final Integer poder;

    private final Boolean estaOculta;

    private final Boolean estaHabilitada;



    public Carta(CartaMaestraId cartaId, Integer poder, Boolean estaOculta, Boolean estaHabilitada) {
        this.cartaId = cartaId;
        this.poder = poder;
        this.estaOculta = estaOculta;
        this.estaHabilitada = estaHabilitada;

    }


    @Override
    public Props value(){
        return new Props(){
            @Override
            public CartaMaestraId cartaId(){
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
        };
    }

    public interface Props{
        CartaMaestraId cartaId();
        Integer poder();
        Boolean estaOculta();
        Boolean estaHabilitada();

    }


}


