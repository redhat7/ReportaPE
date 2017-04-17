package com.example.capsula.reportape.core;

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

import com.example.capsula.reportape.R;


/**
 *Base Actividad de la cual se va a exteder las otras actividades de la app
 */
public class BaseActivity extends AppCompatActivity {

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

    protected void nextActivity(Activity context, Bundle bundle, Class<?> activity, boolean destroy) {
        Intent intent= new Intent(context,activity);
        if(bundle!=null){intent.putExtras(bundle);}
        startActivity(intent);
        if(destroy)context.finish();
    }

    protected void newActivityClearPreview(Activity context, Bundle bundle, Class<?> activity) {
        Intent intent= new Intent(context,activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        if(bundle!=null){intent.putExtras(bundle);}
        startActivity(intent);
        context.finish();
    }

    protected void nextActivityNewTask(Activity context, Bundle bundle, Class<?> activity, boolean destroy) {
        Intent intent= new Intent(context,activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(bundle!=null){intent.putExtras(bundle);}
        startActivity(intent);
        if(destroy)context.finish();
    }

    public void showMessageError(String message) {
        CoordinatorLayout container = (CoordinatorLayout) findViewById(R.id.activity_login);
        this.showMessage(container, message);
    }

}
