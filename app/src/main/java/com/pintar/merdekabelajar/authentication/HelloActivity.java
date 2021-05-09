package com.pintar.merdekabelajar.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.pintar.merdekabelajar.R;

public class HelloActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView daftar, masuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        daftar = findViewById(R.id.daftar);
        masuk = findViewById(R.id.masuk);

        daftar.setOnClickListener(this);
        masuk.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.daftar:
                startActivity(new Intent(HelloActivity.this, RegisterActivity.class));
                break;

            case R.id.masuk:
                startActivity(new Intent(HelloActivity.this, LoginActivity.class));
                break;

        }
    }
}