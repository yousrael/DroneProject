package com.example.yousra.droneproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;

import android.util.Log;
import android.widget.Toast;


import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import objects.DroneService;
import objects.PositionDrone;

import retrofit.Callback;
import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class Main2Activity extends FragmentActivity {
    GPsTracker gps;
    SupportMapFragment map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gps = new GPsTracker(Main2Activity.this);
       map= ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));


        // check if GPS enabled
        if (gps.canGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }

        //appelSynchrone();
        appelAsynchrone();

    }
    private void appelSynchrone() {
        new ListReposTask().execute("florent37");
    }

    private void appelAsynchrone() {
        DroneService githubService = new RestAdapter.Builder()
                .setEndpoint(DroneService.ENDPOINT)
                .setLog(new AndroidLog("retrofit"))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()
                .create(DroneService.class);

        githubService.getPosition( new Callback<PositionDrone>() {
            @Override
            public void success(PositionDrone droneposition, Response response) {
                afficherRepos(droneposition);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public void afficherRepos(PositionDrone position) {
        Toast.makeText(this, "Position du drone : " + position.getPosition().get(0)+","+position.getPosition().get(1), Toast.LENGTH_SHORT).show();
        Log.e("============>",position.getPosition().get(0)+"");
        LatLng PERTH = new LatLng(-31.90, 115.86);

                //.position(PERTH)
                //.draggable(true));

    }

    public void notAllowed() {
        Toast.makeText(this, "Impossible d'effectuer cette action", Toast.LENGTH_SHORT).show();
    }

    class ListReposTask extends AsyncTask<String, Void,PositionDrone> {

        @Override
        protected PositionDrone doInBackground(String... params) {
            DroneService githubService = new RestAdapter.Builder()
                    .setEndpoint(DroneService.ENDPOINT)
                    .setErrorHandler(new ErrorHandler() {
                        @Override
                        public Throwable handleError(RetrofitError cause) {
                            Response r = cause.getResponse();
                            if (r != null && r.getStatus() == 405) {
                                Main2Activity.this.notAllowed();
                            }
                            return cause;
                        }
                    })
                    .setRequestInterceptor(
                            new RequestInterceptor() {
                                @Override
                                public void intercept(RequestFacade request) {
                                    //ajoute "baerer: 1234567890" en header de chaque requête
                                    request.addHeader("bearer", "1234567890");
                                }
                            }
                    ).build().create(DroneService.class);


            PositionDrone position = githubService.position();

            return position;
        }

        @Override
        protected void onPostExecute(PositionDrone position) {
            super.onPostExecute(position);
            afficherRepos(position);
        }
    }
}
