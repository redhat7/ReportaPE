package com.example.capsula.reportape.data.repositories.local;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by miguel on 06/02/17.
 */

public class SessionManager {
    static final String PREFERENCE_NAME = "NamePreference";

    static int PRIVATE_MODE = 0;

    private static final String TOKEN = "token";
    private static final String EMAIL = "email";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        preferences = this.context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public  void saveEmail(String email){
        editor.putString(EMAIL,email);
        editor.commit();
    }

    public String getEmail(){
        return preferences.getString(EMAIL , null);
    }

    public void closeSession(){
        editor.putString(EMAIL, null);
        editor.commit();
    }

}
