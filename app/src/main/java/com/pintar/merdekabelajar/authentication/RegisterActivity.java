package com.pintar.merdekabelajar.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.TransitionManager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.pintar.merdekabelajar.R;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText userid, email, password, repassword;

    ImageView but_daftar, back, mail;

    TextView punyaakun, notif;

    ProgressBar progressBar;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;

    String useridd;

    private String emailPattern1 = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    private String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+.[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userid = findViewById(R.id.userid);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        but_daftar = findViewById(R.id.but_daftar);
        punyaakun = findViewById(R.id.punyaakun);
        back = findViewById(R.id.back);
        progressBar = findViewById(R.id.progressBar);
        mail = findViewById(R.id.icon_mail);
        notif = findViewById(R.id.notif);

        firebaseAuth = FirebaseAuth.getInstance();

        but_daftar.setOnClickListener(this);
        punyaakun.setOnClickListener(this);
        back.setOnClickListener(this);

        // Add Listener
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

        userid.addTextChangedListener(new TextWatcher() {
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

        repassword.addTextChangedListener(new TextWatcher() {
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

        // Check text empty or not
        if (!TextUtils.isEmpty(userid.getText())){
            if (!TextUtils.isEmpty(email.getText())){
                if (!TextUtils.isEmpty(password.getText()) && password.length() == 8){
                    but_daftar.setEnabled(true);
                }else {
                    but_daftar.setEnabled(false);
                }
            }else {
                but_daftar.setEnabled(false);
            }
        }else {
            but_daftar.setEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.back:
                startActivity(new Intent(RegisterActivity.this, HelloActivity.class));
                break;
            case R.id.punyaakun:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                break;
            case R.id.but_daftar:
                checkEmailAndPassword();
                break;

        }
    }

    private void checkEmailAndPassword() {

        Drawable customErrorIcon = getResources().getDrawable(R.drawable.error);
        customErrorIcon.setBounds(0,0, customErrorIcon.getIntrinsicWidth(), customErrorIcon.getIntrinsicHeight());

        if(email.getText().toString().matches(emailPattern1) || email.getText().toString().matches(emailPattern2)){
            if(password.getText().toString().equals(repassword.getText().toString())){

                notif.setVisibility(View.GONE);

                progressBar.setVisibility(View.VISIBLE);
                mail.setVisibility(View.INVISIBLE);

                but_daftar.setEnabled(false);

                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){

                                    firebaseUser = firebaseAuth.getCurrentUser();
                                    useridd = firebaseUser.getUid();

                                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(useridd);

                                    HashMap<String, Object> userdata = new HashMap<>();
                                    userdata.put("id", useridd);
                                    userdata.put("username", userid.getText().toString().toLowerCase());
                                    userdata.put("gender", "");
                                    userdata.put("asal", "");
                                    userdata.put("lahir", "");
                                    userdata.put("join", ServerValue.TIMESTAMP);
                                    userdata.put("reputasi", "");
                                    userdata.put("phone", "");
                                    userdata.put("email", email.getText().toString());
                                    userdata.put("verified", "no");
                                    userdata.put("imageurl", "https://cdn.statically.io/gh/kodlitecom/Anatomy/2589e5bb/img/Group-15-3x.png");
                                    userdata.put("rekening", "");
                                    userdata.put("alengkap", "");
                                    userdata.put("status", "offline");

                                    databaseReference.setValue(userdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){

                                                SendEmailVerificationMessage();

                                                ScaleAnimation scaleAnimation = new ScaleAnimation(1,0,1,0, mail.getWidth()/2, mail.getHeight()/2);
                                                scaleAnimation.setDuration(100);
                                                scaleAnimation.setInterpolator(new AccelerateInterpolator());
                                                scaleAnimation.setRepeatCount(Animation.REVERSE);
                                                scaleAnimation.setRepeatCount(1);
                                                scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                                                    @Override
                                                    public void onAnimationStart(Animation animation) {

                                                    }

                                                    @Override
                                                    public void onAnimationEnd(Animation animation) {

                                                        notif.setText(getResources().getString(R.string.periksa_pesan_masuk_atau_spam_untuk_verifikasi_akunmu));
                                                        notif.setTextColor(getResources().getColor(R.color.green));

                                                        mail.setVisibility(View.VISIBLE);
                                                        notif.setVisibility(View.VISIBLE);
                                                        progressBar.setVisibility(View.GONE);

                                                        email.setText("");
                                                        userid.setText("");
                                                        password.setText("");
                                                        repassword.setText("");

                                                    }

                                                    @Override
                                                    public void onAnimationRepeat(Animation animation) {

                                                        mail.setImageResource(R.drawable.emailgreen);

                                                    }
                                                });

                                                mail.startAnimation(scaleAnimation);

                                            }else{

                                                but_daftar.setEnabled(true);
                                                progressBar.setVisibility(View.GONE);
                                                notif.setText(getResources().getString(R.string.kesalahan));
                                                notif.setTextColor(getResources().getColor(R.color.red));
                                                notif.setVisibility(View.VISIBLE);

                                            }
                                        }
                                    });

                                }else{

                                    progressBar.setVisibility(View.INVISIBLE);
                                    but_daftar.setEnabled(true);
                                    String error = task.getException().getMessage();
                                    Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }else{
                repassword.setError("Sandi tidak cocok", customErrorIcon);
            }
        }else{
            email.setError("Alamat surel tidak valid", customErrorIcon);
        }
    }

    private void SendEmailVerificationMessage() {

        firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser != null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        firebaseAuth.signOut();
                    }else{
                        firebaseAuth.signOut();
                    }
                }
            });
        }
    }
}