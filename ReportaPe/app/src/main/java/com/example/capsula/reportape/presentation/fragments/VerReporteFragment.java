package com.example.capsula.reportape.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.capsula.reportape.R;
import com.example.capsula.reportape.core.BaseFragment;
import com.example.capsula.reportape.data.entities.ReporteSemaforo;
import com.example.capsula.reportape.presentation.activity.ReportarActivity;
import com.example.capsula.reportape.presentation.activity.VerReportesActivity;
import com.example.capsula.reportape.presentation.adapters.ReporteSemaforoAdaptador;
import com.example.capsula.reportape.presentation.contracts.VerReporteContract;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Desarrollo3 on 15/02/2017.
 */

public class VerReporteFragment extends BaseFragment implements VerReporteContract.View {

    @BindView(R.id.btn_reportar)
    Button btnReportar;
    @BindView(R.id.rvReportes)
    RecyclerView rvReportes;

    ReporteSemaforoAdaptador reporteSemaforoAdaptador;
    LinearLayoutManager linearLayoutManager;
    VerReporteContract.Presenter presenter;

    public static VerReporteFragment newInstance() {
        return new VerReporteFragment();
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

        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvReportes.setLayoutManager(linearLayoutManager);
        presenter.start();

    }

    @Override
    public void mostrarLista(ArrayList<ReporteSemaforo> reporteSemaforo) {
        reporteSemaforoAdaptador = new ReporteSemaforoAdaptador(reporteSemaforo, getActivity());
        rvReportes.setAdapter(reporteSemaforoAdaptador);
    }

    @Override
    public void verReporteDenegado(String error) {

    }

    @Override
    public void setPresenter(VerReporteContract.Presenter presenter) {
        this.presenter = presenter;

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active){

        }else {

        }
    }

    @OnClick(R.id.btn_reportar)
    public void onClick() {
        getActivity().onBackPressed();
    }
}
