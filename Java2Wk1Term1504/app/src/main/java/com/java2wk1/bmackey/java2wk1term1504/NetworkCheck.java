package com.java2wk1.bmackey.java2wk1term1504;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by brandonmackey on 4/11/15.
 */
public class NetworkCheck {

    Context mContext;
    public NetworkCheck(Context mContext){
        this.mContext = mContext;
    }



    public Boolean connectionStatus() {
        Boolean connect = false;
        ConnectivityManager cm = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isConnectedOrConnecting()) {
            connect = true;
        }
        return connect;
    }
}
