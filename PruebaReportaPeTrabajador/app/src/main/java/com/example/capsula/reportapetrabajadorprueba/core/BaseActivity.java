package com.example.capsula.reportapetrabajadorprueba.core;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capsula.reportapetrabajadorprueba.R;

/**
 * Created by linuxdesarrollo01 on 20/02/17.
 */

public class BaseActivity extends AppCompatActivity {

    protected void nextActivity(Activity context, Bundle bundle, Class<?> activity, boolean destroy) {
        Intent intent= new Intent(context,activity);
        if(bundle!=null){intent.putExtras(bundle);}
        startActivity(intent);
        if(destroy)context.finish();
    }

    public void showMessage(View container, String message)
    {
        if(container!=null){
            Snackbar snackbar = Snackbar
                    .make(container,message, Snackbar.LENGTH_LONG);
            snackbar.setActionTextColor(Color.WHITE);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        }else {
            Toast toast =
                    Toast.makeText(getApplicationContext(),
                            message, Toast.LENGTH_LONG);

            toast.show();
        }

    }
    public void showMessageError(String message) {
        CoordinatorLayout container = (CoordinatorLayout) findViewById(R.id.activity_login);
        this.showMessage(container, message);
    }



}
