package com.example.capsula.reportape.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ejdez on 13/01/2017.
 */

public class User {
    public String estado;
    @SerializedName("datos")
    public ArrayList<ReporteSemaforo> datos;

    public User(String estado, ArrayList<ReporteSemaforo> datos) {
        this.estado=estado;
        this.datos=datos;
    }
}
