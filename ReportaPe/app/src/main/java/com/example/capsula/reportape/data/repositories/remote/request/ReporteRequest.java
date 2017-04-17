package com.example.capsula.reportape.data.repositories.remote.request;

import com.example.capsula.reportape.data.entities.ReporteSemaforo;
import com.example.capsula.reportape.data.entities.User;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Desarrollo3 on 9/02/2017.
 */

public interface ReporteRequest {

    @GET("reporteusuario/reporteporcorreo/{reporteusuario_correo}")
    Call<User> contributors(
            @Path("reporteusuario_correo") String reporteusuario_correo
    );

    @POST("registrarreporteusuario/insertalo")
    Call<ReporteSemaforo> enviarReporte(@Body ReporteSemaforo enviarReporte);


}
