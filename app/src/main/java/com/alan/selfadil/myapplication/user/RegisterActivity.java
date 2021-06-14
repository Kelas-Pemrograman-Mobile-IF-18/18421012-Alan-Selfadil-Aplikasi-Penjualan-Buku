package com.alan.selfadil.myapplication.user;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alan.selfadil.myapplication.R;
import com.alan.selfadil.myapplication.server.BaseURL;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ornach.nobobutton.NoboButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    Button btnKembaliRegistrasi;
    NoboButton btnRegister;
    EditText edtUsername, edtNamalengkap, edtEmail, edtNotelp, edtPassword;

    private RequestQueue mRequestQueue;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();

        mRequestQueue = Volley.newRequestQueue(this);

        edtUsername     = (EditText) findViewById(R.id.edtUsername);
        edtNamalengkap  = (EditText) findViewById(R.id.edtNamalengkap);
        edtEmail        = (EditText) findViewById(R.id.edtEmail);
        edtNotelp       = (EditText) findViewById(R.id.edtNotelp);
        edtPassword     = (EditText) findViewById(R.id.edtPassword);

        btnKembaliRegistrasi = (Button) findViewById(R.id.btnKembaliRegistrasi);
        btnRegister = (NoboButton) findViewById(R.id.btnRegister);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        btnKembaliRegistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername      = edtUsername.getText().toString();
                String strNamalengkap   = edtNamalengkap.getText().toString();
                String strEmail         = edtEmail.getText().toString();
                String strNotelp        = edtNotelp.getText().toString();
                String strPassword      = edtPassword.getText().toString();

                if(strUsername.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strNamalengkap.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Nama Lengkap Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strEmail.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Email Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strNotelp.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "No. Telepon Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strPassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else {
                    register(strUsername, strNamalengkap, strEmail, strNotelp, strPassword);
                }
            }
        });

    }
    public void register(String Username, String Namalengkap, String Email, String Notelp, String Password){

            // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("Username", Username);
        params.put("namaLengkap", Namalengkap);
        params.put("Email", Email);
        params.put("Notelp", Notelp);
        params.put("role", "2");
        params.put("Password", Password);


        pDialog.setMessage("Mohon Tunggu.....");
        showDialog();

        JsonObjectRequest req = new JsonObjectRequest(BaseURL.register, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            String strMsg = response.getString("msg");
                            boolean status = response.getBoolean("error");
                            if(status == false){
                                Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(i);
                                finish();
                            }else {
                                Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });
        mRequestQueue.add(req);
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void showDialog(){
        if(!pDialog.isShowing()){
            pDialog.show();
        }
    }

    private void hideDialog(){
        if(pDialog.isShowing()){
            pDialog.dismiss();
        }
    }
}