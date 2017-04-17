package com.example.capsula.reportape.presentation.contracts;

import android.support.annotation.NonNull;

import com.example.capsula.reportape.core.BasePresenter;
import com.example.capsula.reportape.core.BaseView;
import com.google.android.gms.common.api.Status;

/**
 * Created by Desarrollo3 on 9/02/2017.
 */

public interface ReportarContract {
    interface View extends BaseView<Presenter> {
        void onResult(@NonNull Status status);

        void reportSuccessfully();
        void reportDenied(String response);

    }
    interface Presenter extends BasePresenter {
        void reporteApi( String foto, String ruta, String email , String latitud, String longitud );
    }
}
