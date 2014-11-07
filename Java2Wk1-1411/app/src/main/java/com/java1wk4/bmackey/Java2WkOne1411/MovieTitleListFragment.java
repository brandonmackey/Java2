package com.java1wk4.bmackey.Java2WkOne1411;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by brandonmackey on 10/29/14.
 */
public class MovieTitleListFragment extends ListFragment {

    public static final String TAG = "MovieTitleListFragment.TAG";

    private static final String ARG_TEXT = "MovieTitleListFragment.ARG_TEXT";

    public static MovieTitleListFragment newInstance() {
        MovieTitleListFragment frag = new MovieTitleListFragment();

//        Bundle args = new Bundle();
//        args.putString(ARG_TEXT, _text);
//        frag.setArguments(args);

        return frag;
    }

    public MovieTitleListFragment(){
        super();
    }

    @Override
    public void onListItemClick(ListView _l, View _v, int _position, long _id) {

        String movieTitles = (String) _l.getItemAtPosition(_position);

        new AlertDialog.Builder(getActivity())
                .setTitle("Movie Selected")
                .setMessage("You selected " + movieTitles + ".")
                .setPositiveButton("Ok", null)
                .show();
    }

    @Override
    public void onActivityCreated(Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);

        String[] movieTitles = getResources().getStringArray(R.array.android_os_flavours);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, movieTitles);

        setListAdapter(adapter);

    }

//    public interface OnSearchListener {
//        public void searchForSomething(String text);
//    }
//
//    private OnSearchListener mListener;
//
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        if(activity instanceof OnSearchListener) {
//            mListener = (OnSearchListener) activity;
//        } else {
//            throw new IllegalArgumentException("Containing activity must implement OnSearchListener interface");
//        }
//    }

}