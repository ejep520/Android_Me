package com.example.android.android_me.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class BodyPartFragment extends Fragment {
    /**
     * This is a do-nothing, mandatory constructor.
     */
    public BodyPartFragment() { }

    /**
     * Inflates the fragment layout and sets any image resources.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the Android-Me fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        // Get a reference to the ImageView in the rootView.
        ImageView imageView = rootView.findViewById(R.id.body_part_image_view);

        // Set the image resource to display.
        imageView.setImageResource(AndroidImageAssets.getHeads().get(0));

        // Return the inflated view.
        return rootView;
    }
}
