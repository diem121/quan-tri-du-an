package com.example.BookShop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends Activity {


    private static int SPLASH_TIME_OUT= 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        ImageView Loading_library = findViewById(R.id.loading_library);
        Glide.with(this).load(R.drawable.loading).into(Loading_library);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                OpenMainActivity();
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
    public void OpenMainActivity(){
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
    }

}
