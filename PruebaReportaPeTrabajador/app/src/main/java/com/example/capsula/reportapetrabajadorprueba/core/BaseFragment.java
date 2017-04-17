package com.example.capsula.reportapetrabajadorprueba.core;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by linuxdesarrollo01 on 20/02/17.
 */

public class BaseFragment extends Fragment {

    private boolean loading= false;

    protected void nextActivity(Activity context, Bundle bundle, Class<?> activity, boolean destroy) {
        Intent intent= new Intent(context,activity);
        if(bundle!=null){intent.putExtras(bundle);}
        startActivity(intent);
        if(destroy)context.finish();
    }

    public boolean isLoading() {
        return loading;
    }
}
