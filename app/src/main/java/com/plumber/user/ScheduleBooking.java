package com.plumber.user;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.model.CalendarEvent;
import devs.mulham.horizontalcalendar.utils.CalendarEventsPredicate;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class ScheduleBooking extends AppCompatActivity {
    CardView btnBook, pick_time;
    TextView month, time;
    private HorizontalCalendar horizontalCalendar;
    BetterSpinner job_spinner;
    private static final String[] COUNTRIES = new String[]{
            "Leaky Pipes", "Running Toilet", "Water Heater Not Working", "Low Water Pressure", "Sewer Line Issues"
    };
    Typeface typeface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_booking);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        typeface = ResourcesCompat.getFont(this, R.font.fonts_medium);
        btnBook = findViewById(R.id.btnBook);
        month = findViewById(R.id.month);
        pick_time = findViewById(R.id.pick_time);
        time = findViewById(R.id.time);
        job_spinner = findViewById(R.id.job_type);
        job_spinner .setTypeface(typeface);
        /* start 2 months ago from now */

        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, 0);
        startDate.add(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat month_date = new SimpleDateFormat("MMM");
        String month_name = month_date.format(startDate.getTime());
        int year = startDate.get(Calendar.YEAR);

        month.setText(month_name + ", " + String.valueOf(year));
        /* end after 2 months from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 200);

        // Default Date set to Today.
        final Calendar defaultSelectedDate = Calendar.getInstance();

        horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(7)
                .configure()
                .formatTopText("MMM")
                .formatMiddleText("dd")
                .formatBottomText("EEE")
                .showTopText(true)
                .showBottomText(true)
                .textColor(Color.DKGRAY, getColor(R.color.colorPrimary))
                .selectedDateBackground(getDrawable(R.drawable.box))
                .colorTextMiddle(Color.DKGRAY, getColor(R.color.colorPrimary))
                .end()
                .defaultSelectedDate(defaultSelectedDate)
//                .addEvents(new CalendarEventsPredicate() {
//
//                    Random rnd = new Random();
//                    @Override
//                    public List<CalendarEvent> events(Calendar date) {
//                        List<CalendarEvent> events = new ArrayList<>();
//                        int count = rnd.nextInt(6);
//
//                        for (int i = 0; i <= count; i++){
//                            events.add(new CalendarEvent(Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)), "event"));
//                        }
//
//                        return events;
//                    }
//                })
                .build();

        // Log.i("Default Date", DateFormat.format("EEE, MMM d, yyyy", defaultSelectedDate).toString());

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                String selectedDateStr = DateFormat.format("EEE, MMM d, yyyy", date).toString();
                Toast.makeText(ScheduleBooking.this, selectedDateStr + " selected!", Toast.LENGTH_SHORT).show();
                //   Log.i("onDateSelected", selectedDateStr + " - Position = " + position);
                month.setText(DateFormat.format("MMM, yyyy", date));
            }

        });
        pick_time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ScheduleBooking.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        int hour = hourOfDay % 12;
                        if (hour == 0)
                            hour = 12;
                        time.setText(String.format("%02d:%02d %s", hour, minute,
                                hourOfDay < 12 ? "AM" : "PM"));
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ScheduleBooking.this,R.style.CustomAlertDialog);
                ViewGroup viewGroup = view.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_estimate, viewGroup, false);
                TextView cancel=dialogView.findViewById(R.id.cancel);
                TextView add=dialogView.findViewById(R.id.add);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(ScheduleBooking.this,BookingSuccessful.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        finish();
                        alertDialog.dismiss();
                    }



                });
                alertDialog.show();

            }
        });
        //spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        job_spinner.setAdapter(adapter);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            this.finish();
        }
        return super.onOptionsItemSelected(item);
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