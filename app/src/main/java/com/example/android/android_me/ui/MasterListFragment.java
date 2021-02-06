package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MasterListFragment extends Fragment {

    OnImageClickListener mCallback;

    public interface OnImageClickListener {
        void onImageSelected(Integer position);
    }

    // Mandatory do-nothing constructor.
    public MasterListFragment() { }

    // Inflate the GridView of all AndroidMe images.
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(
                R.layout.fragment_master_list,
                container,
                false);
        GridView gridView = rootView.findViewById(R.id.images_grid_view);
        gridView.setAdapter(new MasterListAdapter(getContext(), AndroidImageAssets.getAll()));
        gridView.setOnItemClickListener((adapterView, view, position, l) ->
                mCallback.onImageSelected(position));
        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        // This makes sure the host activity has implemented the callback interface.
        // If not, throw an exception.
        try { mCallback = (OnImageClickListener) context; }
        catch (ClassCastException err) {
            throw new ClassCastException(context.toString()
                    + "must implement OnImageClickListener.");
        }
    }
}
