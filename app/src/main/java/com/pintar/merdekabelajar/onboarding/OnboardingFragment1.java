package com.pintar.merdekabelajar.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pintar.merdekabelajar.MainActivity;
import com.pintar.merdekabelajar.R;

public class OnboardingFragment1 extends Fragment {

    ImageView layer1;

    TextView merdeka, text, skip;

    float v=0;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_on_boarding1, container, false);

        layer1 = root.findViewById(R.id.imageView);
        merdeka = root.findViewById(R.id.merdeka);
        text = root.findViewById(R.id.text);
        skip = root.findViewById(R.id.skip);

        skip.setTranslationY(300); // Set transition animaation
        layer1.setTranslationX(-300);
        merdeka.setTranslationX(-300);
        text.setTranslationX(-300);

        skip.setAlpha(v); // Set Alpha
        layer1.setAlpha(v);
        merdeka.setAlpha(v);
        text.setAlpha(v);

        skip.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start(); // Set Animation
        layer1.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        merdeka.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        text.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();

            }
        });

        return root;
    }
}
