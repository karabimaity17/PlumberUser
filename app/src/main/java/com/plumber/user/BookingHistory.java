package com.plumber.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.plumber.user.Model.BookingHistoryModel;
import com.plumber.user.Model.ScheduleHistoryModel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
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

public class BookingHistory extends AppCompatActivity {
    RecyclerView recyclerView;
    List<BookingHistoryModel> bookingHistoryModels;
   BookingHistoryAdapter bookingHistoryAdapter;
    CardView back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history);
       // Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //mapping
        recyclerView = findViewById(R.id.list);
        back_btn = findViewById(R.id.back_btn);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookingHistoryModels=new ArrayList<>();

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });

        //dummy data for recycler
        bookingHistoryModels.add(new BookingHistoryModel("#12315462134","James Winston","PLUMBING EXPERT",
                "Schedule Booking","AUG 4, ","(2:00 PM)","THURSDAY ",R.drawable.plum_img1,"Completed"));
        bookingHistoryModels.add(new BookingHistoryModel("#21345697864","James Winston","PLUMBING EXPERT",
                "Emergency Booking","OCT 25, ","","WEDNESDAY ",R.drawable.plum_img4,"Completed"));
        bookingHistoryModels.add(new BookingHistoryModel("#531418002145","Will Jacks","PLUMBING EXPERT",
                "Schedule Booking","JULY 12, ","(11:00 AM)","MONDAY ",R.drawable.pro_pic,"Completed"));
        bookingHistoryAdapter = new BookingHistoryAdapter(this,bookingHistoryModels);
        recyclerView.setAdapter(bookingHistoryAdapter);
    }

    public class BookingHistoryAdapter extends RecyclerView.Adapter<BookingHistoryAdapter.ViewHolder> {

        Context context;
        List<BookingHistoryModel> bookingHistoryModels;
        public BookingHistoryAdapter(Context context, List<BookingHistoryModel> bookingHistoryModels) {
            this.context = context;
            this.bookingHistoryModels = bookingHistoryModels;
        }

        @NonNull
        @Override
        public BookingHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.single_booking_history, parent,false);
            return new BookingHistoryAdapter.ViewHolder(view);
        }

        @SuppressLint("ResourceType")
        @Override
        public void onBindViewHolder(@NonNull final BookingHistoryAdapter.ViewHolder holder, final int position) {
            holder.booking_type.setText(bookingHistoryModels.get(position).getBooking_type());
            holder.name.setText(bookingHistoryModels.get(position).getName());
            holder.desc.setText(bookingHistoryModels.get(position).getDesc());
            holder.date.setText(bookingHistoryModels.get(position).getDate());
            holder.day.setText(bookingHistoryModels.get(position).getDay());
            holder.time.setText(bookingHistoryModels.get(position).getTime());
            holder.status.setText(bookingHistoryModels.get(position).getStatus());
            holder.booking_id.setText(bookingHistoryModels.get(position).getId());
            holder.plumber_image.setImageResource(bookingHistoryModels.get(position).getImage());




        }

        @Override
        public int getItemCount() {
            return bookingHistoryModels.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView booking_type,day,date,time,desc,name,status,booking_id;
           // ImageView chat,call;
            CircleImageView plumber_image;
           // CardView card_background;
           // LinearLayout search_lin,details_lin;
            //View line;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                booking_type=itemView.findViewById(R.id.booking_type);
                booking_id=itemView.findViewById(R.id.b_id);
                day=itemView.findViewById(R.id.day);
                date=itemView.findViewById(R.id.date);
                time=itemView.findViewById(R.id.time);
                desc=itemView.findViewById(R.id.desc);
                name=itemView.findViewById(R.id.name);
                status=itemView.findViewById(R.id.status);
               // card_background=itemView.findViewById(R.id.card_background);
                plumber_image=itemView.findViewById(R.id.profile_image);
               // search_lin=itemView.findViewById(R.id.find_lin);
               // details_lin=itemView.findViewById(R.id.details_lin);
             //   line=itemView.findViewById(R.id.line);
              //  chat=itemView.findViewById(R.id.chat);
               // call=itemView.findViewById(R.id.call);



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