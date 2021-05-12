package com.pintar.merdekabelajar.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.pintar.merdekabelajar.R;
import com.pintar.merdekabelajar.model.User;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView image;

    TextView username, email, edit, logout, join, change, fullname;

    DatabaseReference reference;

    FirebaseUser firebaseUser;

    Uri imageUri;
    String myUrl = "";
    StorageTask uploadTask;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        image = findViewById(R.id.imageprofil);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        edit = findViewById(R.id.edit);
        logout = findViewById(R.id.logout);
        join = findViewById(R.id.join);
        fullname = findViewById(R.id.fullname);
        change = findViewById(R.id.change);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        edit.setOnClickListener(this);
        logout.setOnClickListener(this);
        change.setOnClickListener(this);

        dataProfil();
    }

    private void dataProfil() {

        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                final User model = snapshot.getValue(User.class);

                username.setText(model.getUsername());
                email.setText(model.getEmail());

                long mills = model.getJoin() - System.currentTimeMillis();
                long hour = (mills/(1000*60*60))*-1;
                long mins = ((mills/(1000*60)) % 60)*-1;
                long day = hour/24;
                long week = day/7;
                long month = week/4;

                String a = Long.toString(hour);
                String b = Long.toString(mins);
                String c = Long.toString(day);
                String d = Long.toString(week);
                String e = Long.toString(month);

                if(a.equals("0")){
                    join.setText(b+" menit lalu");
                }else if(hour > 0 && hour <= 24){
                    join.setText(a+" jam lalu");
                }else if(hour > 24 && day <= 6){
                    join.setText(c+" hari lalu");
                }else if(day > 7 && week <= 4){
                    join.setText(d+" minggu lalu");
                }else if(week > 4 && month <= 12){
                    join.setText(e+" bulan lalu");
                }else if(month > 12) {
                    join.setText(e + " tahun lalu");
                }

                Glide.with(getApplicationContext()).load(model.getImageurl()).into(image);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.edit:
                startActivity(new Intent(ProfileActivity.this, EditprofilActivity.class));
                break;
            case R.id.logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}