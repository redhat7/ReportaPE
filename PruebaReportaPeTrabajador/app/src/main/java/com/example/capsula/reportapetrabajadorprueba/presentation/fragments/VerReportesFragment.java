package com.example.capsula.reportapetrabajadorprueba.presentation.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.capsula.reportapetrabajadorprueba.R;
import com.example.capsula.reportapetrabajadorprueba.core.BaseFragment;
import com.example.capsula.reportapetrabajadorprueba.data.entities.ReporteGrupal;
import com.example.capsula.reportapetrabajadorprueba.data.repositories.local.SessionManager;
import com.example.capsula.reportapetrabajadorprueba.presentation.adapters.ReportesAdapter;
import com.example.capsula.reportapetrabajadorprueba.presentation.contracts.VerReportesContract;
import com.example.capsula.reportapetrabajadorprueba.services.GPSTracker;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by Desarrollo3 on 15/02/2017.
 */

public class VerReportesFragment extends BaseFragment implements VerReportesContract.View{
    //Variables PERMISOS LOCALIZACION
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_NETWORK_STATE
    };
    ReportesAdapter reportesAdapter;
    LinearLayoutManager linearLayoutManager;

    VerReportesContract.Presenter presenter;
    @BindView(R.id.Reportes)
    RecyclerView Reportes;

    SessionManager sessionManager;
    GPSTracker gpsTracker;
    @BindView(R.id.swipeRefreshLayout)

     SwipeRefreshLayout swipeRefreshLayout;

    public static VerReportesFragment newInstance() {
        return new VerReportesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recicleviewrs, container, false);
        ButterKnife.bind(this, v);
        return v;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        verifyStoragePermissions(getActivity());
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        Reportes.setLayoutManager(linearLayoutManager);
        presenter.start();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    // Refresh items

                    refreshItems();
                }
            });

    }
    void refreshItems() {
        // Load items
        // ...
        presenter.start();
        // Load complete
        onItemsLoadComplete();
    }

    void onItemsLoadComplete() {
        // Update the adapter and notify data set changed
        // ...
        reportesAdapter.notifyDataSetChanged();
        // Stop refresh animation
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void mostrarLista(ArrayList<ReporteGrupal> reporteGrupal) {
        Collections.shuffle(reporteGrupal);
        reportesAdapter = new ReportesAdapter(reporteGrupal, getActivity());
        Reportes.setAdapter(reportesAdapter);
    }
    public void verifyStoragePermissions(final Activity activity) {
        // Check if we have write permission
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
                new SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Atención!")
                        .setContentText("Debes otorgar los permisos para continuar")
                        .setConfirmText("Solicitar Permiso")
                        .setCancelText("Cancelar")
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.cancel();
                                activity.finish();
                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.cancel();
                                ActivityCompat.requestPermissions(activity, PERMISSIONS,
                                        REQUEST_EXTERNAL_STORAGE);
                                gpsTracker = new GPSTracker(getContext());
                                if (gpsTracker.canGetLocation()) {

                                } else {
                                    // can't get location
                                    gpsTracker.showSettingsAlert();
                                }
                            }
                        })
                        .show();
            } else {

                new SweetAlertDialog(activity, SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText("Atención!")
                        .setContentText("Debes otorgar los permisos de localización para continuar")
                        .setConfirmText("Solicitar Permiso")
                        .setCancelText("Cancelar")
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.cancel();
                                activity.finish();
                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.cancel();
                                ActivityCompat.requestPermissions(activity, PERMISSIONS,
                                        REQUEST_EXTERNAL_STORAGE);
                                gpsTracker = new GPSTracker(getContext());
                                if (gpsTracker.canGetLocation()) {

                                } else {
                                    // can't get location
                                    gpsTracker.showSettingsAlert();
                                }
                            }
                        })
                        .show();

            }
        }
    }

    @Override
    public void verReporteDenegado(String error) {
    }

    @Override
    public void setPresenter(VerReportesContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active) {

        } else {
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}

