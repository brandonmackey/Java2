// Brandon Mackey
// Java2
// Week 1
// Term: 1411

package com.java1wk4.bmackey.Java2WkOne1411;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends Activity {

    public static final String TAG = "MainActivity.TAG";

    ProgressBar myProgress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
           MovieTitleListFragment frag = MovieTitleListFragment.newInstance();

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, frag, MovieDetailsFragment.TAG).commit();
        }


//        myProgress = (ProgressBar) findViewById(R.id.progressBar);
//        myProgress.setVisibility(View.INVISIBLE);




        Button mainBtn = (Button) findViewById(R.id.btnSearch);
        mainBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView movieQuery = (TextView) findViewById(R.id.etMovieSearch);
                String query = movieQuery.getText().toString();
                query = query.replace(" ", "+");

                // Method for Hiding the Keyboard //
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                try{
                    String myURL = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=6cvyrv4n86brwgx7s528kzru&q=";
                    URL queryURL = new URL(myURL + query + "&page_limit=1");
                    new getData().execute(queryURL);
                } catch (Exception e){
                    Log.e(TAG, "Invalid Search");
                }

            }
        });
        // END OF onCREATE METHOD //
    }

//    public void findAndRemoveFragment() {
//        FragmentManager mgr = getFragmentManager();
//        ListFragment frag =
//                (ListFragment)mgr.findFragmentByTag(ListFragment.TAG);
//
//        if(frag == null) {
//            // No fragment found, possibly because the transaction
//            // hasn't completed yet.
//        } else {
//            // Fragment found. You can use it here.
//            // Fragment is still part of the activity.
//        }
//    }
//
//    public void findAndRemoveFragment() {
//        FragmentManager mgr = getFragmentManager();
//        ListFragment frag =
//                (ListFragment)mgr.findFragmentByTag(ListFragment.TAG);
//
//        if(frag == null) {
//            // No fragment found, possibly because the transaction
//            // hasn't completed yet.
//        } else {
//            // Fragment found. You can use it here.
//            FragmentTransaction trans = mgr.beginTransaction();
//            trans.remove(frag);
//            trans.commit();
//            // When the main thread runs, the fragment will be
//            // removed from the activity.
//        }
//    }



    private class getData extends AsyncTask<URL, Integer, JSONObject> {

        final String TAG = "API DEMO ASYNCTASK";

        protected void onPreExecute() {

        }

        @Override
        protected JSONObject doInBackground(URL... urls) {

            String jsonString = "";

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

            try {
                apiData = new JSONObject(jsonString);
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


            return apiData;
        }

        protected void onPostExecute(JSONObject apiData) {
            MyJsonData result = new MyJsonData(apiData);
            loadDetailView(result);
            myProgress.setVisibility(View.INVISIBLE);
        }

        public void loadDetailView(MyJsonData myJsonData) {
            // Create listview from

//            ((TextView) findViewById(R.id.tvDetailView)).setText(myJsonData.getRating());
//            ((TextView) findViewById(R.id.tvDetailView2)).setText(String.valueOf(myJsonData.getRuntime()));
//            ((TextView) findViewById(R.id.tvDetailView6)).setText(myJsonData.getCriticsRating());
//            ((TextView) findViewById(R.id.tvDetailView8)).setText(myJsonData.getName());

        }
    }



// END //
}
