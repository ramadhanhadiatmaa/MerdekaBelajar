package com.pintar.merdekabelajar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.pintar.merdekabelajar.onboarding.OnboardingFragment1;
import com.pintar.merdekabelajar.onboarding.OnboardingFragment2;
import com.pintar.merdekabelajar.onboarding.OnboardingFragment3;

public class SplashActivity extends AppCompatActivity {

    ImageView logo, background;

    ViewPager viewPager;
    ScreenSlidePagerAdapter pagerAdapter;

    private static final int NUM_PAGES = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logo = findViewById(R.id.image_logo);
        background = findViewById(R.id.background);
        viewPager = findViewById(R.id.pager);

    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{

        public ScreenSlidePagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    OnboardingFragment1 tab1 = new OnboardingFragment1();
                    return tab1;
                case 1:
                    OnboardingFragment2 tab2 = new OnboardingFragment2();
                    return tab2;
                case 2:
                    OnboardingFragment3 tab3 = new OnboardingFragment3();
                    return tab3;
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
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
                logo.animate().translationY(20000).setDuration(3000); // Set animation
                background.animate().translationY(-20000).setDuration(3000);
                pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
                viewPager.setAdapter(pagerAdapter);
            }

            // Delay waktu berapa detik, 3000 berarti sama dengan 3 detik
        },3000);
    }
}