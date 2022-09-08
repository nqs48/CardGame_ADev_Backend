package com.sofka.cardgame.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.values.JugadorId;
import com.sofka.cardgame.values.Mazo;

public class JugadorAgregado extends DomainEvent {

    private final JugadorId identity;
    private final String alias;
    private final Mazo mazo;

    public JugadorAgregado(JugadorId identity, String alias, Mazo mazo) {
        super("cardgame.jugadoragregado");
        this.identity = identity;
        this.alias = alias;
        this.mazo = mazo;
    }

    public JugadorId getIdentity() {
        return this.identity;
    }

    public String getAlias() {
        return this.alias;
    }

    public Mazo getMazo() {
        return this.mazo;
    }
}
