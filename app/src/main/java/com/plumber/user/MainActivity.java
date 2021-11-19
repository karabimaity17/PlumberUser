package com.plumber.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.infideap.drawerbehavior.AdvanceDrawerLayout;
import com.plumber.user.Model.PlumberModel;
import com.plumber.user.Model.ServiceModel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    RecyclerView recycler_category,recycler_plumbers;
    ServiceAdapter serviceAdapter;
    List<ServiceModel> serviceModels = new ArrayList<>();
    PlumberAdapter plumberAdapter;
    List<PlumberModel> plumberModels = new ArrayList<>();
    TextView txt_location;

    AdvanceDrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //mapping
        recycler_category = findViewById(R.id.recycler_view);
        txt_location = findViewById(R.id.txt_location);
        recycler_plumbers = findViewById(R.id.recycler_plumbers);

        //recycler view set
        recycler_category.setHasFixedSize(true);
        recycler_category.setLayoutManager(new GridLayoutManager(MainActivity.this,2));

        recycler_plumbers.setHasFixedSize(true);
        recycler_plumbers.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.HORIZONTAL,false));

        serviceModels.add(new ServiceModel("1","Plumbing",R.drawable.plumber));
        serviceAdapter = new ServiceAdapter(MainActivity.this,serviceModels);
        recycler_category.setAdapter(serviceAdapter);

        plumberModels.add(new PlumberModel("1","Plumbing","4.0",R.drawable.plum_img1));
        plumberModels.add(new PlumberModel("1","Plumbing","4.5",R.drawable.plum_img2));
        plumberModels.add(new PlumberModel("1","Plumbing","5.0",R.drawable.plum_img3));
        plumberModels.add(new PlumberModel("1","Plumbing","4.0",R.drawable.plum_img4));
        plumberAdapter = new PlumberAdapter(MainActivity.this,plumberModels);
        recycler_plumbers.setAdapter(plumberAdapter);

        //location
        txt_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SearchLocation.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        drawerToggle.setDrawerIndicatorEnabled(false);
        drawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        toolbar.setNavigationIcon(R.drawable.menu);


        drawer.setViewScale(Gravity.START, 0.9f);
        drawer.setRadius(Gravity.START, 35);
        drawer.setViewElevation(Gravity.START, 20);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
    }
    public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

        Context context;
        List<ServiceModel> serviceModelList;
        public int[] mColors = {R.drawable.gradiant2,R.drawable.gradiant1,R.drawable.gradiant3,R.drawable.gradiant4,R.drawable.gradiant5};
        public ServiceAdapter(Context context, List<ServiceModel> serviceModelList) {
            this.context = context;
            this.serviceModelList = serviceModelList;
        }

        @NonNull
        @Override
        public ServiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.single_service, parent,false);
            return new ServiceAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ServiceAdapter.ViewHolder holder, final int position) {
            holder.rel.setBackground(getDrawable(mColors[position]));
            holder.service_name.setText(serviceModelList.get(position).getName());
            holder.service_image.setImageResource(serviceModelList.get(position).getImage());

        }

        @Override
        public int getItemCount() {
            return serviceModelList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView service_name;
            ImageView service_image;
            RelativeLayout rel;
            CardView card_view;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                service_name=itemView.findViewById(R.id.service_name);
                card_view=itemView.findViewById(R.id.card_view);
                service_image=itemView.findViewById(R.id.service_image);

                rel=itemView.findViewById(R.id.rel);

            }

        }


    }

    public class PlumberAdapter extends RecyclerView.Adapter<PlumberAdapter.ViewHolder> {

        Context context;
        List<PlumberModel> plumberModels;
        public PlumberAdapter(Context context, List<PlumberModel> plumberModels) {
            this.context = context;
            this.plumberModels = plumberModels;
        }

        @NonNull
        @Override
        public PlumberAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.single_plumbers, parent,false);
            return new PlumberAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final PlumberAdapter.ViewHolder holder, final int position) {
            holder.num_rating.setText(plumberModels.get(position).getRating());
            holder.plumber_image.setImageResource(plumberModels.get(position).getImage());

        }

        @Override
        public int getItemCount() {
            return plumberModels.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView num_rating;
            ImageView plumber_image;
            RelativeLayout rel;
            CardView card_view;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                num_rating=itemView.findViewById(R.id.num_rating);
                card_view=itemView.findViewById(R.id.card_view);
                plumber_image=itemView.findViewById(R.id.plumber_image);

                rel=itemView.findViewById(R.id.rel);

            }

        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//    @Override
//    public boolean onSupportNavigateUp() {
////        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//               || super.onSupportNavigateUp();
//    }
}