package com.java2wk1.bmackey.java2wk1term1504;

import org.json.JSONObject;

/**
 * Created by brandonmackey on 4/10/15.
 */
public interface FirstFragmentListener {

    public void entryClickListener(JSONObject data);

    public void mySearch(String myMovie);

    void entryClickListener(String query);
}
