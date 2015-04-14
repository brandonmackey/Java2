package com.java2wk1.bmackey.java2wk1term1504;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brandonmackey on 4/10/15.
 */
public class MasterFrag extends Fragment implements View.OnClickListener {

    FirstFragmentListener listener;
    public String selectText;
    String query;
    //searchBar mSearch;
    searchData mData;
    ArrayAdapter<String> spinAdapter, listAdapter;
    public ListView mListView;
    ArrayList<String> myArrayList = new ArrayList<String>();
    List<MyJsonData> mList;
    boolean connectionStatus;
    private entryClickListener mListener;

    @Override
    public void onClick(View v) {

    }


//    public interface searchBar{
//        public void mySearch(String movie);
//    }

    public interface searchData{
        public void movieDetails(String _title, String _rating, Integer _length, String _critic, String _actor);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof FirstFragmentListener){
            listener = (FirstFragmentListener) activity;
//            mSearch = (searchBar) activity;


        }else {
            throw new IllegalArgumentException("Please Enter A Movie Title!");
        }
        if (activity instanceof searchData){

            mData = (searchData) activity;

        }else {

            throw new IllegalArgumentException("NO Data!");
        }
    }

    public void onListItemClick(ListView _l, View _v, int _position, long _id) {


        MyJsonData myJsonData = (MyJsonData) _l.getItemAtPosition(_position);


         mData.movieDetails(myJsonData.getTitle(), myJsonData.getRating(), myJsonData.getRuntime(), myJsonData.getCriticsRating(), myJsonData.getName());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_first, container, false);


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        // Create ListView
        mListView = (ListView) view.findViewById(R.id.myListView);

        getActivity().findViewById(R.id.searchButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText et = (EditText) getActivity().findViewById(R.id.firstEdit);

                query = et.getText().toString();
                query = query.replace(" ", "+");

                InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(et.getWindowToken(), 0);

                listener.entryClickListener(query);
            }
        });

        getFiles();
        addListener();

    }


//    @Override
//    public void onClick(View v) {
//        EditText et = (EditText) getView().findViewById(R.id.firstEdit);
//        String etStr = et.getText().toString();
//        mListener.listItemPressed(etStr);
//
//    }

    public interface entryClickListener{

       // public void listItemPressed(String myMovieTitle);

    }



    public void getFiles(){
        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        String[] titleNames = getActivity().getApplicationContext().fileList();
        List<MyJsonData> list = new ArrayList<MyJsonData>();
        JSONObject myData = null;
        MyJsonData movies = new MyJsonData(myData);

        for (int i = 0; i < titleNames.length; i++){
            Log.d("titleName", titleNames[i]);
            movies.setTitle(titleNames[i]);

            list.add(movies);
            String title = list.get(i).getTitle();
            adapter.add(title);

        }
        mListView.setAdapter(adapter);
    }

    public void addListener(){
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectText = (String) parent.getItemAtPosition(position);
                //if ( Run NetworkCheck )
                String myUrl = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?apikey=6cvyrv4n86brwgx7s528kzru";


            }
        });
    }


}
