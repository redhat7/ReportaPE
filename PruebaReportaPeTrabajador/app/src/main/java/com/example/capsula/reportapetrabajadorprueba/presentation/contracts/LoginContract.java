package com.example.capsula.reportapetrabajadorprueba.presentation.contracts;

import com.example.capsula.reportapetrabajadorprueba.core.BasePresenter;
import com.example.capsula.reportapetrabajadorprueba.core.BaseView;

/**
 * Created by linuxdesarrollo01 on 20/02/17.
 */

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void loginSuccessfully();
        void loginDenied(String response);
    }
    interface Presenter extends BasePresenter {
        void loginApi(String email, String password);

    }
}