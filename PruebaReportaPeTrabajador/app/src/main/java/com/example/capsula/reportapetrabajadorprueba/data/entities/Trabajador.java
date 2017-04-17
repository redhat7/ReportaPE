package com.example.capsula.reportapetrabajadorprueba.data.entities;

import java.io.Serializable;

/**
 * Created by Desarrollo3 on 24/02/2017.
 */

public class Trabajador  implements Serializable{
    public int trabajador_id;
    public String trabajador_nombre;
    public String trabajador_correo;
    public String trabajador_contrasena;
    public int trabajador_estado;
    public String trabajador_codigo;
    public String tipotrabajador_id;


    public Trabajador ( String email , String password ){
        this.trabajador_correo = email;
        this.trabajador_contrasena = password;
    }

    public int getTrabajador_id() {
        return trabajador_id;
    }

    public void setTrabajador_id(int trabajador_id) {
        this.trabajador_id = trabajador_id;
    }

    public String getTrabajador_nombre() {
        return trabajador_nombre;
    }

    public void setTrabajador_nombre(String trabajador_nombre) {
        this.trabajador_nombre = trabajador_nombre;
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

    public int getTrabajador_estado() {
        return trabajador_estado;
    }

    public void setTrabajador_estado(int trabajador_estado) {
        this.trabajador_estado = trabajador_estado;
    }

    public String getTrabajador_codigo() {
        return trabajador_codigo;
    }

    public void setTrabajador_codigo(String trabajador_codigo) {
        this.trabajador_codigo = trabajador_codigo;
    }

    public String getTipotrabajador_id() {
        return tipotrabajador_id;
    }

    public void setTipotrabajador_id(String tipotrabajador_id) {
        this.tipotrabajador_id = tipotrabajador_id;
    }



    public class Datos implements Serializable{

    }

}



