package com.example.capsula.reportape.data.entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ejdez on 13/01/2017.
 */

public class UserSerializable implements Serializable{
    public String estado;
    private String mensaje;
    private UserEntity datos;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public UserEntity getDatos() {
        return datos;
    }

    public void setDatos(UserEntity datos) {
        this.datos = datos;
    }





}
