package com.example.capsula.reportape.core;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by manu on 14/06/16.
 */
public class BaseFragment extends Fragment {

    private boolean loading= false;

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


    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    protected void initView(){

    }

    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }
}
