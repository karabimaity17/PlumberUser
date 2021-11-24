package com.plumber.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SplashScreen extends AppCompatActivity {
    ImageView imageView;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        imageView= findViewById(R.id.imageView);
        text= findViewById(R.id.text);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        text.startAnimation(animation);
        Glide.with(SplashScreen.this)
                .load(R.drawable.plumbers_gif2)
                .into(imageView);
        Thread timer = new Thread(){

            @Override
            public void run() {

                try {
                    sleep(5000);
                    startActivity(new Intent(SplashScreen.this,WelcomeActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                    super.run();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        };
        timer.start();
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        final Configuration override = new Configuration(newBase.getResources().getConfiguration());
        override.fontScale = 1.0f;
        applyOverrideConfiguration(override);
    }
}