package com.example.capsula.reportape.presentation.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capsula.reportape.R;
import com.example.capsula.reportape.core.BaseActivity;

import com.example.capsula.reportape.presentation.fragments.LoginFragment;
import com.example.capsula.reportape.presentation.presenter.LoginPresenter;
import com.example.capsula.reportape.utils.ActivityUtils;

/**
 * Created by Desarrollo3 on 7/02/2017.
 */

public class LoginActivity extends BaseActivity {

    private Context context;
    private Activity activity;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.bodyLogin);
        if(fragment == null){
            fragment = LoginFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),fragment,R.id.bodyLogin);
        }

        new LoginPresenter(fragment,getApplicationContext());
    }
}