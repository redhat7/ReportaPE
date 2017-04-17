package com.example.capsula.reportapetrabajadorprueba.presentation.contracts;


import com.example.capsula.reportapetrabajadorprueba.core.BasePresenter;
import com.example.capsula.reportapetrabajadorprueba.core.BaseView;
import com.example.capsula.reportapetrabajadorprueba.data.entities.ReporteGrupal;

import java.util.ArrayList;

/**
 * Created by Desarrollo3 on 21/02/2017.
 */

public interface VerReportesContract {
    interface View extends BaseView<Presenter> {
        void mostrarLista(ArrayList<ReporteGrupal> reporteGrupal);
        void verReporteDenegado(String error);
    }
    interface Presenter extends BasePresenter {
    }
}
