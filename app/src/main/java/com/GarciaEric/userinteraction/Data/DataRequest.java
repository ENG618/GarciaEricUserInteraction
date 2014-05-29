package com.GarciaEric.userinteraction.Data;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by: Eric Garcia on May 29, 2014
 * Full Sail University | MDVBS
 * Project: User Interaction
 */

public class DataRequest {

    private static final String LOGTAG = "GetData";
    // Class Fields
    public static String recipeURL;


    // Fetch URL
    public static String getResponse(URL url) {
        // Log message
        Log.i(LOGTAG, "getResponse entered");

        String response;
        try {
            response = null;
            URLConnection conn = url.openConnection();
            BufferedInputStream bin = new BufferedInputStream(conn.getInputStream());
            byte[] contextByte = new byte[1024];
            int byteRead = 0;
            //StringBuffer was producing an error so I switched it to String Builder
            StringBuilder responseBuilder = new StringBuilder();


            while ((byteRead = bin.read(contextByte)) != -1) {
                response = new String(contextByte, 0, byteRead);
                responseBuilder.append(response);
            }
            response = responseBuilder.toString();
            Log.i(LOGTAG, "URL Response: " + response);
        } catch (IOException e) {
            response = "Something isn't right.  We didn't receive a response";
            Log.e(LOGTAG, "Something went wrong", e);
        }

        return response;
    }

    class getData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            // Declare local variable response string
            String responseString;

            try { // Try URL
                URL url = new URL(recipeURL);
                responseString = getResponse(url);
            } catch (Exception e) { // If error show response string and error
                responseString = "Something isn't right";
                Log.e(LOGTAG, "ERROR: ", e);
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.i(LOGTAG, "onPostExecute entered");
            Log.i(LOGTAG, "Post Execute String: " + s);

            // Return ArrayList<String>
            ParseJSON parser = new ParseJSON();
            //setListView(parser.getRecipesJSON(s));


            super.onPostExecute(s);
        }
    }

    public ArrayList<String> getParsedJSON(String urlString) {
        // Log message
        Log.i(LOGTAG, "getParsedJSON entered");

        getData data = new getData();
        data.execute(urlString);

        return null;
    }

}
