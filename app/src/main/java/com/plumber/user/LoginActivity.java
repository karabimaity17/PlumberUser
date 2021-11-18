package com.plumber.user;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    TextView btnlogin,text_login,text_plumber;
    TextView register,txt_owner,landlord,agent;
    String type="Owner";
    Animation fadeAnimation,left_to_right,right_to_left,bottom_to_top;
    CardView login_curd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Mapping
        btnlogin = findViewById(R.id.btnlogin);
        register = findViewById(R.id.register);
        text_login = findViewById(R.id.text_login);
        text_plumber = findViewById(R.id.text_plumber);
        login_curd = findViewById(R.id.login_curd);
        txt_owner = findViewById(R.id.txt_owner);
        landlord = findViewById(R.id.landlord);
        agent = findViewById(R.id.agent);

        //set animation
        fadeAnimation= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out2);
        left_to_right= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_right);
        right_to_left= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_left);
        bottom_to_top= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_bottom);
        login_curd.setAnimation(fadeAnimation);
        text_login.setAnimation(left_to_right);
        text_plumber.setAnimation(right_to_left);
        register.setAnimation(bottom_to_top);

        final SpannableStringBuilder sb = new SpannableStringBuilder("Don't have an account ? Register Here");

        final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
        sb.setSpan(bss, 24, 37, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        register.setText(sb);

        //going to register activity
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });

        /*selecting user type*/

        txt_owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!type.equals("Owner")) {
                    type="Owner";
                    txt_owner.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checked, 0, 0, 0);
                    txt_owner.setBackgroundColor(getColor(R.color.colorAccent));

                    landlord.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
                    landlord.setBackgroundColor(getColor(R.color.colorWhite));

                    agent.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
                    agent.setBackgroundColor(getColor(R.color.colorWhite));
                    agent.setTextColor(getColor(R.color.colorBlack));
                    txt_owner.setTextColor(getColor(R.color.colorWhite));
                    landlord.setTextColor(getColor(R.color.colorBlack));
                }

            }
        });

        landlord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!type.equals("Landlord")) {
                    type="Landlord";
                    txt_owner.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
                    txt_owner.setBackgroundColor(getColor(R.color.colorWhite));
                    landlord.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checked, 0, 0, 0);
                    landlord.setBackgroundColor(getColor(R.color.colorAccent));
                    agent.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
                    agent.setBackgroundColor(getColor(R.color.colorWhite));
                    agent.setTextColor(getColor(R.color.colorBlack));
                    txt_owner.setTextColor(getColor(R.color.colorBlack));
                    landlord.setTextColor(getColor(R.color.colorWhite));
                }

            }
        });

        agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!type.equals("Agent")) {
                    type="Agent";
                    txt_owner.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
                    txt_owner.setBackgroundColor(getColor(R.color.colorWhite));
                    landlord.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
                    landlord.setBackgroundColor(getColor(R.color.colorWhite));
                    agent.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checked, 0, 0, 0);
                    agent.setBackgroundColor(getColor(R.color.colorAccent));
                    agent.setTextColor(getColor(R.color.colorWhite));
                    txt_owner.setTextColor(getColor(R.color.colorBlack));
                    landlord.setTextColor(getColor(R.color.colorBlack));
                }

            }
        });
        /*selecting user type*/

        //working with login button
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle=new Bundle();
//                bundle.putString("page_type","login");
//                Intent i = new Intent(LoginActivity.this, EduMe.class).putExtras(bundle);
//                startActivity(i);
//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        });
    }
    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        final Configuration override = new Configuration(newBase.getResources().getConfiguration());
        override.fontScale = 1.0f;
        applyOverrideConfiguration(override);
    }
}