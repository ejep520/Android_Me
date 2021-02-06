/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.android_me.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    private final static String LOG_TAG = AndroidMeActivity.class.getSimpleName();

    private BodyPartFragment mHeadFragment, mBodyFragment, mLegFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);


        // Check for an existing saved instance state.
        if (savedInstanceState == null) {

            // If not grab the intent bundle.
            Bundle intentBundle = getIntent().getExtras();

            // Instance a BodyPartFragment.
            mHeadFragment = new BodyPartFragment();
            mBodyFragment = new BodyPartFragment();
            mLegFragment = new BodyPartFragment();

            // Set the image lists.
            mHeadFragment.setmImageIds(AndroidImageAssets.getHeads());
            mBodyFragment.setmImageIds(AndroidImageAssets.getBodies());
            mLegFragment.setmImageIds(AndroidImageAssets.getLegs());

            // Grab the data out of the intent bundle...
            int headInt = intentBundle.getInt("headIndex");
            int bodyInt = intentBundle.getInt("bodyIndex");
            int legInt = intentBundle.getInt("legIndex");

            // Initialize the list index according to the intent.
            mHeadFragment.setmListIndex(headInt);
            mBodyFragment.setmListIndex(bodyInt);
            mLegFragment.setmListIndex(legInt);

            // Request the fragment manager.
            FragmentManager fragmentManager = getSupportFragmentManager();

            // Fragment transaction.
            fragmentManager.beginTransaction()
                    .add(R.id.head_container, mHeadFragment)
                    .add(R.id.body_container, mBodyFragment)
                    .add(R.id.leg_container, mLegFragment)
                    .commit();
        }
    }
    public void setFragmentImage(int fragmentType, int imageNumber) {
        switch(fragmentType) {
            case 0:
                mHeadFragment.setmListIndex(imageNumber);
                break;
            case 1:
                mBodyFragment.setmListIndex(imageNumber);
                break;
            case 2:
                mLegFragment.setmListIndex(imageNumber);
                break;
            default:
                break;
        }
    }
}
