// Eric Garcia
// Full Sail University MDVBS
// Java 1
// May 15, 2014

package com.GarciaEric.userinteraction.Data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSON {

    private static final String LOGTAG = "JSON";

    public static void constructJSON(String JSONString) {
        // Log message
        Log.i(LOGTAG, "constructJSON entered");

        try {
            // Create JSON
            JSONArray data = new JSONArray(JSONString);

            //JSONObject matches = data.getJSONObject("matches");

            Log.i(LOGTAG, "Before for loop");
            // Loop through json for desired info
            for (int i = 0; i < data.length(); i++) {
                Log.i(LOGTAG, "In for loop");
                JSONObject matches = data.getJSONObject(i).getJSONObject("matches");

                Log.i(LOGTAG, "matches: " + matches.toString());


            }
        } catch (JSONException e) {
            Log.e(LOGTAG, "Error: ", e);
            e.printStackTrace();

        }
    }
}
