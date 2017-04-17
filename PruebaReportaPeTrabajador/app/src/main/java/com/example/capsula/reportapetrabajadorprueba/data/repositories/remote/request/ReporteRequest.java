package com.example.capsula.reportapetrabajadorprueba.data.repositories.remote.request;



import com.example.capsula.reportapetrabajadorprueba.data.entities.ModelEstado;
import com.example.capsula.reportapetrabajadorprueba.data.entities.ModelLogin;
import com.example.capsula.reportapetrabajadorprueba.data.entities.ModelResponseEstado;
import com.example.capsula.reportapetrabajadorprueba.data.entities.ModelResponseLogin;
import com.example.capsula.reportapetrabajadorprueba.data.entities.Reporte;
import com.example.capsula.reportapetrabajadorprueba.data.entities.ReporteGrupal;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Desarrollo3 on 9/02/2017.
 */

public interface ReporteRequest {


    @GET("trabajadorreporte/reportetrabajadorporid/{trabajador_id}")
    Call<Reporte> obtenerReporteGrupal(
            @Path("trabajador_id") int trabajador_id
    );


    @POST("cambioestado/cambio")
    Call<ModelResponseEstado> cambioDeEstado (@Body ModelEstado data
    );


    /*@POST("cambioestado/cambio/{reportegrupal_id}")
    Call<ReporteGrupal> cambioEstado(@Path("reportegrupal_id") int reportegrupal_id
    );*/


}
