package com.alan.selfadil.myapplication.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import com.alan.selfadil.myapplication.R;
import com.alan.selfadil.myapplication.session.PrefSetting;
import com.alan.selfadil.myapplication.session.SessionManager;
import com.alan.selfadil.myapplication.user.LoginActivity;

import java.util.Calendar;

import static com.alan.selfadil.myapplication.R.id.cardprofile;

public class HomeAdminActivity extends AppCompatActivity {

    SessionManager session;
    SharedPreferences prefs;
    PrefSetting prefSetting;
    CardView cardExit, cardDataBuku, cardInputBuku;
    CardView cardProfile;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        prefSetting = new PrefSetting(this);
        prefs = prefSetting.getSharePreferances();

        session = new SessionManager(HomeAdminActivity.this);

        prefSetting.isLogin(session, prefs);

        cardExit = (CardView) findViewById(R.id.cardExit);
        cardDataBuku = (CardView) findViewById(R.id.carddataBuku);
        cardInputBuku = (CardView) findViewById(R.id.cardInputBuku);

        cardProfile = (CardView) findViewById(R.id.cardprofile);

        cardExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setSessid(0);
                Intent i = new Intent(HomeAdminActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        cardDataBuku.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, ActivityDataBuku.class);
                startActivity(i);
                finish();
            }
        });

        cardInputBuku.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, InputDataBuku.class);
                startActivity(i);
                finish();
            }
        });

        cardProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, Profile.class);
                startActivity(i);
                finish();
            }
        });
    }
}