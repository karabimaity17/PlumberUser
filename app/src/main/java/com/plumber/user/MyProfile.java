package com.plumber.user;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MyProfile extends AppCompatActivity {
    CardView edit_profile_bottomsheet,other_bottomsheet;
    CardView back_btn;
    TextView nameToolbar,nameToolbar2;
    Button schedule_btn;
    BottomSheetBehavior sheetBehavior,sheetBehavior2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        edit_profile_bottomsheet = findViewById(R.id.edit_profile_bottomsheet);
        other_bottomsheet = findViewById(R.id.other_bottomsheet);
        nameToolbar = findViewById(R.id.nameToolbar);
        nameToolbar2 = findViewById(R.id.nameToolbar2);
        back_btn = findViewById(R.id.back_btn);
        schedule_btn = findViewById(R.id.schedule_btn);
        sheetBehavior = BottomSheetBehavior.from(other_bottomsheet);
        sheetBehavior2 = BottomSheetBehavior.from(edit_profile_bottomsheet);

        if(sheetBehavior.getState()==BottomSheetBehavior.STATE_EXPANDED){
            sheetBehavior2.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
        if(sheetBehavior2.getState()==BottomSheetBehavior.STATE_COLLAPSED){
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });

        nameToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sheetBehavior2.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        nameToolbar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        schedule_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyProfile.this,ScheduleHistory.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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