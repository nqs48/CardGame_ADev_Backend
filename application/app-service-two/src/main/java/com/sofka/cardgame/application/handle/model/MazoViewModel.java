package com.sofka.cardgame.application.handle.model;

import java.util.Objects;
import java.util.Set;

public class MazoViewModel {

    private Integer cantidad;
    private Set<Carta> cartas;
    private String juegoId;
    private String jugadorId;

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Set<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(Set<Carta> cartas) {
        this.cartas = cartas;
    }

    public String getJuegoId() {
        return juegoId;
    }

    public void setJuegoId(String juegoId) {
        this.juegoId = juegoId;
    }

    public String getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(String jugadorId) {
        this.jugadorId = jugadorId;
    }

    public static class Carta {
        private  String cartaId;
        private  Boolean estaOculta;
        private  Boolean estaHabilitada;
        private  Integer poder;
        private String uri;

        public String getCartaId() {
            return cartaId;
        }

        public void setCartaId(String cartaId) {
            this.cartaId = cartaId;
        }

        public Boolean getEstaOculta() {
            return estaOculta;
        }

        public void setEstaOculta(Boolean estaOculta) {
            this.estaOculta = estaOculta;
        }

        public Boolean getEstaHabilitada() {
            return estaHabilitada;
        }

        public void setEstaHabilitada(Boolean estaHabilitada) {
            this.estaHabilitada = estaHabilitada;
        }

        public Integer getPoder() {
            return poder;
        }

        public void setPoder(Integer poder) {
            this.poder = poder;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public Carta(String cartaId, Boolean estaOculta, Boolean estaHabilitada, Integer poder,
                     String url) {
            this.cartaId = cartaId;
            this.estaOculta = estaOculta;
            this.estaHabilitada = estaHabilitada;
            this.poder = poder;
            this.uri = uri;
        }

        public Carta() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Carta carta = (Carta) o;
            return Objects.equals(cartaId, carta.cartaId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(cartaId);
        }

    }
}
