package com.alan.selfadil.myapplication.session;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.alan.selfadil.myapplication.admin.HomeAdminActivity;
import com.alan.selfadil.myapplication.pembeli.HomePembeli;

public class PrefSetting {

    public static String _id;
    public static String Username;
    public static String Namalengkap;
    public static String Email;
    public static String Notelp;
    public static String role;
    Activity activity;

    public PrefSetting(Activity activity) {
        this.activity = activity;
    }

    public SharedPreferences getSharePreferances() {
        SharedPreferences preferences = activity.getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
        return preferences;
    }

    public void isLogin(SessionManager session, SharedPreferences pref) {
        session = new SessionManager(activity);
        if (session.isLoggedIn()) {
            pref = getSharePreferances();
            _id = pref.getString("_id", "");
            Username = pref.getString("Username", "");
            Namalengkap = pref.getString("Namalengkap", "");
            Email = pref.getString("Email", "");
            Notelp = pref.getString("NoTelp", "");
            role = pref.getString("role", "");
        } else {
            session.setLogin(false);
            session.setSessid(0);
            Intent i = new Intent(activity, activity.getClass());
            activity.startActivity(i);
            activity.finish();
        }
    }

    public void checkLogin(SessionManager session, SharedPreferences pref) {
        session = new SessionManager(activity);
        _id = pref.getString("_id", "");
        Username = pref.getString("Username", "");
        Namalengkap = pref.getString("Namalengkap", "");
        Email = pref.getString("Email", "");
        Notelp = pref.getString("NoTelp", "");
        role = pref.getString("role", "");
        if (session.isLoggedIn()) {
            if (role.equals("1")){
            Intent i = new Intent(activity, HomeAdminActivity.class);
            activity.startActivity(i);
            activity.finish();
        } else {
            Intent i = new Intent(activity, HomePembeli.class);
            activity.startActivity(i);
            activity.finish();
        }
    }
}
    public void storeRegIdSharedPreferences(Context context, String _id, String Username,
                                            String Namalengkap, String Email, String Notelp, String role, SharedPreferences prefs){

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("_id", _id);
        editor.putString("Username", Username);
        editor.putString("Namalengkap", Namalengkap);
        editor.putString("Email", Email);
        editor.putString("Notelp", Notelp);
        editor.putString("role", role);
        editor.commit();

    }
}
