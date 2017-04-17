package com.example.capsula.reportapetrabajadorprueba.data.repositories.remote.request;


import com.example.capsula.reportapetrabajadorprueba.data.entities.ModelLogin;
import com.example.capsula.reportapetrabajadorprueba.data.entities.ModelResponseLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Desarrollo3 on 9/02/2017.
 */

public interface LoginRequest {

    @POST("vertrabajador/login")
    Call<ModelResponseLogin> loginBody(@Body ModelLogin data
    );



}
