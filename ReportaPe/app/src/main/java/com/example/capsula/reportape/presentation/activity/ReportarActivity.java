package com.example.capsula.reportape.presentation.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.capsula.reportape.R;
import com.example.capsula.reportape.core.BaseActivity;
import com.example.capsula.reportape.data.repositories.local.SessionManager;
import com.example.capsula.reportape.presentation.fragments.LoginFragment;
import com.example.capsula.reportape.presentation.fragments.ReportarFragment;
import com.example.capsula.reportape.presentation.presenter.ReportarPresenter;
import com.example.capsula.reportape.utils.ActivityUtils;

/**
 * Created by Desarrollo3 on 7/02/2017.
 */

public class ReportarActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Activity activity;
    SessionManager sessionManager;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegador);
        activity = this;
        sessionManager = new SessionManager(getApplicationContext());

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Obtener Email
        //email = Email();

        // Fragment
        ReportarFragment fragment = (ReportarFragment) getSupportFragmentManager().findFragmentById(R.id.bodyReportar);
        if(fragment == null){
            fragment = ReportarFragment.newInstance();
            Bundle bundle = new Bundle();
            //bundle.putString("email", email);
            // Envía el parámetro recogido a camara_fragment
            fragment.setArguments(bundle);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),fragment,R.id.bodyReportar);
        }

        //NavigationDrawe
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_navegador);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Conectamos con el Presenter

        new ReportarPresenter(fragment,getApplicationContext());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

         if (id == R.id.nav_reportes) {
             Bundle bundle = new Bundle();
             //bundle.putString("email" , email);
             nextActivity(activity, bundle, VerReportesActivity.class , false);

        } else if (id == R.id.nav_nosotros) {
            nextActivity(activity, null, NosotrosActivity.class , false);

        } else if (id == R.id.nav_condiciones) {
            nextActivity(activity, null, CondicionesActivity.class , false);

        } else if (id == R.id.nav_valorar) {

        } else if (id == R.id.nav_sesion) {
             sessionManager.closeSession();
             nextActivity(activity, null, LandingActivity.class, false);
             //LoginFragment loginFragment = new LoginFragment();
             //loginFragment.signOut();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_navegador);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
