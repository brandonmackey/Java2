package com.java1wk4.bmackey.connectedapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by brandonmackey on 10/2/14.
 */
public class DisplayFragment extends Fragment {

    public static final String TAG = "DisplayFragment.TAG";

    public static final String  RATING       = "Rating";
    public static final String RUNTIME       = "Runtime";
    public static final String CRITICSRATING = "Critics Rating";
    public static final String NAME          = "Main Actor";

    public static DisplayFragment newInstance(String _rating, Integer _runtime, String _criticsRating, String _name) {
        DisplayFragment displayFrag = new DisplayFragment();

        Bundle bundle = new Bundle();
        bundle.putString(RATING, _rating);
        bundle.putInt(RUNTIME, _runtime);
        bundle.putString(CRITICSRATING, _criticsRating);
        bundle.putString(NAME, _name);

        displayFrag.setArguments(bundle);

        return displayFrag;
    }

    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState) {
        // Create and return view for this fragment.
        View view = _inflater.inflate(R.layout.display_fragment, _container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null){
            DetailView(bundle.getString(RATING), bundle.getInt(RUNTIME), bundle.getString(CRITICSRATING), bundle.getString(NAME));
        }

    }

    public void DetailView(String _rating, Integer _runtime, String _criticsRating, String _name) {
        TextView rating        = (TextView) getView().findViewById(R.id.tvDetailView);
        TextView runtime       = (TextView) getView().findViewById(R.id.tvDetailView2);
        TextView criticsRating = (TextView) getView().findViewById(R.id.tvDetailView6);
        TextView name          = (TextView)getView().findViewById(R.id.tvDetailView8);

        rating.setText(_rating);
        runtime.setText(_runtime);
        criticsRating.setText(_criticsRating);
        name.setText(_name);

    }
}
