package com.example.yousra.droneproject;

import android.preference.PreferenceActivity;
import android.support.v4.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;




import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends FragmentActivity {
    GPsTracker gps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gps = new GPsTracker(MainActivity.this);

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
    }

    /*public void getPublicTimeline() throws JSONException {
        ApiREST.get("https://jsonplaceholder.typicode.com/posts", null, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, JSONObject response) {
                Log.e("============>", response + "");
            }


        });

    }*/


   /* public void getPublicTimeline() throws JSONException {
        ApiREST.get("statuses/public_timeline.json", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
            }


        });
    }*/



}