package com.example.capsula.reportape.presentation.contracts;

import android.support.annotation.NonNull;

import com.example.capsula.reportape.core.BasePresenter;
import com.example.capsula.reportape.core.BaseView;
import com.example.capsula.reportape.data.entities.ReporteSemaforo;
import com.google.android.gms.auth.api.credentials.internal.SaveRequest;
import com.google.android.gms.common.api.Status;

import java.util.ArrayList;

/**
 * Created by Desarrollo3 on 15/02/2017.
 */

public interface VerReporteContract {
    interface View extends BaseView<Presenter> {
        void mostrarLista(ArrayList<ReporteSemaforo> reporteSemaforo);
        void verReporteDenegado(String error);
    }
    interface Presenter extends BasePresenter {

    }
}

