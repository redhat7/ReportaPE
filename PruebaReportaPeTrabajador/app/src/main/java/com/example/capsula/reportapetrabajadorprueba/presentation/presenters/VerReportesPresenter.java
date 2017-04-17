package com.example.capsula.reportapetrabajadorprueba.presentation.presenters;

import android.content.Context;

import com.example.capsula.reportapetrabajadorprueba.data.entities.ModelEstado;
import com.example.capsula.reportapetrabajadorprueba.data.entities.ModelLogin;
import com.example.capsula.reportapetrabajadorprueba.data.entities.ModelResponseEstado;
import com.example.capsula.reportapetrabajadorprueba.data.entities.ModelResponseLogin;
import com.example.capsula.reportapetrabajadorprueba.data.entities.Reporte;
import com.example.capsula.reportapetrabajadorprueba.data.repositories.local.SessionManager;
import com.example.capsula.reportapetrabajadorprueba.data.repositories.remote.ServiceGeneratorSimple;
import com.example.capsula.reportapetrabajadorprueba.data.repositories.remote.request.LoginRequest;
import com.example.capsula.reportapetrabajadorprueba.data.repositories.remote.request.ReporteRequest;
import com.example.capsula.reportapetrabajadorprueba.presentation.contracts.VerReportesContract;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Desarrollo3 on 15/02/2017.
 */

public class VerReportesPresenter implements VerReportesContract.Presenter {
    private VerReportesContract.View mView;
    Context context;
    SessionManager sessionManager;

    public VerReportesPresenter(VerReportesContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
        this.mView.setPresenter(this);

        //sessionManager = new SessionManager(context);
    }


    @Override
    public void start() {
        mView.setLoadingIndicator(true);
        sessionManager = new SessionManager(context);
        System.out.println("ID: "+ sessionManager.getId());
        ServiceGeneratorSimple serviceGeneratorSimple = new ServiceGeneratorSimple();
        Gson gsonDate = serviceGeneratorSimple.construyeGsonDeserializador();
        ReporteRequest reporteRequest = serviceGeneratorSimple.establecerConexionRestApi(gsonDate);
        //Call<Reporte> call = reporteRequest.obtenerReporteGrupal(789);
        Call<Reporte> call = reporteRequest.obtenerReporteGrupal(sessionManager.getId());
        call.enqueue(new Callback<Reporte>() {
            @Override
            public void onResponse(Call<Reporte> call, Response<Reporte> response) {
                if (!mView.isActive()){
                    return;
                }
                mView.setLoadingIndicator(false);
                if(response.isSuccessful()){
                    System.out.println(call+ "    ");
                    System.out.println(response);
                    mView.mostrarLista(response.body().datos);
                }else {
                    mView.verReporteDenegado("Hubo un error al mostrar la lista");
                }

            }

            @Override
            public void onFailure(Call<Reporte> call, Throwable t) {
                System.out.println("F A L L A ---> " + call);
                System.out.println(t);
                if (!mView.isActive()){
                    return;
                }
                mView.setLoadingIndicator(false);
                mView.verReporteDenegado("No se pudo acceder al servidor en este momento");
            }
        });
    }
}