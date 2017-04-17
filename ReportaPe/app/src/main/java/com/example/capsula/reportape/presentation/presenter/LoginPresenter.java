package com.example.capsula.reportape.presentation.presenter;

import android.content.Context;

import com.example.capsula.reportape.data.entities.Login;
import com.example.capsula.reportape.data.repositories.local.SessionManager;
import com.example.capsula.reportape.data.repositories.remote.ServiceGeneratorSimple;
import com.example.capsula.reportape.data.repositories.remote.request.LoginRequest;
import com.example.capsula.reportape.presentation.contracts.LoginContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Desarrollo3 on 8/02/2017.
 */

public class LoginPresenter implements LoginContract.Presenter{
    private LoginContract.View mView;
    private SessionManager sessionManager;


    public LoginPresenter(LoginContract.View mView, Context context) {
        this.mView = mView;
        this.sessionManager = new SessionManager(context);
        this.mView.setPresenter(this);
    }

    @Override
    public void loginApi(String email) {
        LoginRequest loginRequest = ServiceGeneratorSimple.createService(LoginRequest.class);
        Login login = new Login(email);
        Call<Login> call = loginRequest.login(login);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it
                System.out.println(response.body());
                System.out.println(response.message());
                System.out.println("ENVIADO");
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                // the network call was a failure
                // TODO: handle error
                System.out.println("NO ENVIADO");
                System.out.println("error: " + t.toString());
            }
        });
    }

    @Override
    public void start() {

    }
}
