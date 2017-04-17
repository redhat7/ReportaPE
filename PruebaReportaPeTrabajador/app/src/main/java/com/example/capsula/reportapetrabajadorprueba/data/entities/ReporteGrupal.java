package com.example.capsula.reportapetrabajadorprueba.data.entities;

import java.util.Date;

/**
 * Created by linuxdesarrollo01 on 20/02/17.
 */

public class ReporteGrupal {
    public int reportegrupal_id;
    public String reportegrupal_estado;
    public String reportegrupal_contador;
    public String reportegrupal_latitud;
    public String reportegrupal_longitud;
    public Date reportegrupal_fechahora;
    public String reportegrupal_direccion;

    public ReporteGrupal(int reportegrupal_id, String reportegrupal_estado, String reportegrupal_contador) {
        this.reportegrupal_id = reportegrupal_id;
        this.reportegrupal_estado = reportegrupal_estado;
        this.reportegrupal_contador = reportegrupal_contador;
    }




}
