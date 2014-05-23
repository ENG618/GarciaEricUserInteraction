// Eric Garcia
// Full Sail University MDVBS
// Java 1
// May 15, 2014

package com.GarciaEric.userinteraction.app;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.GarciaEric.userinteraction.Data.JSON;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by: Eric Garcia on 5/15/14
 * Full Sail University
 */
public class MainActivity extends Activity {

    // Class fields
    private static final String LOGTAG = "MainActivity";
    private Context mContext;
    private String[] coursesArray;
    ArrayList<String> recipeList;
    private String selectedCourse;
    public ArrayAdapter<String> aAdapter;
    public static String recipeURL;
    //public static String _urlString = "http://api.yummly.com/v1/api/recipes?_app_id=6191b024&_app_key=6efe529146a8e210cec188d55f877c9f&q=onion+soup&requirePictures=true";

    // User input Fields
    private EditText searchField;
    private ListView resultsLV;
    public TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Log message
        Log.d(LOGTAG, "onCreate entered");

        // Obtain fields
        searchField = (EditText) findViewById(R.id.etSearch);
        //resultsLV = (ListView) findViewById(R.id.listView);
        tv = (TextView) findViewById(R.id.tempTextView);

        // Set up spinner & listView
        mContext = this;
        coursesArray = getResources().getStringArray(R.array.courses_array);
        setSpinner();

//        aAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, recipeList);
//        aAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
//        //resultsLV.setAdapter(aAdapter);
//
//        resultsLV.setVisibility(View.GONE);

    }

    // Check network status
    public Boolean checkNetworkStatus(Context context) {
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
                Toast.makeText(mContext, "Connected to: " + ni.getTypeName(), Toast.LENGTH_SHORT).show();
                conn = true;
                return conn;
            }
        }
        Toast.makeText(mContext, "Please connect to internet to search", Toast.LENGTH_SHORT).show();
        return conn;
    }

    private void setSpinner() {
        // Log message
        Log.d(LOGTAG, "setSpinner entered");

        // Obtain spinner
        Spinner courseSpinner = (Spinner) findViewById(R.id.courseSpinner);

        // Spinner adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, coursesArray);

        // Specify layout
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set adapter to spinner
        courseSpinner.setAdapter(adapter);

        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast item selected
                Toast.makeText(mContext, "You have selected: " + coursesArray[position], Toast.LENGTH_SHORT).show();

                // Temp log
                Log.d(LOGTAG, coursesArray[position] + " was selected from the spinner");

                // Set course string
                selectedCourse = coursesArray[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setListView() {
        // Log message
        Log.d(LOGTAG, "setListView entered");

        // Obtain ListView
        //ListView searchResults = (ListView) findViewById(R.id.listView);

        Log.d(LOGTAG, "Found lv");

        // List adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, recipeList);

        Log.d(LOGTAG, "Array adapter created");

        // Specify layout
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        // Set adapter to listView
        resultsLV.setAdapter(adapter);

        Log.d(LOGTAG, "array adapter set");

        resultsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO finish on click method for listView
                Toast.makeText(mContext, "You have selected: " + recipeList.get(position), Toast.LENGTH_SHORT).show();

            }
        });


    }

    // Convert ArrayList to array, and set list view
    public void convertArray(ArrayList<String> list) {
        // Log message
        Log.i(LOGTAG, "convertArray entered");
////        // Cast ArrayList to array
////        String[] recipesArray = new String[list.size()];
////        for(String s : recipesArray){
////            recipesArray.
////        }
////        recipesArray = (String[]) list.toArray();
////        setListView(recipesArray);
//        recipeList = list;
//        //setListView();
//        //aAdapter.notifyDataSetChanged();
//        resultsLV.setAdapter(aAdapter);
//        resultsLV.setVisibility(View.VISIBLE);
    }

    // Temporary method to show that data is coming in and being parsed
    public void displayJSONReturn(String jsonString) {
        Log.i(LOGTAG, "displayJSONReturn entered.  jsonSting is: " + jsonString);

        //tv.setText(jsonString);
        //Log.i(LOGTAG, "tv obtained");
    }

    // Get recipes method
    public String getRecipeURL(String searchTerm, String course) {
        // Log message
        Log.i(LOGTAG, "getRecipe entered");
        Log.i(LOGTAG, "Searching for: " + searchTerm + " Course: " + course);

        // Format searchTerm for URL
        String newSearchTerm = searchTerm.trim().replace(" ", "+");

        // Construct recipe string
        // Format: http://api.yummly.com/v1/api/recipes?_app_id=app-id&_app_key=app-key&your_search_parameters
        recipeURL = "http://api.yummly.com/v1/api/recipes?_app_id=6191b024&_app_key=6efe529146a8e210cec188d55f877c9f&q=" + newSearchTerm + "&allowedCourse[]=course^course-" + course;

        return recipeURL;
    }

    // Cancel button method
    public void onCancel(View v) {
        // Log message
        Log.d(LOGTAG, "Cancel button clicked");
        // Clear search field
        searchField.setText("");
    }

    // Search button action
    public void onSearch(View v) {
        // Log message
        Log.d(LOGTAG, "Search button clicked");

        // Local variable
        String search = String.valueOf(searchField.getText());

        // Check search term
        if (search.matches("") || search.trim().length() == 0) { // Check if search term is blank
            // Toast error message
            Toast.makeText(mContext, "Please enter a search term", Toast.LENGTH_LONG).show();
        } else { // Sent it to getResponse
            // Check network status
            if (checkNetworkStatus(MainActivity.this)) {
                // Get recipe URL
                String recipeURL = getRecipeURL(String.valueOf(searchField.getText()), selectedCourse);
                Log.i(LOGTAG, "URL is: " + recipeURL);

                //
                getData data = new getData();
                data.execute(recipeURL);
            }
        }

    }

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

    static class getData extends AsyncTask<String, Void, String> {

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

            JSON.constructJSON(s);


            super.onPostExecute(s);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(MainActivity.this, "Setting selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_option1:
                Toast.makeText(MainActivity.this, "Option 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_option2:
                Toast.makeText(MainActivity.this, "Option 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_option3:
                Toast.makeText(MainActivity.this, "Option 3 selected", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
