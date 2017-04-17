package com.example.capsula.reportapetrabajadorprueba.data.entities;

import java.io.Serializable;

/**
 * Created by junior on 26/02/17.
 */

public class ModelResponseLogin implements Serializable {
    private int estado;
    private String mensaje;
    private Trabajador datos;

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Trabajador getDatos() {
        return datos;
    }

    public void setDatos(Trabajador datos) {
        this.datos = datos;
    }
}
