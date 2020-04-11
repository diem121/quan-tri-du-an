package com.example.shima;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ShiftActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle(getString(R.string.screen_name_shift));
    }
}
