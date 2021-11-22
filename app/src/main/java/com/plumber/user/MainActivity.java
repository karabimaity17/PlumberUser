package com.plumber.user;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationView;
import com.infideap.drawerbehavior.AdvanceDrawerLayout;
import com.plumber.user.Model.PlumberModel;
import com.plumber.user.Model.ServiceModel;
import com.ramotion.foldingcell.FoldingCell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
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

public class MainActivity extends AppCompatActivity /*implements OnMapReadyCallback,
        LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener*/{

    private AppBarConfiguration mAppBarConfiguration;

//    private GoogleMap mMap;
//    Location mLastLocation;
//    Marker mCurrLocationMarker;
//    GoogleApiClient mGoogleApiClient;
//    LocationRequest mLocationRequest;


    CardView option_bottomsheet;
    BottomSheetBehavior sheetBehavior;
    RecyclerView recycler_category,recycler_plumbers;
    ServiceAdapter serviceAdapter;
    List<ServiceModel> serviceModels = new ArrayList<>();
    PlumberAdapter plumberAdapter;
    List<PlumberModel> plumberModels = new ArrayList<>();
    TextView txt_location;
    User user;

    AdvanceDrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user= new User(MainActivity.this);


        //mapping
        recycler_category = findViewById(R.id.recycler_view);
        txt_location = findViewById(R.id.txt_location);
        recycler_plumbers = findViewById(R.id.recycler_plumbers);
        option_bottomsheet = findViewById(R.id.option_bottomsheet);
        sheetBehavior = BottomSheetBehavior.from(option_bottomsheet);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);

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

        //set location
      //  txt_location.setText(user.getLocation());
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
        int selected_position = 0;
        Context context;
        List<ServiceModel> serviceModelList;
        public int[] mColors = {R.drawable.gradiant3,R.drawable.gradiant1,R.drawable.gradiant2,R.drawable.gradiant4,R.drawable.gradiant5};
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
            holder.bind(serviceModelList.get(position));

            holder.txt_emergency.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, EmergencyBooking.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            });


            holder.txt_schedule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    startActivity(new Intent(MainActivity.this, ScheduleBooking.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            });



        }

        @Override
        public int getItemCount() {
            return serviceModelList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView service_name,txt_emergency,txt_schedule;
            ImageView service_image,img;
            LinearLayout lin;
            RelativeLayout rel;
            CardView card_view;
            FoldingCell fc;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                service_name=itemView.findViewById(R.id.service_name);
                card_view=itemView.findViewById(R.id.card_view);
                service_image=itemView.findViewById(R.id.service_image);

                rel=itemView.findViewById(R.id.rel);
                lin=itemView.findViewById(R.id.lin);
                img=itemView.findViewById(R.id.img);
                txt_emergency=itemView.findViewById(R.id.txt_emergency);
                txt_schedule=itemView.findViewById(R.id.txt_schedule);

            }
            void bind(final ServiceModel model) {
                RotateAnimation anim = new RotateAnimation(90, 0);
                RotateAnimation anim2 = new RotateAnimation(0, 90);
                anim.setFillAfter(true);
                anim2.setFillAfter(true);
                if (selected_position == -1 || selected_position == 0) {
                    lin.setVisibility(View.GONE);
                    img.setImageResource(R.drawable.right_arrow2);
                } else {
                    if (selected_position == getAdapterPosition() ) {
                        lin.setVisibility(View.VISIBLE);
                        img.setImageResource(R.drawable.arrow_down);

                    } else {
                        lin.setVisibility(View.GONE);
                        img.setImageResource(R.drawable.right_arrow2);
                    }
                }

                rel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (lin.getVisibility()==View.VISIBLE){

                            lin.setVisibility(View.GONE);
                            img.setImageResource(R.drawable.right_arrow2);
                        }else {


                            lin.setVisibility(View.VISIBLE);
                            img.setImageResource(R.drawable.arrow_down);
                        }
                    }
                });
            }
        }


    }
    private void toggleBottomSheet() {
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);


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

/*    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

    }
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }*/



//    @Override
//    public boolean onSupportNavigateUp() {
////        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//               || super.onSupportNavigateUp();
//    }
}