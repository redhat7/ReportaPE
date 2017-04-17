package com.example.capsula.reportape.presentation.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.capsula.reportape.R;

import com.example.capsula.reportape.data.repositories.local.SessionManager;
import com.example.capsula.reportape.presentation.fragments.VerReporteFragment;
import com.example.capsula.reportape.presentation.presenter.VerReportePresenter;
import com.example.capsula.reportape.utils.ActivityUtils;

/**
 * Created by Desarrollo3 on 8/02/2017.
 */

public class VerReportesActivity extends AppCompatActivity {
    Activity activity;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_reportes);
        activity = this;
        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        // Fragment

        VerReporteFragment fragment = (VerReporteFragment) getSupportFragmentManager().findFragmentById(R.id.bodyVerReportes);
        if(fragment == null){
            fragment = VerReporteFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putString("email", email);
            // Envía el parámetro recogido a camara_fragment
            fragment.setArguments(bundle);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),fragment,R.id.bodyVerReportes);
        }

        new VerReportePresenter(fragment , getApplicationContext());

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
