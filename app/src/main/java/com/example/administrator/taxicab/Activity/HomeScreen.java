package com.example.administrator.taxicab.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.taxicab.App.PrefManager;
import com.example.administrator.taxicab.ConfirmBooking.activity.Confirm_Booking;
import com.example.administrator.taxicab.UserRegistration.activity.Login;
import com.example.administrator.taxicab.R;
import com.example.administrator.taxicab.Utils.MapUtils.CustomSupportMapFragment;
import com.example.administrator.taxicab.Utils.MapUtils.TouchableWrapper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        OnMapReadyCallback, LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private Button ridenow;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Location mLastLocation;
    private Marker mCurrLocationMarker;
    private TextView locationTxt,dropLocationTxt;
    private LinearLayout carLayout;
    private  Toolbar toolbar;
    private  DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private  NavigationView navigationView;
    private View mapView;
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    public static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 99;
    private PrefManager pref;
    private ImageView imgCar1,imgCar2,imgCar3,imgCar4,imgCar5;
    private AppBarLayout appBarLayout;
    private Animation animShow, animHide,animHideReverse,animShowReverse;
    private ActionBar actionbar;
    private View mainLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initializations();

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkLocationPermission();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        CustomSupportMapFragment mapFragment = (CustomSupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.setOnDragListener(new TouchableWrapper.OnDragListener() {
            @Override
            public boolean onDrag(MotionEvent motionEvent) {
                switch (motionEvent.getActionMasked())
                {
                    case MotionEvent.ACTION_MOVE:
                        Log.d("ON_MOVE", String.format("ME: %s", motionEvent));

                        carLayout.startAnimation( animHide );
                        carLayout.setVisibility(View.GONE);

                       /* appBarLayout.startAnimation( animShowReverse );
                        appBarLayout.setVisibility(View.GONE);
                        toolbar.startAnimation( animShowReverse );
                        toolbar.setVisibility(View.GONE);

                        //setDrawerState( false);

                        if(actionbar!=null)
                        {
                            if(actionbar.isShowing())
                            {
                                actionbar.hide();
                            }
                        }
*/

                        break ;
                    case MotionEvent.ACTION_DOWN:
                        Log.d("ON_DOWN", String.format("ME: %s", motionEvent));

                        carLayout.startAnimation( animHide );
                        carLayout.setVisibility(View.GONE);

/*
                        appBarLayout.startAnimation( animShowReverse );
                        appBarLayout.setVisibility(View.GONE);
                        toolbar.startAnimation( animShowReverse );
                        toolbar.setVisibility(View.GONE);

                        //setDrawerState( false);
                        if(actionbar!=null)
                        {
                           if(actionbar.isShowing())
                           {
                               actionbar.hide();
                           }
                        }
*/



                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("ON_UP", String.format("ME: %s", motionEvent));
                        int code = motionEvent.getAction() & MotionEvent.ACTION_MASK;
                        if ((code == MotionEvent.ACTION_POINTER_UP) || (code == MotionEvent.ACTION_UP) || (code == MotionEvent.ACTION_CANCEL))
                        {
                            carLayout.setVisibility(View.VISIBLE);
                            carLayout.startAnimation( animShow );

/*
                            appBarLayout.setVisibility(View.VISIBLE);
                            appBarLayout.startAnimation( animHideReverse );

                            toolbar.setVisibility(View.VISIBLE);
                            toolbar.startAnimation( animHideReverse );
                            setSupportActionBar(toolbar);
*/

                            //setDrawerState( true);

                            return true;
                        }

                        carLayout.setVisibility(View.VISIBLE);
                        carLayout.startAnimation( animShow );

/*
                        appBarLayout.setVisibility(View.VISIBLE);
                        appBarLayout.startAnimation( animHideReverse );

                        toolbar.setVisibility(View.VISIBLE);
                        toolbar.startAnimation( animHideReverse );
                        setSupportActionBar(toolbar);
*/

                        //setDrawerState( false);


                        break;

                }
                    return true;
            }
        });

        mapFragment.getMapAsync(this);
        mapView = mapFragment.getView();

        openGooglePlacePicker();
        rideNowClickListener();
        carImgClickListener();


      }

      private void initializations()
      {
          pref=new PrefManager(this);
          toolbar = (Toolbar) findViewById(R.id.toolbar);
          carLayout=(LinearLayout)findViewById(R.id.car_layout);
          appBarLayout=(AppBarLayout)findViewById(R.id.appbar_layout);
          mainLayout=(View)findViewById(R.id.main_layout);
          setSupportActionBar(toolbar);
          actionbar=getSupportActionBar();

          locationTxt=(TextView)findViewById(R.id.myLocation);
          dropLocationTxt=(TextView)findViewById(R.id.dropLocation);

          drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
          toggle = new ActionBarDrawerToggle(
                  this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
          drawer.setDrawerListener(toggle);
          toggle.syncState();

          navigationView = (NavigationView) findViewById(R.id.nav_view);
          navigationView.setNavigationItemSelectedListener(this);
          ridenow = (Button)findViewById(R.id.ridenow);

          animShow = AnimationUtils.loadAnimation( this, R.anim.show);
          animHide = AnimationUtils.loadAnimation( this, R.anim.hide);
          animShowReverse = AnimationUtils.loadAnimation( this, R.anim.show_reverse);
          animHideReverse = AnimationUtils.loadAnimation( this, R.anim.hide_reverse);

          imgCar1=(ImageView)findViewById(R.id.car1);
          imgCar2=(ImageView)findViewById(R.id.car2);
          imgCar3=(ImageView)findViewById(R.id.car3);
          imgCar4=(ImageView)findViewById(R.id.car4);
          imgCar5=(ImageView)findViewById(R.id.car5);

      }

      public void setDrawerState(boolean isEnabled) {
        if ( isEnabled ) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            toggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_UNLOCKED);
            toggle.setDrawerIndicatorEnabled(true);
            toggle.syncState();

        }
        else {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            toggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            toggle.setDrawerIndicatorEnabled(false);
            toggle.syncState();
        }
    }

      private void openGooglePlacePicker()
      {
          dropLocationTxt.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view)
              {
                  AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                          .setTypeFilter(AutocompleteFilter.TYPE_FILTER_REGIONS)
                          .setCountry("IN")
                          .build();
                  try {
                      Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                              .setFilter(typeFilter)

                              .build(HomeScreen.this);
                      startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                  } catch (GooglePlayServicesRepairableException e) {
                      // TODO: Handle the error.
                  } catch (GooglePlayServicesNotAvailableException e) {
                      // TODO: Handle the error.
                  }
              }
          });

      }

      private void rideNowClickListener()
      {
          ridenow.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(HomeScreen.this,Confirm_Booking.class);
                  startActivity(intent);
              }
          });

      }

      private void carImgClickListener()
      {
          imgCar1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  imgCar1.setBackgroundResource(R.drawable.circularview_selected);
                  imgCar2.setBackgroundResource(R.drawable.circularview);
                  imgCar3.setBackgroundResource(R.drawable.circularview);
                  imgCar4.setBackgroundResource(R.drawable.circularview);
                  imgCar5.setBackgroundResource(R.drawable.circularview);
              }
          });
          imgCar2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  imgCar1.setBackgroundResource(R.drawable.circularview);
                  imgCar2.setBackgroundResource(R.drawable.circularview_selected);
                  imgCar3.setBackgroundResource(R.drawable.circularview);
                  imgCar4.setBackgroundResource(R.drawable.circularview);
                  imgCar5.setBackgroundResource(R.drawable.circularview);
              }
          });
          imgCar3.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  imgCar1.setBackgroundResource(R.drawable.circularview);
                  imgCar2.setBackgroundResource(R.drawable.circularview);
                  imgCar3.setBackgroundResource(R.drawable.circularview_selected);
                  imgCar4.setBackgroundResource(R.drawable.circularview);
                  imgCar5.setBackgroundResource(R.drawable.circularview);
              }
          });
          imgCar4.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  imgCar1.setBackgroundResource(R.drawable.circularview);
                  imgCar2.setBackgroundResource(R.drawable.circularview);
                  imgCar3.setBackgroundResource(R.drawable.circularview);
                  imgCar4.setBackgroundResource(R.drawable.circularview_selected);
                  imgCar5.setBackgroundResource(R.drawable.circularview);
              }
          });
          imgCar5.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  imgCar1.setBackgroundResource(R.drawable.circularview);
                  imgCar2.setBackgroundResource(R.drawable.circularview);
                  imgCar3.setBackgroundResource(R.drawable.circularview);
                  imgCar4.setBackgroundResource(R.drawable.circularview);
                  imgCar5.setBackgroundResource(R.drawable.circularview_selected);
              }
          });

         }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //   LatLng sydney = new LatLng(-34, 151);
        //  mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //   mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        //mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //Initialize Google Play Services
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

        if (mapView != null &&
                mapView.findViewById(Integer.parseInt("1")) != null) {
            // Get the button view
            View locationButton = ((View) mapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
            // and next place it, on bottom right (as Google Maps app)
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)
                    locationButton.getLayoutParams();
            // position on right bottom
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            layoutParams.addRule(RelativeLayout.ABOVE, R.id.car_layout);


        }


    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected( Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
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
    public void onConnectionFailed( ConnectionResult connectionResult) {

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
       // markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
       // mCurrLocationMarker = mMap.addMarker(markerOptions);
        int height = 100;
        int width = 100;
        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.red_car_top);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        // latitude and longitude
        double latitude = 19.886131297648284;
        double longitude = 75.36875165998936;

// create marker
        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude));

