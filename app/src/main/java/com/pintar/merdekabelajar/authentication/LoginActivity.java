package com.pintar.merdekabelajar.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.pintar.merdekabelajar.MainActivity;
import com.pintar.merdekabelajar.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView back, daftar;

    EditText email, password;

    TextView lupa, textdaftar;

    ProgressBar progressBar;

    FirebaseAuth firebaseAuth;

    private String emailPattern1 = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    private String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+.[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        back = findViewById(R.id.back);
        daftar = findViewById(R.id.but_masuk);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        lupa = findViewById(R.id.lupasandi);
        textdaftar = findViewById(R.id.daftar);
        progressBar = findViewById(R.id.progressbarl);

        firebaseAuth = FirebaseAuth.getInstance();

        back.setOnClickListener(this);
        daftar.setOnClickListener(this);
        lupa.setOnClickListener(this);
        textdaftar.setOnClickListener(this);

        // Add Textwatcher
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void checkInputs() {

        if(!TextUtils.isEmpty(email.getText())){
            if(!TextUtils.isEmpty(password.getText())){

                daftar.setEnabled(true);

            }else{

                daftar.setEnabled(false);

            }
        }else{

            daftar.setEnabled(false);

        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.back:
                startActivity(new Intent(LoginActivity.this, HelloActivity.class));
                break;
            case R.id.but_masuk:
                checkEmailandPassword();
                break;
            case R.id.lupasandi:
                startActivity(new Intent(LoginActivity.this, ResetActivity.class));
                break;
            case R.id.daftar:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;

        }
    }

    private void checkEmailandPassword() {

        Drawable customErrorIcon = getResources().getDrawable(R.drawable.error);
        customErrorIcon.setBounds(0,0,customErrorIcon.getIntrinsicWidth(),customErrorIcon.getIntrinsicHeight());

        if(email.getText().toString().matches(emailPattern1) || email.getText().toString().matches(emailPattern2)){
            if(password.length() == 8){

                progressBar.setVisibility(View.VISIBLE);
                daftar.setEnabled(false);

                firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){

                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();

                                }else{

                                    progressBar.setVisibility(View.INVISIBLE);
                                    daftar.setEnabled(true);
                                    String error = task.getException().getMessage();
                                    Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }else{

                password.setError("Password salah", customErrorIcon);

            }
        }else{

            email.setError("Email tidak valid", customErrorIcon);

        }
    }
}