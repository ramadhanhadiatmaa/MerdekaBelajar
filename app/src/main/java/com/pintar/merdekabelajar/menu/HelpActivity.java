package com.pintar.merdekabelajar.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.pintar.merdekabelajar.R;

import java.util.HashMap;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email, pesan;

    TextView kirim;

    DatabaseReference databaseReference;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        email = findViewById(R.id.email);
        pesan = findViewById(R.id.feedback);
        kirim = findViewById(R.id.kirim);

        firebaseAuth = FirebaseAuth.getInstance();

        kirim.setOnClickListener(this);

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

        pesan.addTextChangedListener(new TextWatcher() {
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

        if (!TextUtils.isEmpty(email.getText())){
            if (!TextUtils.isEmpty(pesan.getText())){
                kirim.setEnabled(true);
            }else {
                kirim.setEnabled(false);
            }
        }else {
            kirim.setEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.kirim:
                kirim();
                break;

        }
    }

    private void kirim() {

        firebaseUser = firebaseAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference("Feedback");

        String id = databaseReference.push().getKey();

        HashMap<String, Object> userdata = new HashMap<>();
        userdata.put("id", firebaseUser.getUid());
        userdata.put("feedid", id);
        userdata.put("email", email.getText().toString());
        userdata.put("pesan", pesan.getText().toString());

        databaseReference.child(id).setValue(userdata);

        Toast.makeText(this, "Pesan terkirim", Toast.LENGTH_SHORT).show();
    }
}