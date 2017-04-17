package com.example.capsula.reportape.presentation.adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.Resource;
import com.example.capsula.reportape.R;
import com.example.capsula.reportape.data.entities.ReporteSemaforo;

import java.util.ArrayList;

/**
 * Created by Dise07 on 30/12/2016.
 */

public class ReporteSemaforoAdaptador extends RecyclerView.Adapter<ReporteSemaforoAdaptador.ReporteSemaforoViewHolder> {

    ArrayList<ReporteSemaforo> reportes;
    Activity activity;
    byte [] imageAsBytes;
    String imageString;



    public ReporteSemaforoAdaptador(ArrayList<ReporteSemaforo> reportes, Activity activity) {
        this.reportes  = reportes;
        this.activity   = activity;
    }

    //Inflar el layout y lo pasará al viewholder para que le obtenga la lista
    public ReporteSemaforoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_rs, parent, false);
        //Esta linea de codigo asocia el layour cardview_contacto con el MainActivity

        return new ReporteSemaforoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ReporteSemaforoViewHolder reporteSemaforoViewHolder, int position) {
        //Aca pasamos la lista de reportes
        final ReporteSemaforo reporteSemaforo = reportes.get(position);

        reporteSemaforoViewHolder.tvfechaReporte.setText(reporteSemaforo.reporteusuario_fechahora);
        reporteSemaforoViewHolder.tvDireccionReporte.setText(reporteSemaforo.reporteusuario_descripcion);
        if(reporteSemaforo.reporteusuario_estado == 2){
            reporteSemaforoViewHolder.tvEstado.setText("Atendido");
            reporteSemaforoViewHolder.tvEstado.setTextColor
                    (reporteSemaforoViewHolder.tvEstado.getContext().getResources().getColor(R.color.colorGreen));
        }else{
            reporteSemaforoViewHolder.tvEstado.setText("No Atendido");
        }
        if(BitmapFactory.decodeFile(reporteSemaforo.reporteusuario_ruta)== null){
            reporteSemaforoViewHolder.imgReporte.setImageResource(R.drawable.foto_pixeleada);
        }else{
            reporteSemaforoViewHolder.imgReporte.setImageBitmap(BitmapFactory.decodeFile(reporteSemaforo.reporteusuario_ruta));
        }
    }

    @Override
    public int getItemCount() {
        //Cantidad de elementos que contiene la cardview
        return reportes.size();
    }


    public static class ReporteSemaforoViewHolder extends RecyclerView.ViewHolder{
        //Esta clase es la que interacciona con el layout
        private TextView tvEstado;
        private TextView tvDireccionReporte;
        private TextView tvfechaReporte;
        private ImageView imgReporte;

        public ReporteSemaforoViewHolder(View itemView) {
            super(itemView);

            tvEstado        = (TextView) itemView.findViewById(R.id.estado);
            tvDireccionReporte  = (TextView) itemView.findViewById(R.id.direccion);
            tvfechaReporte      = (TextView) itemView.findViewById(R.id.fecha);
            imgReporte = (ImageView) itemView.findViewById(R.id.img_reporte);
        }
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
