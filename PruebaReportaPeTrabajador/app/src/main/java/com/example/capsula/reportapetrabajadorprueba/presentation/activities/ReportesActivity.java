package com.example.capsula.reportapetrabajadorprueba.presentation.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.capsula.reportapetrabajadorprueba.R;
import com.example.capsula.reportapetrabajadorprueba.core.BaseActivity;
import com.example.capsula.reportapetrabajadorprueba.presentation.fragments.VerReportesFragment;
import com.example.capsula.reportapetrabajadorprueba.presentation.presenters.VerReportesPresenter;
import com.example.capsula.reportapetrabajadorprueba.utils.ActivityUtils;

/**
 * Created by linuxdesarrollo01 on 20/02/17.
 */

public class ReportesActivity extends BaseActivity {


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("vista reportes creada");
        setContentView(R.layout.activity_reportes);
        VerReportesFragment fragment = (VerReportesFragment) getSupportFragmentManager().findFragmentById(R.id.body_reportes);
        if (fragment == null){
            fragment = VerReportesFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),fragment,R.id.body_reportes);
        }

        new VerReportesPresenter(fragment , getApplicationContext());

    }

    @Override
    protected void onDestroy() {
        System.out.println("Vista reportes, destruida");
        super.onDestroy();
    }

}