// Changing marker icon
        marker.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
        marker.rotation(location.getBearing()).flat(true).anchor(0.5f, 0.5f)
                .alpha((float) 0.91);

// adding marker
        mCurrLocationMarker=mMap.addMarker(marker);


        // latitude and longitude
        double latitude1 = 19.88743688238253;
        double longitude1 = 75.36426030099392;

// create marker
        MarkerOptions marker1 = new MarkerOptions().position(new LatLng(latitude1, longitude1));

// Changing marker icon
        marker1.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
        marker1.rotation(location.getBearing()).flat(true).anchor(0.5f, 0.5f)
                .alpha((float) 0.91);

// adding marker
        mCurrLocationMarker=mMap.addMarker(marker1);


        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        locationTxt.setText(getAddress(HomeScreen.this,location.getLatitude(),location.getLongitude()));


        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }



        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {

                locationTxt.setText(getAddress(getApplicationContext(),cameraPosition.target.latitude,cameraPosition.target.longitude));

                Log.i("Latitude", String.valueOf(cameraPosition.target.latitude));
                Log.i("Langitude", String.valueOf(cameraPosition.target.longitude));

/*
                if(!mMapIsTouched)
                {
                    carLayout.animate()
                            .translationY(0)
                            .alpha(0.0f)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    carLayout.setVisibility(View.GONE);
                                }
                            });

                }
                else
                {
                    // Prepare the View for the animation
                    carLayout.setVisibility(View.VISIBLE);
                    carLayout.setAlpha(0.0f);

// Start the animation
                    carLayout.animate()
                            .translationY(carLayout.getHeight())
                            .alpha(1.0f)
                            .setListener(null);
                }
*/

                //  Log.i("centerLong", String.valueOf(cameraPosition.target.longitude));
            }
        });

    }

    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_FINE_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_FINE_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // Permission was granted.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            //You can add here other case statements according to your requirement.
        }
    }

    public String getAddress(Context context, double lat, double lng) {
        try
        {

            Geocoder geocoder = new Geocoder(context, Locale.getDefault());

            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);

            String add = obj.getAddressLine(0);
            add = add + "\n" + obj.getAdminArea();
            add = add + "\n" + obj.getSubAdminArea();
            add = add + "\n" + obj.getLocality();
            add = add + "\n" + obj.getSubThoroughfare();

            Log.d("Area",add);


            return add;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        else  if(id==R.id.nav_log_out)
        {
            pref.setLoggedOut();
            Intent i=new Intent(HomeScreen.this,Login.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
            return true;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK)
            {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.i("HomeScreen", "Place: " + place.getName());
                dropLocationTxt.setText(place.getName());
            }
            else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.i("HomeScreen", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }

    }


   }
