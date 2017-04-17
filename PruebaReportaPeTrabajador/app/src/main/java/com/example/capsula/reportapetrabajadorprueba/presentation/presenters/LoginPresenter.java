package com.example.capsula.reportapetrabajadorprueba.presentation.presenters;

import android.content.Context;

import com.example.capsula.reportapetrabajadorprueba.data.entities.ModelLogin;
import com.example.capsula.reportapetrabajadorprueba.data.entities.ModelResponseLogin;
import com.example.capsula.reportapetrabajadorprueba.data.repositories.local.SessionManager;
import com.example.capsula.reportapetrabajadorprueba.data.repositories.remote.ServiceGeneratorSimple;
import com.example.capsula.reportapetrabajadorprueba.data.repositories.remote.request.LoginRequest;
import com.example.capsula.reportapetrabajadorprueba.presentation.contracts.LoginContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by linuxdesarrollo01 on 20/02/17.
 */

public class LoginPresenter implements LoginContract.Presenter{
    private LoginContract.View mView;
    private SessionManager sessionManager;
    private Context context;

    public LoginPresenter(LoginContract.View mView, Context context) {
        this.mView = mView;
        this.sessionManager = new SessionManager(context);
        this.mView.setPresenter(this);
        this.context = context;
    }


    @Override
    public void start() {

    }

    @Override
    public void loginApi(String email, String password) {
        mView.setLoadingIndicator(true);
        LoginRequest loginRequest1 =  ServiceGeneratorSimple.createService(LoginRequest.class);
        ModelLogin modelLogin = new ModelLogin(email,password);
        Call<ModelResponseLogin> call = loginRequest1.loginBody(modelLogin);
        call.enqueue(new Callback<ModelResponseLogin>() {
            @Override
            public void onResponse(Call<ModelResponseLogin> call, Response<ModelResponseLogin> response) {
                if (!mView.isActive()){
                    return;
                }
                mView.setLoadingIndicator(false);
                if(response.isSuccessful()){
                    System.out.println("ID --> " + response.body().getDatos().trabajador_id);
                    sessionManager = new SessionManager(context);
                    sessionManager.saveId(response.body().getDatos().trabajador_id);
                    mView.loginSuccessfully();
                }
                else {
                    mView.loginDenied("Correo incorrecto");
                }

            }

            @Override
            public void onFailure(Call<ModelResponseLogin> call, Throwable t) {
                System.out.println("NO ENVIADO");
                System.out.println("error: " + t.toString());
            }
    });
    }
}