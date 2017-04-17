package com.example.capsula.reportape.data.repositories.remote.request;

import com.example.capsula.reportape.data.entities.Login;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Desarrollo3 on 9/02/2017.
 */

public interface LoginRequest {

    //Enviar Login
    @POST("login/registro_login")
    Call<Login> login(@Body Login login);

}
