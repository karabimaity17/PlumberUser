package com.plumber.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.plumber.user.Model.PlumberModel;
import com.plumber.user.Model.ScheduleHistoryModel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ScheduleHistory extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ScheduleHistoryModel> scheduleHistoryModels;
    ScheduleHistoryAdapter scheduleHistoryAdapter;
    CardView back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_history);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //mapping
        recyclerView = findViewById(R.id.list);
        back_btn = findViewById(R.id.back_btn);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        scheduleHistoryModels=new ArrayList<>();

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });

        //dummy data for recycler
        scheduleHistoryModels.add(new ScheduleHistoryModel("1","James Winston","PLUMBING EXPERT",
                "4.0","AUG 4, ","(2:00 PM)","THURSDAY ",R.drawable.plum_img1,"Confirmed"));
        scheduleHistoryModels.add(new ScheduleHistoryModel("1","James Winston","PLUMBING EXPERT",
                "4.0","OCT 25, ","(09:00 PM)","WEDNESDAY ",R.drawable.plum_img4,"Unconfirmed"));
        scheduleHistoryModels.add(new ScheduleHistoryModel("1","Will Jacks","PLUMBING EXPERT",
                "4.5","JULY 12, ","(11:00 AM)","MONDAY ",R.drawable.pro_pic,"Canceled"));
        scheduleHistoryAdapter = new ScheduleHistoryAdapter(this,scheduleHistoryModels);
        recyclerView.setAdapter(scheduleHistoryAdapter);

    }

    //Recycler Adapter
    public class ScheduleHistoryAdapter extends RecyclerView.Adapter<ScheduleHistoryAdapter.ViewHolder> {

        Context context;
        List<ScheduleHistoryModel> scheduleHistoryModels;
        public ScheduleHistoryAdapter(Context context, List<ScheduleHistoryModel> scheduleHistoryModels) {
            this.context = context;
            this.scheduleHistoryModels = scheduleHistoryModels;
        }

        @NonNull
        @Override
        public ScheduleHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.single_schedule_history, parent,false);
            return new ScheduleHistoryAdapter.ViewHolder(view);
        }

        @SuppressLint("ResourceType")
        @Override
        public void onBindViewHolder(@NonNull final ScheduleHistoryAdapter.ViewHolder holder, final int position) {
            holder.num_rating.setText(scheduleHistoryModels.get(position).getRatting());
            holder.name.setText(scheduleHistoryModels.get(position).getName());
            holder.desc.setText(scheduleHistoryModels.get(position).getDesc());
            holder.date.setText(scheduleHistoryModels.get(position).getDate());
            holder.day.setText(scheduleHistoryModels.get(position).getDay());
            holder.time.setText(scheduleHistoryModels.get(position).getTime());
            holder.status.setText(scheduleHistoryModels.get(position).getStatus());


            if(scheduleHistoryModels.get(position).getStatus().equals("Unconfirmed")){
                holder.search_lin.setVisibility(View.VISIBLE);
                holder.details_lin.setVisibility(View.GONE);
                holder.card_background.setCardBackgroundColor(Color.parseColor("#f2f3f9"));
                holder.line.setBackgroundColor(Color.parseColor("#e2e7f5"));
                holder.status.setTextColor(Color.parseColor("#6c7694"));
                holder.chat.setColorFilter(ContextCompat.getColor(context, R.color.unconfirm_color), android.graphics.PorterDuff.Mode.MULTIPLY);
                holder.call.setColorFilter(ContextCompat.getColor(context, R.color.unconfirm_color), android.graphics.PorterDuff.Mode.MULTIPLY);

                holder.plumber_image.setBackgroundResource(R.drawable.man);
            }
           else if(scheduleHistoryModels.get(position).getStatus().equals("Confirmed")){
                holder.search_lin.setVisibility(View.GONE);
                holder.details_lin.setVisibility(View.VISIBLE);
                holder.card_background.setCardBackgroundColor(Color.parseColor("#d8fcf7"));
                holder.line.setBackgroundColor(Color.parseColor("#cef7f3"));
                holder.status.setTextColor(Color.parseColor("#22c9c8"));
                holder.chat.setColorFilter(ContextCompat.getColor(context, R.color.confirm_color), android.graphics.PorterDuff.Mode.MULTIPLY);
                holder.call.setColorFilter(ContextCompat.getColor(context, R.color.confirm_color), android.graphics.PorterDuff.Mode.MULTIPLY);

                holder.plumber_image.setImageResource(scheduleHistoryModels.get(position).getImage());
            }
           else if(scheduleHistoryModels.get(position).getStatus().equals("Canceled")){
                holder.search_lin.setVisibility(View.GONE);
                holder.details_lin.setVisibility(View.VISIBLE);
                holder.card_background.setCardBackgroundColor(Color.parseColor("#fff2f2"));
                holder.line.setBackgroundColor(Color.parseColor("#ffe3e3"));
                holder.status.setTextColor(Color.parseColor("#ff3e3e"));
                holder.chat.setColorFilter(ContextCompat.getColor(context, R.color.cancel_color), android.graphics.PorterDuff.Mode.MULTIPLY);
                holder.call.setColorFilter(ContextCompat.getColor(context, R.color.cancel_color), android.graphics.PorterDuff.Mode.MULTIPLY);

                holder.plumber_image.setImageResource(scheduleHistoryModels.get(position).getImage());
            }
        }

        @Override
        public int getItemCount() {
            return scheduleHistoryModels.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView num_rating,day,date,time,desc,name,status;
            ImageView chat,call;
            CircleImageView plumber_image;
            CardView card_background;
            LinearLayout search_lin,details_lin;
            View line;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                num_rating=itemView.findViewById(R.id.num_rating);
                day=itemView.findViewById(R.id.day);
                date=itemView.findViewById(R.id.date);
                time=itemView.findViewById(R.id.time);
                desc=itemView.findViewById(R.id.desc);
                name=itemView.findViewById(R.id.name);
                status=itemView.findViewById(R.id.status);
                card_background=itemView.findViewById(R.id.card_background);
                plumber_image=itemView.findViewById(R.id.profile_image);
                search_lin=itemView.findViewById(R.id.find_lin);
                details_lin=itemView.findViewById(R.id.details_lin);
                line=itemView.findViewById(R.id.line);
                chat=itemView.findViewById(R.id.chat);
                call=itemView.findViewById(R.id.call);



            }

        }


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