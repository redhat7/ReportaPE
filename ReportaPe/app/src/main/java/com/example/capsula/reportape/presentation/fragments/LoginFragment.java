package com.example.capsula.reportape.presentation.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.capsula.reportape.R;
import com.example.capsula.reportape.core.BaseActivity;
import com.example.capsula.reportape.core.BaseFragment;
import com.example.capsula.reportape.data.repositories.local.SessionManager;
import com.example.capsula.reportape.presentation.activity.LandingActivity;
import com.example.capsula.reportape.presentation.activity.ReportarActivity;
import com.example.capsula.reportape.presentation.contracts.LoginContract;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Desarrollo3 on 9/02/2017.
 */

public class LoginFragment extends BaseFragment implements LoginContract.View, View.OnClickListener,
        GoogleApiClient.OnConnectionFailedListener {
    SessionManager sessionManager;
    //Variables Gmail
    private static final String TAG = LandingActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;

    private GoogleApiClient mGoogleApiClient;
    public String personName, email; // para obtener datos de usuario
    private LoginContract.Presenter presenter;


    @BindView(R.id.login_logo)
    ImageView loginLogo;
    @BindView(R.id.tvRegistrate)
    TextView tvRegistrate;
    @BindView(R.id.semaforo_login)
    ImageView semaforoLogin;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    @BindView(R.id.btn_sign_in)
    SignInButton btnSignIn;
    @BindView(R.id.cBox)
    CheckBox cBox;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sessionManager = new SessionManager(getContext());
        btnSignIn.setOnClickListener(this);
        // Configuramos el inicio de sesión para solicitar el ID del usuario y/o de correo electrónico
        // El ID y el perfil básico se incluyen en DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Creamos el GoogleApiClient con acceso al API de loginGmail
        //  y a las opciones especificadas en el gso
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .enableAutoManage(getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addApi(AppIndex.API).build();

        // Personalización del botón G +
        //btnSignIn.setSize(SignInButton.SIZE_STANDARD);
        //btnSignIn.setScopes(gso.getScopeArray());
    }

    private void signIn() {
        // Se inicia la intención para que el usuario elija una cuenta de Google.
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    /*public void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        sessionManager.closeSession();
                        // [END_EXCLUDE]
                    }
                });
    }*/

    //// Recupera el resultado de inicio de sesión con GoogleSignInResult,
    public void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {  // se comprueba si tuvo éxito con el isSuccess
            // Se llama al método getSignInAccount para obtener un objeto GoogleSignInAccount
            // que contiene información sobre el usuario.
            GoogleSignInAccount acct = result.getSignInAccount();
            Log.e(TAG, "display name: " + acct.getDisplayName());
            personName = acct.getDisplayName();
            email = acct.getEmail();
            Log.e(TAG, "Name: " + personName + ", email: " + email);
        } else {
            // Signed out, show unauthenticated UI.
            //updateUI(false);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Resultado devuelto al iniciar el intento en signIn()
        if (requestCode == RC_SIGN_IN) {
            // Si selecciona un correo Google se inicia la intención
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            // Enviamos en resultado para obtener el nombre y el email del correo elegido
            handleSignInResult(result);
        }
        signOut();
        //Conexión con el presenter
        presenter.loginApi(email);

        //Siguiente actividad
        Bundle bundle = new Bundle();
        //bundle.putString("email" , email);
        sessionManager.saveEmail(email);
        nextActivity(getActivity(), bundle, ReportarActivity.class, true);
    }

    @Override
    public void onStart() {
        super.onStart();
        /*OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // Si las credenciales del caché son validas se conecta de forma automática.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);

        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            //showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }*/
    }

    @Override
    public void loginSuccessfully() {
        loginDenied("Inicio de sesión exitosa");

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
        return false;
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        //Al dar click en el boton sign in se accede a signIn(), lo mismo
        //para las demás opciones (signOut,revokeAccess)
        switch (id) {
            case R.id.btn_sign_in:
                signIn();
                break;

        }
    }
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // ...
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

}
