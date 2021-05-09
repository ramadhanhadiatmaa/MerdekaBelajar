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
import com.pintar.merdekabelajar.authentication.HelloActivity;

public class OnboardingFragment3 extends Fragment {

    ImageView layer1;

    TextView merdeka, text, next;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_on_boarding3, container, false);

        layer1 = root.findViewById(R.id.imageView);
        merdeka = root.findViewById(R.id.merdeka);
        text = root.findViewById(R.id.text);
        next = root.findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), HelloActivity.class));
                getActivity().finish();

            }
        });

        return root;
    }
}
