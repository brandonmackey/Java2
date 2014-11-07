// Brandon Mackey
// Java2
// Week 1
// Term: 1411

package com.java1wk4.bmackey.Java2WkOne1411;

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


    public MyJsonData(String rating, Integer runtime, String criticsRating, String name){
        mRating = rating;
        mRuntime = runtime;
        mCriticsRating = criticsRating;
        mName = name;
    }

    public MyJsonData(JSONObject movieData) {
        try {
            mRating  = movieData.getString("mpaa_rating");
            mRuntime = movieData.getInt("runtime");
            mCriticsRating  = movieData.getJSONObject("ratings").getString("critics_rating");
            mName  = movieData.getJSONArray("abridged_cast").getJSONObject(0).getString("name");
        } catch (JSONException e) {
            Log.e(TAG, "Error pulling json");
        }

    }

    public String getRating(){
        return mRating;
    }

    public void setRating(String mRating){
        this.mRating = mRating;
    }

    public Integer getRuntime(){
        return mRuntime;
    }

    public void setRuntime(Integer mRuntime){
        this.mRuntime = mRuntime;
    }

    public String getCriticsRating(){
        return mCriticsRating;
    }

    public void setCriticsRating(String mCriticsRating){
        this.mCriticsRating = mCriticsRating;
    }

    public String getName(){
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }



// END //

}
