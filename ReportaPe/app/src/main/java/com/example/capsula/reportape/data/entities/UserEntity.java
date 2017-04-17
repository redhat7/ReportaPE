package com.example.capsula.reportape.data.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Desarrollo3 on 9/02/2017.
 */

public class UserEntity implements Serializable{
    @SerializedName("usuario_correo")
    public String usuario_correo;
    public String usuario_token;
    public int usuario_id;


    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getUsuario_correo() {
        return usuario_correo;
    }

    public void setUsuario_correo(String usuario_correo) {
        this.usuario_correo = usuario_correo;
    }

    public String getUsuario_token() {
        return usuario_token;
    }

    public void setUsuario_token(String usuario_token) {
        this.usuario_token = usuario_token;
    }

}
