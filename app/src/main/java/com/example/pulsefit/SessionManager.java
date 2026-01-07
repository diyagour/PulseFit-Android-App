package com.example.pulsefit;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    public SessionManager(Context c) {
        sp = c.getSharedPreferences("pulsefit_session", Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void login(String email) {
        editor.putBoolean("logged", true);
        editor.putString("email", email);
        editor.apply();
    }

    public void logout() {
        editor.clear().apply();
    }

    public boolean isLoggedIn() {
        return sp.getBoolean("logged", false);
    }

    public String getEmail() {
        return sp.getString("email", "");
    }
}
