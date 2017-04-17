package com.example.capsula.reportape.data.repositories.remote;


import com.example.capsula.reportape.data.repositories.remote.deserializador.ReporteDeserializador;
import com.example.capsula.reportape.data.repositories.remote.modelo.ReporteResponse;
import com.example.capsula.reportape.data.repositories.remote.request.ReporteRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by miguel on 06/02/17.
 */

public class ServiceGeneratorSimple {

    public static final String API_BASE_URL =  WS.root;

    private static OkHttpClient httpClient = new OkHttpClient();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S     createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }

    public ReporteRequest establecerConexionRestApi(Gson gson) {
        // Creamos un constructor Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                // recibe url base a la que se conecta
                .baseUrl("http://rockenwebperu.com/semaforo/v1/")
                //.addConverterFactory(GsonConverterFactory.create() ---> forma general
                .addConverterFactory(GsonConverterFactory.create())
                //GsonConverter.. lo que hace es transformar ese json en un map clave: valor
                .build();
        // luego de ejecutar la llamada, deja inicializado el objeto
        // accede a la clase para poder usar sus metodos
        return retrofit.create(ReporteRequest.class);
    }

    // Gson para recibir datos
    public Gson construyeGsonDeserializador() {
        // Construimos un gson
        GsonBuilder gsonBuilder = new GsonBuilder();
        // se hace coincidir reporteResponse con ReporteDeserializador
        gsonBuilder.registerTypeAdapter(ReporteResponse.class, new ReporteDeserializador());
        return gsonBuilder.create();
    }
}

    /*OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl("http://rockenwebperu.com/semaforo/v1/")
                        .addConverterFactory(GsonConverterFactory.create()
                        );

        Retrofit retrofit = builder.client(httpClient.build())
                .build();
    */




