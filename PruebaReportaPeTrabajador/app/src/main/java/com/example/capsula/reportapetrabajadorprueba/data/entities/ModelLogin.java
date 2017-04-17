package com.example.capsula.reportapetrabajadorprueba.data.entities;

import java.io.Serializable;

/**
 * Created by junior on 26/02/17.
 */

public class ModelLogin implements Serializable {
    private String trabajador_correo;
    private String trabajador_contrasena;

    public ModelLogin(String trabajador_correo, String trabajador_contrasena) {
        this.trabajador_correo = trabajador_correo;
        this.trabajador_contrasena = trabajador_contrasena;
    }

    public String getTrabajador_correo() {
        return trabajador_correo;
    }

    public void setTrabajador_correo(String trabajador_correo) {
        this.trabajador_correo = trabajador_correo;
    }

    public String getTrabajador_contrasena() {
        return trabajador_contrasena;
    }

    public void setTrabajador_contrasena(String trabajador_contrasena) {
        this.trabajador_contrasena = trabajador_contrasena;
    }
}
