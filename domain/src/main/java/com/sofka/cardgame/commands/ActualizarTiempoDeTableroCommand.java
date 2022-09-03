package com.sofka.cardgame.commands;

import co.com.sofka.domain.generic.Command;
import com.sofka.cardgame.values.JuegoId;
import com.sofka.cardgame.values.TableroId;

public class ActualizarTiempoDeTableroCommand extends Command {
    private JuegoId juegoId;
    private TableroId tableroId;
    private Integer tiempo;


    //Getters y Setters
    public Integer getTiempo() {
        return tiempo;
    }

    public void setJuegoId(JuegoId juegoId) {
        this.juegoId = juegoId;
    }

    public JuegoId getJuegoId() {
        return juegoId;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public TableroId getTableroId() {
        return tableroId;
    }

    public void setTableroId(TableroId tableroId) {
        this.tableroId = tableroId;
    }
}
