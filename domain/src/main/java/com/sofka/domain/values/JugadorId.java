package com.sofka.domain.values;

import co.com.sofka.domain.generic.Identity;

public class JugadorId extends Identity {

    private JugadorId(String jugadorId){
        super(jugadorId);
    }

    public static JugadorId of(String id){
        return new JugadorId(id);
    }


}
