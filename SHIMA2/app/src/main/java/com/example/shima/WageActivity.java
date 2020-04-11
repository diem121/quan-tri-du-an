package com.example.shima;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class WageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wage);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle(getString(R.string.screen_name_wage));
    }
}
