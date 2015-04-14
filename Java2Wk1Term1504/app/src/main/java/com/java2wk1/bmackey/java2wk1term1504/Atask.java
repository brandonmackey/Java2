package com.java2wk1.bmackey.java2wk1term1504;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.net.URL;
import java.net.URLConnection;

/**
 * Created by brandonmackey on 4/12/15.
 */
public class Atask {

    public static JSONObject mApiData;

    public class getAsyncData extends AsyncTask<URL, Integer, JSONObject> {

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

            mApiData = apiData;
            return mApiData;

        }
    }
}
