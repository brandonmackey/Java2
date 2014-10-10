// Brandon Mackey
// Java 2
// Week 1
// Term: 1410

package com.java1wk4.bmackey.connectedapp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by brandonmackey on 8/25/14.
 */
public class MyJsonData {
    static String TAG = "API App";

    private String mRating;
    private Integer mRuntime;
    private String mCriticsRating;
    private String mName;
    private String mtitle;

    public MyJsonData(JSONObject movieData) {
        try {
            mRating  = movieData.getString("mpaa_rating");
            mRuntime = movieData.getInt("runtime");
            mCriticsRating  = movieData.getJSONObject("ratings").getString("critics_rating");
            mName  = movieData.getJSONArray("abridged_cast").getJSONObject(0).getString("name");

            mtitle  = movieData.getString("title");
        } catch (JSONException e) {
            Log.e(TAG, "Error pulling json");
        }

    }

    public String getRating(){
        return mRating;
    }


    public Integer getRuntime(){
        return mRuntime;
    }


    public String getCriticsRating(){
        return mCriticsRating;
    }


    public String getName(){
        return mName;
    }

    public String getTitle(){
        return mtitle;
    }

// END //

}
