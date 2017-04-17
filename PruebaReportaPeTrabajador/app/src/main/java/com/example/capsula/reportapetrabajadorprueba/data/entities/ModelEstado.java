package com.example.capsula.reportapetrabajadorprueba.data.entities;

import android.graphics.PorterDuff;

/**
 * Created by Desarrollo3 on 1/03/2017.
 */

public class ModelEstado {

    private int reportegrupal_id;

    public ModelEstado(int reportegrupal_id){
        this.reportegrupal_id = reportegrupal_id;
    }

    public int getReportegrupal_id() {
        return reportegrupal_id;
    }

    public void setReportegrupal_id(int reportegrupal_id) {
        this.reportegrupal_id = reportegrupal_id;
    }


}
