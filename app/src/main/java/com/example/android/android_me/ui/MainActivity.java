package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    private final static int BODY_PART_TYPES = 12;

    private int mHeadIndex = 0, mBodyIndex = 0, mLegIndex = 0;
    private boolean mTwoPane;
    private Bundle savedInstance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        savedInstance= savedInstanceState;
    }
    protected void onStart() {
        super.onStart();
        Button nextButton = findViewById(R.id.next_button);
        if (findViewById(R.id.android_me_linear_layout) != null) {
            mTwoPane = true;

            GridView gridView = findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);

            nextButton.setVisibility(View.GONE);

            if (savedInstance == null) {
                FragmentManager fragmentManager = getSupportFragmentManager();

                BodyPartFragment headFragment = new BodyPartFragment();
                BodyPartFragment bodyFragment = new BodyPartFragment();
                BodyPartFragment legFragment = new BodyPartFragment();

                headFragment.setmImageIds(AndroidImageAssets.getHeads());
                bodyFragment.setmImageIds(AndroidImageAssets.getBodies());
                legFragment.setmImageIds(AndroidImageAssets.getLegs());

                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .add(R.id.body_container, bodyFragment)
                        .add(R.id.leg_container, legFragment)
                        .commit();
            }
        } else {
            mTwoPane = false;
            nextButton.setOnClickListener((v) -> {
                Bundle b = new Bundle();
                b.putInt("headIndex", mHeadIndex);
                b.putInt("bodyIndex", mBodyIndex);
                b.putInt("legIndex", mLegIndex);

                final Intent intent = new Intent(this, AndroidMeActivity.class);
                intent.putExtras(b);

                startActivity(intent);
            });
        }
    }

    @Override
    public void onImageSelected(Integer position) {
        int bodyPart = position / BODY_PART_TYPES;
        int listIndex = position % BODY_PART_TYPES;
        if (mTwoPane) {
            BodyPartFragment newFragment = new BodyPartFragment();
            newFragment.setmListIndex(listIndex);
            switch(bodyPart) {
                case 0:
                    newFragment.setmImageIds(AndroidImageAssets.getHeads());
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container, newFragment)
                            .commit();
                    break;
                case 1:
                    newFragment.setmImageIds(AndroidImageAssets.getBodies());
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, newFragment)
                            .commit();
                    break;
                case 2:
                    newFragment.setmImageIds(AndroidImageAssets.getLegs());
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_container, newFragment)
                            .commit();
                    break;
                default:
                    break;
            }
        } else {
            switch(bodyPart) {
                case 0:
                    mHeadIndex = listIndex;
                    break;
                case 1:
                    mBodyIndex = listIndex;
                    break;
                case 2:
                    mLegIndex = listIndex;
                    break;
                default:
                    break;
            }
        }
    }
}
