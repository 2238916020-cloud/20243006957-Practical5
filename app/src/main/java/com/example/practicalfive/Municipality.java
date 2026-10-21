package com.example.practicalfive;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Second Activity. Layout and dynamic customisation are filled in by later commits;
 * the shared Intent extra key is declared here so both Activities use the same constant.
 */
public class Municipality extends AppCompatActivity {

    public static final String EXTRA_MUNICIPALITY = "municipality";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);
    }
}
