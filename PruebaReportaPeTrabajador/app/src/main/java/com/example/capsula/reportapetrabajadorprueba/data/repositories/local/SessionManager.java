package com.example.capsula.reportapetrabajadorprueba.data.repositories.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by linuxdesarrollo01 on 20/02/17.
 */

public class SessionManager {
    static final String PREFERENCE_NAME = "NamePreference";

    static int PRIVATE_MODE = 0;

    private static final String TOKEN = "token";
    private static final String EMAIL = "email";
    private static final String ID = "id";


    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        preferences = this.context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public  void saveId(int id){
        editor.putInt(ID,id);
        editor.commit();
    }

    public int getId(){
        return preferences.getInt(ID , 0 );
    }

    public void closeSession(){
        editor.putInt(ID, 0);
        editor.commit();
    }
}
