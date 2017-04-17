package com.example.capsula.reportapetrabajadorprueba.presentation.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.capsula.reportapetrabajadorprueba.R;
import com.example.capsula.reportapetrabajadorprueba.core.BaseActivity;
import com.example.capsula.reportapetrabajadorprueba.core.BaseFragment;
import com.example.capsula.reportapetrabajadorprueba.presentation.activities.ReportesActivity;
import com.example.capsula.reportapetrabajadorprueba.presentation.contracts.LoginContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by linuxdesarrollo01 on 20/02/17.
 */

public class LoginFragment extends BaseFragment implements LoginContract.View {

    private LoginContract.Presenter presenter;

    @BindView(R.id.input_email)
    EditText inputEmail;
    @BindView(R.id.input_password)
    EditText inputPassword;
    @BindView(R.id.btn_login)
    AppCompatButton btnLogin;
    String correo;
    String contrasena;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        //nextActivity(getActivity(), null, ReportesActivity.class,true);

        presenter.loginApi(inputEmail.getText().toString(),inputPassword.getText().toString());
    }

    @Override
    public void loginSuccessfully() {
        loginDenied("Inicio de sesión exitosa");
        nextActivity(getActivity(), null, ReportesActivity.class,true);
    }

    @Override
    public void loginDenied(String response) {
        ((BaseActivity) getActivity()).showMessageError(response);

    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }
}
