package com.example.capsula.reportape.presentation.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.capsula.reportape.R;
import com.example.capsula.reportape.core.BaseActivity;
import com.example.capsula.reportape.core.BaseFragment;
import com.example.capsula.reportape.data.entities.Ubicacion;
import com.example.capsula.reportape.data.repositories.local.SessionManager;
import com.example.capsula.reportape.presentation.activity.VerReportesActivity;
import com.example.capsula.reportape.presentation.contracts.ReportarContract;
import com.example.capsula.reportape.services.GPSTracker;
import com.google.android.gms.common.api.Status;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Desarrollo3 on 8/02/2017.
 */

public class ReportarFragment extends BaseFragment implements ReportarContract.View, EasyPermissions.PermissionCallbacks{
    // Variables Camara
    private static final int CAMARA_PERMISSIONS = 1007;
    private static String carpeta;
    private File photo = null;
    private Bitmap bitmap;

    // Variables Localizacion
    double latitud, longitud;

    //Variables PERMISOS LOCALIZACION
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_NETWORK_STATE
    };
    GPSTracker gpsTracker;

    @BindView(R.id.semafoMro_reporte)
    ImageView semafoMroReporte;
    @BindView(R.id.ic_posicion)
    ImageView icPosicion;
    @BindView(R.id.btn_cancelar)
    Button btnCancelar;
    @BindView(R.id.btn_enviar)
    Button btnEnviar;
    @BindView(R.id.tvfecha)
    TextView tvfecha;
    @BindView(R.id.tvhora)
    TextView tvhora;
    String email;
    private ReportarContract.Presenter presenter;
    Location location;

    SessionManager sessionManager;

    public static ReportarFragment newInstance() {
        return new ReportarFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reportar, container, false);
        ButterKnife.bind(this, v);
        //SOLO CREAR VISTAS O MODIFICAS VISTAS
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        verifyStoragePermissions(getActivity());
        botonDesactivado();
        semafoMroReporte.setImageResource(R.drawable.icono_res);
        sessionManager = new SessionManager(getContext());
        email = sessionManager.getEmail();
        fechayHora();


    }
    @OnClick(R.id.semafoMro_reporte)
    public void onClick() {
        afterPermissions();
    }

    @OnClick({R.id.btn_cancelar, R.id.btn_enviar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancelar:
                semafoMroReporte.setImageResource(R.drawable.icono_res);
                botonDesactivado();
                break;
            case R.id.btn_enviar:
                gpsTracker = new GPSTracker(getContext());
                if(gpsTracker.canGetLocation()){
                    location = gpsTracker.getLocation();
                    latitud = gpsTracker.getLatitude();
                    longitud = gpsTracker.getLongitude();
                }else{
                    // can't get location
                    gpsTracker.showSettingsAlert();
                }
                if(location == null){
                    Toast.makeText(getContext(), "No podemos encontrar su ubicación, por favor espere unos minutos",
                            Toast.LENGTH_LONG).show();


                }else{
                    System.out.println("C A R P E T A: "+ carpeta);
                    presenter.reporteApi( getBase64(bitmap), carpeta, email, String.valueOf(latitud), String.valueOf(longitud));
        }

                break;
        }
    }
    public void verifyStoragePermissions(final Activity activity) {
        // Check if we have write permission
        if(ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(activity,Manifest.permission.ACCESS_FINE_LOCATION)){
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
                            }
                        })
                        .show();
            }else{

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
                            }
                        })
                        .show();

    }}}

    @AfterPermissionGranted(CAMARA_PERMISSIONS)
    private void afterPermissions() {
        String[] PERMISSIONS = {
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        if (EasyPermissions.hasPermissions(getContext(), PERMISSIONS)) {
            cameraIntent();
        } else {
            EasyPermissions.requestPermissions(this, "Se requiere aceptar todos los permisos para continuar",
                    CAMARA_PERMISSIONS, PERMISSIONS);
        }
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photo = getOutputMediaFile();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
        startActivityForResult(intent, CAMARA_PERMISSIONS);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == CAMARA_PERMISSIONS) {

                bitmap = decodeBitmapFromFile(photo.getAbsolutePath(), 700, 700);
                //bitmap = getResizedBitmap(bitmap, 720, 1280);

                try {
                    photo.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FileOutputStream ostream = null;
                try {
                    ostream = new FileOutputStream(photo);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                bitmap.compress(Bitmap.CompressFormat.JPEG, 60, ostream);
                try {
                    ostream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Glide.with(this).load(photo.getAbsolutePath()).into(semafoMroReporte);
                btnEnviar.setEnabled(true);
                btnEnviar.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                btnCancelar.setEnabled(true);
                btnCancelar.setBackgroundColor(getResources().getColor(R.color.colorRed));
            }
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (requestCode == CAMARA_PERMISSIONS) {
            cameraIntent();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        EasyPermissions.checkDeniedPermissionsNeverAskAgain(this,
                getString(R.string.ok),
                R.string.action_settings, R.string.cancel, perms);
    }

    public static Bitmap decodeBitmapFromFile(String path, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        carpeta = path;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize, Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        int inSampleSize = 1;

        if (height > reqHeight) {
            inSampleSize = Math.round((float) height / (float) reqHeight);
        }
        int expectedWidth = width / inSampleSize;

        if (expectedWidth > reqWidth) {
            //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
            inSampleSize = Math.round((float) width / (float) reqWidth);
        }

        options.inSampleSize = inSampleSize;

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(path, options);
    }

    private File getOutputMediaFile() {

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "ReportaPe");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("Camera Guide", "Required media storage does not exist");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".jpg");

        return mediaFile;
    }

    public static String getBase64(Bitmap bitmap) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            return Base64.encodeToString(byteArray, Base64.NO_WRAP);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void setPresenter(ReportarContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void onResult(@NonNull Status status) {

    }

    @Override
    public void reportSuccessfully() {
        reportDenied("Reporte realizado con éxito");
        botonDesactivado();
        semafoMroReporte.setImageResource(R.drawable.icono_res);
        nextActivity(getActivity(),null, VerReportesActivity.class , false);

    }

    @Override
    public void reportDenied(String response) {
        ((BaseActivity) getActivity()).showMessageError(response);

    }

    public void fechayHora(){
        Calendar calendario = GregorianCalendar.getInstance();
        Date fecha = calendario.getTime();
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoDeHora = new SimpleDateFormat("HH:mm:ss");
        tvfecha.setText("Fecha:  "+ formatoDeFecha.format(fecha));
        tvhora.setText("Hora:  " + formatoDeHora.format(fecha));
    }

    private void botonDesactivado(){
        btnEnviar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnEnviar.setBackgroundColor(getResources().getColor(R.color.colorButton));
        btnCancelar.setBackgroundColor(getResources().getColor(R.color.colorButton));
    }


}
