package com.sofka.cardgame.application.handle.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class TableroViewModel {

    private Ronda ronda;
    private Integer cantidadJugadores;
    private Set<String> jugadoresIniciales;
    private String jugadorPrincipalId;
    private Map<String, List<MazoViewModel.Carta>> cartas;


    public Ronda getRonda() {
        return ronda;
    }

    public void setRonda(Ronda ronda) {
        this.ronda = ronda;
    }

    public Integer getCantidadJugadores() {
        return cantidadJugadores;
    }

    public void setCantidadJugadores(Integer cantidadJugadores) {
        this.cantidadJugadores = cantidadJugadores;
    }

    public Set<String> getJugadoresIniciales() {
        return jugadoresIniciales;
    }

    public void setJugadoresIniciales(Set<String> jugadoresIniciales) {
        this.jugadoresIniciales = jugadoresIniciales;
    }

    public String getJugadorPrincipalId() {
        return jugadorPrincipalId;
    }

    public void setJugadorPrincipalId(String jugadorPrincipalId) {
        this.jugadorPrincipalId = jugadorPrincipalId;
    }

    public Map<String, List<MazoViewModel.Carta>> getCartas() {
        return cartas;
    }

    public void setCartas(Map<String, List<MazoViewModel.Carta>> cartas) {
        this.cartas = cartas;
    }


    public static class Ronda {
        private Integer tiempo;
        private Set<String> jugadores;
        private String numero;
        private Boolean estaIniciada;


        public Integer getTiempo() {
            return tiempo;
        }

        public void setTiempo(Integer tiempo) {
            this.tiempo = tiempo;
        }

        public Set<String> getJugadores() {
            return jugadores;
        }

        public void setJugadores(Set<String> jugadores) {
            this.jugadores = jugadores;
        }

        public String getNumero() {
            return numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }

        public Boolean getEstaIniciada() {
            return estaIniciada;
        }

        public void setEstaIniciada(Boolean estaIniciada) {
            this.estaIniciada = estaIniciada;
        }
    }

}
