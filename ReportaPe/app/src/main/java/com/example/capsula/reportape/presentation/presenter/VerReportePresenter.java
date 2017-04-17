package com.example.capsula.reportape.presentation.presenter;


import android.content.Context;

import com.example.capsula.reportape.data.entities.User;
import com.example.capsula.reportape.data.repositories.local.SessionManager;
import com.example.capsula.reportape.data.repositories.remote.ServiceGeneratorSimple;
import com.example.capsula.reportape.data.repositories.remote.request.ReporteRequest;
import com.example.capsula.reportape.presentation.contracts.ReportarContract;
import com.example.capsula.reportape.presentation.contracts.VerReporteContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Desarrollo3 on 15/02/2017.
 */

public class VerReportePresenter implements VerReporteContract.Presenter {
    private VerReporteContract.View mView;
    Context context;
    SessionManager sessionManager;

    public VerReportePresenter(VerReporteContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
        this.mView.setPresenter(this);

        sessionManager = new SessionManager(context);
    }


    @Override
    public void start() {
        mView.setLoadingIndicator(true);
        String email = sessionManager.getEmail();
        ReporteRequest reporteRequest = ServiceGeneratorSimple.createService(ReporteRequest.class);
        Call<User> call = reporteRequest.contributors(email);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!mView.isActive()){
                    return;
                }
                mView.setLoadingIndicator(false);
                if(response.isSuccessful()){
                    mView.mostrarLista(response.body().datos);
                }else {
                    mView.verReporteDenegado("Hubo un error al mostrar la lista");
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                if (!mView.isActive()){
                    return;
                }
                mView.setLoadingIndicator(false);
                mView.verReporteDenegado("No se pudo acceder al servidor en este momento");
            }
        });
    }
}
