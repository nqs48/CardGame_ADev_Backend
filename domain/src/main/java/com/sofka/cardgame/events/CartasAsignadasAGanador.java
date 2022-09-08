package com.sofka.cardgame.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgame.values.Carta;
import com.sofka.cardgame.values.JugadorId;

import java.util.Set;

public class CartasAsignadasAGanador extends DomainEvent {
    private final JugadorId ganadorId;
    private final Integer puntos;
    private final Set<Carta> cartasApostadas;
    public CartasAsignadasAGanador(JugadorId ganadorId, Integer puntos, Set<Carta> cartasApostadas) {
        super("cardgame.cartasasignadasaganador");
        this.ganadorId= ganadorId;
        this.puntos= puntos;
        this.cartasApostadas= cartasApostadas;
    }

    public JugadorId getGanadorId(){
        return this.ganadorId;
    }

    public Integer getPuntos(){
        return this.puntos;
    }

    public Set<Carta> getCartasApuesta() {
        return this.cartasApostadas;
    }


}
