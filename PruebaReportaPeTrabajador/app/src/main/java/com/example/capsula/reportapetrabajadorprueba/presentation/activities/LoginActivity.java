package com.example.capsula.reportapetrabajadorprueba.presentation.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.capsula.reportapetrabajadorprueba.R;
import com.example.capsula.reportapetrabajadorprueba.core.BaseActivity;
import com.example.capsula.reportapetrabajadorprueba.presentation.fragments.LoginFragment;
import com.example.capsula.reportapetrabajadorprueba.presentation.presenters.LoginPresenter;
import com.example.capsula.reportapetrabajadorprueba.utils.ActivityUtils;

/**
 * Created by linuxdesarrollo01 on 20/02/17.
 */

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.body_login);
        if (fragment == null){
            fragment = LoginFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),fragment,R.id.body_login);
        }

        new LoginPresenter(fragment , getApplicationContext());

    }
}
