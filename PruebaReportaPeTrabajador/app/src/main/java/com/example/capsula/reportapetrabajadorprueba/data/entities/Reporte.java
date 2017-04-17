package com.example.capsula.reportapetrabajadorprueba.data.entities;

import java.util.ArrayList;

/**
 * Created by linuxdesarrollo01 on 20/02/17.
 */

public class Reporte {

    public String estado;
    public ArrayList<ReporteGrupal> datos;
    public String mensaje;

    public Reporte(String estado, ArrayList<ReporteGrupal> datos, String mensaje) {
        this.estado = estado;
        this.datos = datos;
        this.mensaje = mensaje;
    }

}
