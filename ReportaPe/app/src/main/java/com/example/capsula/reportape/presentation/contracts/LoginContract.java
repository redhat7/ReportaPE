package com.example.capsula.reportape.presentation.contracts;

import com.example.capsula.reportape.core.BasePresenter;
import com.example.capsula.reportape.core.BaseView;

/**
 * Created by miguel on 06/02/17.
 */

public interface LoginContract {
    interface View extends BaseView<Presenter>{
        void loginSuccessfully();
        void loginDenied(String response);
    }
    interface Presenter extends BasePresenter{
        void loginApi(String email);
    }
}
