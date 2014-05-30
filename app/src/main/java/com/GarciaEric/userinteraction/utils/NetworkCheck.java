package com.GarciaEric.userinteraction.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by: Eric Garcia on May 29, 2014
 * Full Sail University | MDVBS
 * Project: User Interaction
 */
public class NetworkCheck {

    private static final String LOGTAG = "NetworkCheck";

    public Boolean check(Context context) {
        // Log message
        Log.d(LOGTAG, "checkNetworkStatus entered");

        // Declare connection as false until checked
        Boolean conn = false;

        // Create connectivity manager
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // Obtain status
        NetworkInfo ni = cm.getActiveNetworkInfo();
        // Check validity
        if (ni != null) {
            if (ni.isConnected()) {
                Log.d(LOGTAG, "Connection type: " + ni.getTypeName());
                Toast.makeText(context, "Connected to: " + ni.getTypeName(), Toast.LENGTH_SHORT).show();
                conn = true;
                return conn;
            }
        }
        Toast.makeText(context, "Please connect to internet to search", Toast.LENGTH_SHORT).show();
        return conn;
    }
}
