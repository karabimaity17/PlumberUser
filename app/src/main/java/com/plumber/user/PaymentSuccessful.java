package com.plumber.user;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

public class PaymentSuccessful extends AppCompatActivity {
    TextView feedback,skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_successful);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        feedback = (TextView) findViewById(R.id.feedback);
        skip = (TextView) findViewById(R.id.skip);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(PaymentSuccessful.this,R.style.CustomAlertDialog);
                ViewGroup viewGroup = v.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_rating, viewGroup, false);
                TextView cancel=dialogView.findViewById(R.id.cancel);
                TextView add=dialogView.findViewById(R.id.add);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        startActivity(new Intent(PaymentSuccessful.this,BookingHistory.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        finish();
                    }
                });

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        startActivity(new Intent(PaymentSuccessful.this,BookingHistory.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        finish();
                    }



                });
                alertDialog.show();
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentSuccessful.this,MainActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(PaymentSuccessful.this,BookingHistory.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        this.finish();

    }

}