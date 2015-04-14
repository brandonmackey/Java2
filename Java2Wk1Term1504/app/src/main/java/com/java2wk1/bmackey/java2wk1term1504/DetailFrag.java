package com.java2wk1.bmackey.java2wk1term1504;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by brandonmackey on 4/10/15.
 */
public class DetailFrag extends Fragment {

    ArrayList<MyJsonData> mMovieArray = new ArrayList<MyJsonData>();


    public static DetailFrag instanof (JSONObject data){

        DetailFrag frag = new DetailFrag();
        Bundle args = new Bundle();
        args.putString("Data", data.toString());
        frag.setArguments(args);

        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        Bundle args = getArguments();
        if (args != null && args.containsKey("Data")) {
            try {
                JSONObject json = new JSONObject(args.getString("Data"));
                JSONArray array = json.getJSONObject("data").getJSONArray("movies");
                for(int i=0; i<array.length();i++) {
                    JSONObject data = array.getJSONObject(i).getJSONObject("data");
                    String title = data.getString("title");
                    MyJsonData myJsonData = new MyJsonData(data);
                    mMovieArray.add(myJsonData);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
//            setView(args.getString(RATING), args.getInt(LENGTH), args.getString(CRITIC), args.getString(ACTOR));
//            tv.setText(query);
        }else{
//            tv.setText("");
        }

    }


}
