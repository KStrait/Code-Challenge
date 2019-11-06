package com.kylestrait.codechallenge.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kylestrait.codechallenge.data.Login;

import java.lang.reflect.Type;

import static android.content.Context.MODE_PRIVATE;

// Util to store login info. Not ideal, but I'm not sure of encryption methods.

public class SharedPreferencesManager {
    private static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    private static final String LOGIN_INFO = "LOGIN_INFO";

    private final SharedPreferences sharedPreferences;
    private final Gson gson;

    public SharedPreferencesManager(Context context, Gson gson) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        this.gson = gson;
    }

    public void putLoginInfo(Login login) {
        String s = gson.toJson(login);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LOGIN_INFO, s);
        editor.apply();
        editor.commit();
    }

    public Login getLoginInfo() {
        Login login;

        Type type = new TypeToken<Login>() {}.getType();
        String jsonPreferences = sharedPreferences.getString(LOGIN_INFO, "");
        login = gson.fromJson(jsonPreferences, type);

        return login;
    }
}
