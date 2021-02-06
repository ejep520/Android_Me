package com.example.android.android_me.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.android.android_me.R;

import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment extends Fragment {

    private final static String LOG_TAG = BodyPartFragment.class.getSimpleName();
    private final static String IMAGE_LIST = "IMAGE_LIST";
    private final static String IMAGE_INDEX = "IMAGE_INDEX";

    private List<Integer> mImageIds = null;
    private int mListIndex = -1;
    private ImageView imageView;

    /**
     * This is a do-nothing, mandatory constructor.
     */
    public BodyPartFragment() {
    }

    /**
     * Inflates the fragment layout and sets any image resources.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Check for a saved instance state.
        if (savedInstanceState != null) {
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_LIST);
            mListIndex = savedInstanceState.getInt(IMAGE_INDEX, -1);
        }

        // Inflate the Android-Me fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        // Get a reference to the ImageView in the rootView.
        imageView = rootView.findViewById(R.id.body_part_image_view);

        // Attach the click listener.
        rootView.setOnClickListener(incrementClickListener);

        // Set the image resource to display.
        if (mImageIds == null) {
            Log.d(LOG_TAG, "The image list has not been set.");
        } else if (mListIndex < 0) {
            Log.d(LOG_TAG, "The image list index hasn't been set.");
        } else {
            imageView.setImageResource(mImageIds.get(mListIndex));
        }

        // Return the inflated view.
        return rootView;
    }

    public void setmImageIds(List<Integer> mImageIds) {
        this.mImageIds = mImageIds;
    }

    public void setmListIndex(int mListIndex) {
        if (mListIndex >= 0) {
            this.mListIndex = mListIndex;
        } else {
            Log.d(LOG_TAG, "An invalid list index was detected.");
        }
    }

    private final View.OnClickListener incrementClickListener = (v) -> {
        mListIndex = (++mListIndex) % mImageIds.size();
        imageView.setImageResource(mImageIds.get(mListIndex));
    };

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        currentState.putIntegerArrayList(IMAGE_LIST, (ArrayList<Integer>)mImageIds);
        currentState.putInt(IMAGE_INDEX, mListIndex);
    }
}
