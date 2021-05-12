package com.pintar.merdekabelajar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.pintar.merdekabelajar.adapter.CateAdapter;
import com.pintar.merdekabelajar.adapter.PopAdapter;
import com.pintar.merdekabelajar.authentication.HelloActivity;
import com.pintar.merdekabelajar.authentication.ResetActivity;
import com.pintar.merdekabelajar.menu.AboutActivity;
import com.pintar.merdekabelajar.menu.FaqActivity;
import com.pintar.merdekabelajar.menu.HelpActivity;
import com.pintar.merdekabelajar.model.CateModel;
import com.pintar.merdekabelajar.model.PopModel;
import com.pintar.merdekabelajar.model.User;
import com.pintar.merdekabelajar.profile.ProfileActivity;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    ImageView menu, profil, cardscreen;

    TextView cari, nama;

    public String linkscreen;

    DatabaseReference reference;

    FirebaseUser firebaseUser;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ConstraintLayout bottomsheet;

    static final float END_SCALE = 0.7f;

    RecyclerView recyclerView, recyclerViewT, recyclerViewC;

    List<PopModel> popModelList, popModelListT;
    List<CateModel> cateModelList;

    PopAdapter popAdapter, popAdapterT;
    CateAdapter cateAdapter;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu = findViewById(R.id.menu);
        profil = findViewById(R.id.profil);
        cardscreen = findViewById(R.id.iamge_screen);
        cari = findViewById(R.id.cari);
        nama = findViewById(R.id.nama);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        bottomsheet = findViewById(R.id.bottom_sheet);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerViewT = findViewById(R.id.recyclerviewT);
        recyclerViewC = findViewById(R.id.recyclerViewCate);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        menu.setOnClickListener(this);
        profil.setOnClickListener(this);
        cari.setOnClickListener(this);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        popModelList = new ArrayList<>();
        popAdapter = new PopAdapter(this, popModelList);

        recyclerView.setAdapter(popAdapter);

        recyclerViewT.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewT.setLayoutManager(linearLayoutManager2);
        popModelListT = new ArrayList<>();
        popAdapterT = new PopAdapter(this, popModelListT);

        recyclerViewT.setAdapter(popAdapterT);

        recyclerViewC.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(this);
        linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewC.setLayoutManager(linearLayoutManager3);
        cateModelList = new ArrayList<>();
        cateAdapter = new CateAdapter(this, cateModelList);

        recyclerViewC.setAdapter(cateAdapter);

        screen();
        profilhome();
        navigationDrawer();
        horizontalList();
        horizontalListTerbaru();
        kategori();

    }

    private void kategori() {

        databaseReference = FirebaseDatabase.getInstance().getReference("Kategori");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.hasChildren()){
                    cateModelList.clear();
                    for (DataSnapshot dss: snapshot.getChildren()){
                        final CateModel model = dss.getValue(CateModel.class);
                        cateModelList.add(model);
                    }
                    cateAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void horizontalListTerbaru() {

        databaseReference = FirebaseDatabase.getInstance().getReference("Kursus");

        Query query = databaseReference.limitToLast(5);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    popModelListT.clear();
                    for (DataSnapshot dss: snapshot.getChildren()){
                        final PopModel model = dss.getValue(PopModel.class);
                        popModelListT.add(model);
                    }
                    popAdapterT.notifyDataSetChanged();
                    //hideLoading();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void horizontalList() {

        databaseReference = FirebaseDatabase.getInstance().getReference("Kursus");

        Query query = databaseReference.limitToLast(5);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    popModelList.clear();
                    for (DataSnapshot dss: snapshot.getChildren()){
                        final PopModel model = dss.getValue(PopModel.class);
                        popModelList.add(model);
                    }
                    popAdapter.notifyDataSetChanged();
                    //hideLoading();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.profil:
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                break;
            case R.id.cari:
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                break;

        }
    }

    private void navigationDrawer() {

        navigationView.bringToFront(); // Navigation drawer
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.home);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);

            }
        });

        animateNavigationDrawer();

    }

    private void animateNavigationDrawer() {

        drawerLayout.setScrimColor(getResources().getColor(R.color.purlet));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                bottomsheet.setScaleX(offsetScale);
                bottomsheet.setScaleY(offsetScale);

                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = bottomsheet.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                bottomsheet.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                break;

            case R.id.account:
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
                break;

            case R.id.about:
                Intent intent1 = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent1);
                break;

            case R.id.faq:
                Intent intent2 = new Intent(MainActivity.this, FaqActivity.class);
                startActivity(intent2);
                break;

            case R.id.help:
                Intent intent3 = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent3);
                break;
        }
        return true;
    }

    private void profilhome() {

        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                final User model = snapshot.getValue(User.class);

                nama.setText(model.getUsername());

                Glide.with(getApplicationContext()).load(model.getImageurl()).into(profil);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void screen() {

        reference = FirebaseDatabase.getInstance().getReference("Images").child("screen");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                linkscreen = snapshot.getValue().toString();

                if (linkscreen != null){

                    Glide.with(getApplicationContext()).load(linkscreen)
                            .centerCrop()
                            .into(cardscreen);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}