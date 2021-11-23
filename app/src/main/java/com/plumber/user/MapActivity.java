package com.plumber.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class MapActivity extends AppCompatActivity {
    CardView search_bottomsheet,driver_bottomsheet;
    CardView back_btn;
    BottomSheetBehavior sheetBehavior,sheetBehavior2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        driver_bottomsheet = findViewById(R.id.driver_bottomsheet);
        search_bottomsheet = findViewById(R.id.search_bottomsheet);
        back_btn = findViewById(R.id.back_btn);
        sheetBehavior = BottomSheetBehavior.from(search_bottomsheet);
        sheetBehavior2 = BottomSheetBehavior.from(driver_bottomsheet);

        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        sheetBehavior2.setState(BottomSheetBehavior.STATE_HIDDEN);


        Thread timer = new Thread(){

            @Override
            public void run() {

                try {
                    sleep(5000);
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    sheetBehavior2.setState(BottomSheetBehavior.STATE_EXPANDED);
                    sheetBehavior2.setHideable(false);
                    super.run();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        };
        timer.start();

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        this.finish();

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        final Configuration override = new Configuration(newBase.getResources().getConfiguration());
        override.fontScale = 1.0f;
        applyOverrideConfiguration(override);
    }
}