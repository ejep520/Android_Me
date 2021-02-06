package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.android_me.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    private final static int BODY_PART_TYPES = 12;

    private int mHeadIndex = 0, mBodyIndex = 0, mLegIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button nextButton = findViewById(R.id.next_button);
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

    @Override
    public void onImageSelected(Integer position) {
        int bodyPart = position / BODY_PART_TYPES;
        switch (bodyPart) {
            case 0:
                mHeadIndex = position % BODY_PART_TYPES;
                break;
            case 1:
                mBodyIndex = position % BODY_PART_TYPES;
                break;
            case 2:
                mLegIndex = position % BODY_PART_TYPES;
                break;
            default:
                break;
        }
    }
}
