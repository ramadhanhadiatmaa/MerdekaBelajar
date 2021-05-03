package com.pintar.merdekabelajar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    ImageView logo, background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logo = findViewById(R.id.image_logo);
        background = findViewById(R.id.background);

    }

    @Override
    protected void onStart() {
        super.onStart();

        // Membuat handler
        final Handler handler = new Handler();

        // Delay activity
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                // Hal yang dilakukan setelah delay
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }

            // Delay waktu berapa detik, 3000 berarti sama dengan 3 detik
        },3000);
    }
}