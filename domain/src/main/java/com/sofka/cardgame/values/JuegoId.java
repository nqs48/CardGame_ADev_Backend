package com.sofka.cardgame.values;

import co.com.sofka.domain.generic.Identity;

public class JuegoId extends Identity {

    public JuegoId(String id) {
        super(id);
    }

    public JuegoId(){}

    public static JuegoId of(String juegoId) {
        return new JuegoId(juegoId);
    }

}

