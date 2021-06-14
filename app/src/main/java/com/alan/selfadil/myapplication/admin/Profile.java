package com.alan.selfadil.myapplication.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.alan.selfadil.myapplication.R;
import com.alan.selfadil.myapplication.session.PrefSetting;

public class Profile extends AppCompatActivity {

    TextView txtUserName, txtNamaLengkap, txtEmail, txtNotelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("Profile User");

        txtUserName = (TextView) findViewById(R.id.edtUsername);
        txtNamaLengkap = (TextView) findViewById(R.id.edtNamalengkap);
        txtEmail = (TextView) findViewById(R.id.edtEmail);
        txtNotelp = (TextView) findViewById(R.id.edtNotelp);

        txtUserName.setText(PrefSetting.Username);
        txtNamaLengkap.setText(PrefSetting.Namalengkap);
        txtEmail.setText(PrefSetting.Email);
        txtNotelp.setText(PrefSetting.Notelp);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Profile.this, HomeAdminActivity.class);
        startActivity(i);
        finish();
    }
}