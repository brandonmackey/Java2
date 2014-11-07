package com.java1wk4.bmackey.Java2WkOne1411;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by brandonmackey on 11/3/14.
 */
public class NetworkCheck {

    public static final String TAG = "NetworkCheck.TAG";

    // Checks Network Status //
    public static Boolean connectionStatus(Context context) {
        Boolean connect = false;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        String connectionType = "You are not connect to the internet";
        if (ni != null) {
            if (ni.isConnected()) {
                Log.i(TAG, "connection type " + ni.getTypeName());
                connect = true;
                connectionType = ni.getTypeName();
            }
        }
        Log.i(TAG, connectionType);
        return connect;

    }
}
