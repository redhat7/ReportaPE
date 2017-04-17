package com.example.capsula.reportape.data.repositories.remote.modelo;

import com.example.capsula.reportape.data.entities.ReporteSemaforo;


import java.util.ArrayList;

/**
 * Created by Desarrollo3 on 11/01/2017.
 */

public class ReporteResponse {

    // Modelo
    ArrayList<ReporteSemaforo> reportes;
    public ArrayList<ReporteSemaforo> getReportes() {
        return reportes;
    }
    public void setReportes(ArrayList<ReporteSemaforo> reportes) {
        this.reportes = reportes;
    }
}
