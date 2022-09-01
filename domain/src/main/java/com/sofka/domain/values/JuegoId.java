package com.sofka.domain.values;

import co.com.sofka.domain.generic.Identity;

public class JuegoId extends Identity {

    public JuegoId(String id){
        super(id);
    }

    public static JuegoId of(String id){
        return new JuegoId(id);
    }
}
