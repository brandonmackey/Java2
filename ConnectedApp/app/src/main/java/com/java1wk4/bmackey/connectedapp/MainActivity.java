// Brandon Mackey
// Java 2
// Week 1
// Term: 1410

package com.java1wk4.bmackey.connectedapp;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends Activity implements ListFragment.OnSearchListener{


    static String TAG = "API App / Fragments";
    boolean connectionStatus;
    String query;
    String myURL;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo ni = cm.getActiveNetworkInfo();
//
//        if (ni != null && ni.isConnected()){
//            if (ni.getType() == ConnectivityManager.TYPE_MOBILE){
//                Toast.makeText(this, "You have moblie network",Toast.LENGTH_SHORT).show();
//            }else if (ni.getType() == ConnectivityManager.TYPE_WIFI){
//                Toast.makeText(this, "Wifi Connected",Toast.LENGTH_SHORT).show();
//            }else{
//                Toast.makeText(this, "You have " + ni + " connection",Toast.LENGTH_SHORT).show();
//            }
//            Toast.makeText(this, "Data Loading...",Toast.LENGTH_SHORT).show();
//            //return true;
//        }else{
//            Toast.makeText(this, "You have no internet connection",Toast.LENGTH_SHORT).show();
//            //return false;
//        }



        if (savedInstanceState == null){
            Log.d(TAG, "View Fragment");
            // Create Fragment ////////////////////////////
            ListFragment listFrag = ListFragment.newInstance();
            getFragmentManager().beginTransaction().replace(R.id.list_fragment, listFrag, ListFragment.TAG).commit();

        }


        // Check for Orientation //
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            Toast.makeText(getApplicationContext(), "Please Rotate to Landscape!", Toast.LENGTH_SHORT).show();


        }else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){


        }



        Button mainBtn = (Button) findViewById(R.id.btnSearch);
        mainBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView movieQuery = (TextView) findViewById(R.id.etMovieSearch);
                query = movieQuery.getText().toString();
                query = query.replace(" ", "+");



                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                try{
                    // movie search query //
                    //String myURL = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=6cvyrv4n86brwgx7s528kzru&q=";
                    // movie title list for list view//
                    myURL = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?apikey=6cvyrv4n86brwgx7s528kzru&q=";
                    URL queryURL = new URL(myURL + query + "&page_limit=1");
                    new getData().execute(queryURL);
                } catch (Exception e){
                    Log.e(TAG, "Invalid Search");
                }

            }
        });
        // END OF onCREATE METHOD //
    }

//    public boolean isNetworkOnline() {
//        boolean status=false;
//        try{
//            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo netInfo = cm.getNetworkInfo(0);
//            if (netInfo != null && netInfo.getState()==NetworkInfo.State.CONNECTED) {
//                status= true;
//            }else {
//                netInfo = cm.getNetworkInfo(1);
//                if(netInfo!=null && netInfo.getState()==NetworkInfo.State.CONNECTED)
//                    status= true;
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//            return false;
//        }
//        return status;
//
//    }

//    public boolean connectionStatus(){
//        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo ni = cm.getActiveNetworkInfo();
//
//        if (ni != null && ni.isConnected()){
//            if (ni.getType() == ConnectivityManager.TYPE_MOBILE){
//                Toast.makeText(this, "You have moblie network",Toast.LENGTH_SHORT).show();
//            }else if (ni.getType() == ConnectivityManager.TYPE_WIFI){
//                Toast.makeText(this, "Wifi Connected",Toast.LENGTH_SHORT).show();
//            }else{
//                Toast.makeText(this, "You have " + ni + " connection",Toast.LENGTH_SHORT).show();
//            }
//            Toast.makeText(this, "Data Loading...",Toast.LENGTH_SHORT).show();
//            return true;
//        }else{
//            Toast.makeText(this, "You have no internet connection",Toast.LENGTH_SHORT).show();
//            return false;
//        }
//    }


//    private void getNetworkInfo(int toast){
//
//        String toastMsg = "";
//
//
//        switch (toast){
//            case 0:
//                toastMsg = "Cached data will be loaded";
//                break;
//            case 1:
//                toastMsg = "Check your connection ";
//                break;
//        }
//
//        Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_SHORT).show();
//
//    }


//            public void onClick(View v) {
//                TextView movieQuery = (TextView) findViewById(R.id.etMovieSearch);
//                query = movieQuery.getText().toString();
//                query = query.replace(" ", "+");

//   try{
//                    // movie search query //
//                    //String myURL = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=6cvyrv4n86brwgx7s528kzru&q=";
//                    // movie title list for list view//
//                    myURL = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?apikey=6cvyrv4n86brwgx7s528kzru&q=";
//                    URL queryURL = new URL(myURL + query + "&page_limit=1");
//                    new getData().execute(queryURL);
//                } catch (Exception e){
//                    Log.e(TAG, "Invalid Search");
//                }
//
//            }

    public void getApiData(String Str) {
        ListView myListView = (ListView) findViewById(R.id.lvMyListView);

        try {
            myURL = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?apikey=6cvyrv4n86brwgx7s528kzru&q=";
            URL queryURL = new URL(myURL + query + "&page_limit=1");
            new getData().execute(queryURL);
        } catch (Exception e){
            Log.e(TAG, "Invalid Search");
        }

    }






    public class getData extends AsyncTask<URL, Integer, JSONObject> {

        final String TAG = "API DEMO ASYNCTASK";

        protected void onPreExecute(){

        }

        @Override
        protected JSONObject doInBackground(URL... urls) {

            String jsonString = "";

            //COLLECT STRING RESPONSES FROM API
            for(URL queryURL : urls){
                try{
                    URLConnection conn = queryURL.openConnection();
                    jsonString = IOUtils.toString(conn.getInputStream());
                    break;
                } catch (Exception e){
                    Log.e(TAG, "Could not establish URLConnection to " + queryURL.toString());
                }
            }

            Log.i(TAG, "Received Data: " + jsonString);


            //CONVERT API STRING RESPONSE TO JSONOBJECT

            JSONObject apiData;
            JSONArray apiArray;

            try{
                apiData = new JSONObject(jsonString);
                apiArray = apiData.getJSONArray("data");
                String movieitles;
                for (int i =0; i < 1; i++){
                    apiData = apiArray.getJSONObject(i);
                    movieitles = apiData.getString("title");
                }
            } catch (Exception e){
                Log.e(TAG, "Cannot convert API response to JSON");
                apiData = null;
            }

            try{
                apiData = (apiData != null) ? apiData.getJSONArray("movies").getJSONObject(0) : null;
                Log.i(TAG, "API JSON data received: " + apiData.toString());
            } catch (Exception e){
                Log.e(TAG, "Could not parse data record from response: " + apiData.toString());
                apiData = null;
            }


            return apiData;
        }

        protected void onPostExecute(JSONObject apiData) {
            MyJsonData result = new MyJsonData(apiData);

            DisplayFragment displayFrag = (DisplayFragment)getFragmentManager().findFragmentByTag(DisplayFragment.TAG);

            getFragmentManager().beginTransaction().replace(R.id.display_fragment, displayFrag, DisplayFragment.TAG).commit();

        }
    }


// END //
}
