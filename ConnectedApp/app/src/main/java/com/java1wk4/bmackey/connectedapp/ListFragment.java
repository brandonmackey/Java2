package com.java1wk4.bmackey.connectedapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by brandonmackey on 10/6/14.
 */
public class ListFragment extends Fragment{

    public static final String TAG = "ListFragment.TAG";

    ArrayAdapter<String> spinAdapter, listAdapter;
    ListView myListView;
    ArrayList<String> mMovies;

    public static ListFragment newInstance() {
        ListFragment listFrag = new ListFragment();
        return listFrag;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        //String title = mTitle.get(position).getTitle();
    }

    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState) {
        // Create and return view for this fragment.
        View view = _inflater.inflate(R.layout.list_fragment, _container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);

        View view =getView();

       assert view !=null;
        myListView = (ListView) getView().findViewById(R.id.list_fragment);

    }

    public interface OnSearchListener {
    }
}
