package com.java2wk1.bmackey.java2wk1term1504;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.java2wk1.bmackey.java2wk1term1504.libs.StorageHelper;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class MainActivity extends Activity implements FirstFragmentListener{

    FragmentManager manager;
    public static JSONObject mApiData;
    boolean connectionStatus;
    public String mTitle = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean connectionStatus = new NetworkCheck(this).connectionStatus();
        if (connectionStatus){
            Toast.makeText(getApplicationContext(), "Connection found", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Connection unavailable", Toast.LENGTH_SHORT).show();
        }

        manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.masterContainer, new MasterFrag()).commit();
        manager.beginTransaction().replace(R.id.detailContainer, new DetailFrag()).commit();
        loadSavedData();
    }


    @Override
    public void entryClickListener(JSONObject data) {

        manager = getFragmentManager();
        Fragment secondFrag = DetailFrag.instanof(data);
        manager.beginTransaction().replace(R.id.detailContainer, secondFrag).commit();
    }

    public void listItemPressed(String myTitleMovie){
        if (connectionStatus){
            Toast.makeText(getApplicationContext(), "Connection found", Toast.LENGTH_SHORT).show();
            try {
                mTitle = myTitleMovie;
                new getAsyncData().execute();
            }catch (Exception e){

            }
        }else{
            Toast.makeText(getApplicationContext(), "Connection unavailable", Toast.LENGTH_SHORT).show();
            DetailFrag detailFrag = new DetailFrag();
            getFragmentManager().beginTransaction().replace(R.id.detailContainer, detailFrag).commit();
        }
    }



    @Override
    public void mySearch(String myMovie) {

            String myURL = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=6cvyrv4n86brwgx7s528kzru&q=";
            String queryURL = "&page_limit=5";

            try {
                URL myQueryURL = new URL(myURL + myMovie + queryURL);

                new getAsyncData().execute(myQueryURL);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

    }

    @Override
    public void entryClickListener(String query) {

    }


    private class getAsyncData extends AsyncTask<URL, Integer, JSONObject> {

        final String TAG = "API DEMO ASYNCTASK";

        public ArrayList<String> myTitles = new ArrayList<String>();

        protected void onPreExecute(){

        }

        @Override
        protected JSONObject doInBackground(URL... urls) {

            String jsonString = "";

            if (connectionStatus) {

                Toast.makeText(getApplicationContext(), "Good Network Connection", Toast.LENGTH_SHORT).show();

                //COLLECT STRING RESPONSES FROM API
                for (URL queryURL : urls) {
                    try {
                        URLConnection conn = queryURL.openConnection();
                        jsonString = IOUtils.toString(conn.getInputStream());
                        break;
                    } catch (Exception e) {
                        Log.e(TAG, "Could not establish URLConnection to " + queryURL.toString());
                    }
                }

                Log.i(TAG, "Received Data: " + jsonString);


                //CONVERT API STRING RESPONSE TO JSONOBJECT

                JSONObject apiData;
                JSONArray apiArray;

                try {
                    apiData = new JSONObject(jsonString);
                    apiArray = apiData.getJSONArray("movies");
                    String movieTitles;
                    for (int i = 0; i < 1; i++) {
                        apiData = apiArray.getJSONObject(i);
                        movieTitles = apiData.getJSONObject("movies").getString("title");
                        myTitles.add(movieTitles);

                    }
                } catch (Exception e) {
                    Log.e(TAG, "Cannot convert API response to JSON");
                    apiData = null;
                }

                try {
                    apiData = (apiData != null) ? apiData.getJSONArray("movies").getJSONObject(0) : null;
                    Log.i(TAG, "API JSON data received: " + apiData.toString());
                } catch (Exception e) {
                    Log.e(TAG, "Could not parse data record from response: " + apiData.toString());
                    apiData = null;
                }

                mApiData = apiData;
                return mApiData;
            }else {
                Toast.makeText(getApplicationContext(), "No Connection!", Toast.LENGTH_SHORT).show();

                // Load backup Data //
            }

            return mApiData;
        }

        protected void onPostExecute(JSONObject apiData) {
            MyJsonData result = new MyJsonData(apiData);
            save(mApiData);
            loadDetailView(result);


        }
        public void loadDetailView(MyJsonData myJsonData){
            ((TextView) findViewById(R.id.movRatingDetail)).setText(myJsonData.getRating());
            ((TextView) findViewById(R.id.movLengthDetail)).setText(String.valueOf(myJsonData.getRuntime()));
            ((TextView) findViewById(R.id.movCRatingDetail)).setText(myJsonData.getCriticsRating());
            ((TextView) findViewById(R.id.movActorDetail)).setText(myJsonData.getName());

        }


    }

    public void save(JSONObject jsonObject){
        String jStr = jsonObject.toString();
        StorageHelper.SaveData(jStr, this);
    }

    public void loadSavedData() {
        String savedData = StorageHelper.LoadData(this);
        try {
            mApiData = new JSONObject(savedData);
        } catch (JSONException e) {

        }
    }



}
