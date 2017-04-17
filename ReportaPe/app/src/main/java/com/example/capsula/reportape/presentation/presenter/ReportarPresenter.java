package com.example.capsula.reportape.presentation.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.capsula.reportape.core.BaseActivity;
import com.example.capsula.reportape.core.BaseFragment;
import com.example.capsula.reportape.data.entities.ReporteSemaforo;
import com.example.capsula.reportape.data.repositories.remote.ServiceGeneratorSimple;
import com.example.capsula.reportape.data.repositories.remote.request.ReporteRequest;
import com.example.capsula.reportape.presentation.activity.VerReportesActivity;
import com.example.capsula.reportape.presentation.contracts.ReportarContract;
import com.example.capsula.reportape.presentation.fragments.ReportarFragment;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Desarrollo3 on 9/02/2017.
 */

public class ReportarPresenter implements ReportarContract.Presenter {
    private ReportarContract.View mView;

    public ReportarPresenter(ReportarContract.View mView, Context context) {
        this.mView = mView;
        this.mView.setPresenter(this);
    }
    @Override
    public void reporteApi(String foto, String carpeta, String email , String latitud, String longitud) {
        mView.setLoadingIndicator(true);
        ServiceGeneratorSimple restApiAdapter = new ServiceGeneratorSimple();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializador();
        ReporteRequest endpointsApi = restApiAdapter.establecerConexionRestApi(gsonMediaRecent);
        //-12.0538289,-77.0859132
        //-12.0726125,-77.0801126
        //-12.0792833,-77.0845573
        System.out.println( "F O T O: " + carpeta);
        ReporteSemaforo enviarReporte = new ReporteSemaforo(foto, carpeta, email,latitud,longitud);
        Call<ReporteSemaforo> call = endpointsApi.enviarReporte(enviarReporte);
        call.enqueue(new Callback<ReporteSemaforo>() {
            @Override
            public void onResponse(Call<ReporteSemaforo> call, Response<ReporteSemaforo> response) {

                if (!mView.isActive()){
                    return;
                }
                mView.setLoadingIndicator(false);
                if(response.isSuccessful()){
                    mView.reportSuccessfully();
                }else {
                    mView.reportDenied("Error al enviar reporte");
                }
            }
            @Override
            public void onFailure(Call<ReporteSemaforo> call, Throwable t) {
                if (!mView.isActive()){
                    return;
                }
                mView.setLoadingIndicator(false);
                mView.reportDenied("No se pudo acceder al servidor en este momento");
            }
        });
    }

    @Override
    public void start() {

    }
}
