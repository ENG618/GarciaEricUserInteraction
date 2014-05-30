// Eric Garcia
// Full Sail University MDVBS
// Java 1
// May 15, 2014

package com.GarciaEric.userinteraction.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

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
    private String selectedCourse;
    private static String recipeURL;

    // User input Fields
    private EditText searchField;
    private ListView resultsLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Log message
        Log.d(LOGTAG, "onCreate entered");

        // Obtain fields
        searchField = (EditText) findViewById(R.id.etSearch);
        resultsLV = (ListView) findViewById(R.id.listView);

        // Set background transparency
        View layout = findViewById(R.id.layout);
        Drawable background = layout.getBackground();
        //noinspection ConstantConditions
        background.setAlpha(120);

        // Set up spinner & listView
        mContext = this;
        coursesArray = getResources().getStringArray(R.array.courses_array);
        setSpinner();

        // Hide listView
        resultsLV.setVisibility(View.GONE);

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

    void setListView(ArrayList<String> r) {
        // Log message
        Log.d(LOGTAG, "setListView entered");

        // Obtain ListView
        //ListView searchResults = (ListView) findViewById(R.id.listView);

        Log.d(LOGTAG, "Found lv");

        Log.i(LOGTAG, "r value: " + r);

        if (r.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please try another search, nothing was found", Toast.LENGTH_LONG);
        }

        // List adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, r /*recipeList*/);

        Log.d(LOGTAG, "Array adapter created");

        // Specify layout
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        // Set adapter to listView
        resultsLV.setAdapter(adapter);

        Log.d(LOGTAG, "array adapter set");

        resultsLV.setVisibility(View.VISIBLE);

        /*resultsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO finish on click method for listView
                Toast.makeText(mContext, "You have selected: " + r.get(position) *//*recipeList.get(position)*//*, Toast.LENGTH_SHORT).show();

            }
        });*/


    }

    // Get recipes method
    String getRecipeURL(String searchTerm, String course) {
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
    public void onClear(View v) {
        // Log message
        Log.d(LOGTAG, "Cancel button clicked");
        // Clear search field
        searchField.setText("");
    }

    // Search button action
    public void onSearch(View v) {
        // Log message
        Log.d(LOGTAG, "Search button clicked");

        // Hide keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //noinspection ConstantConditions
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        // Create instance of NetworkCheck.jar
        com.GarciaEric.networkcheck.NetworkCheck check = new com.GarciaEric.networkcheck.NetworkCheck();

        resultsLV.setVisibility(View.GONE);

        // Local variable
        String search = String.valueOf(searchField.getText());

        // Check search term
        if (search.matches("") || search.trim().length() == 0) { // Check if search term is blank
            // Toast error message
            Toast.makeText(mContext, "Please enter a search term", Toast.LENGTH_LONG).show();
        } else { // Send it to getResponse
            // Check network status using NetworkCheck.jar
            if (check.check(MainActivity.this)) {
                // Get recipe URL
                String recipeURL = getRecipeURL(String.valueOf(searchField.getText()), selectedCourse);
                Log.i(LOGTAG, "URL is: " + recipeURL);


                getData data = new getData();
                data.execute(recipeURL);
            }
        }

    }

    // Fetch URL
    private static String getResponse(URL url) {
        // Log message
        Log.i(LOGTAG, "getResponse entered");

        String response;
        try {
            //noinspection UnusedAssignment
            response = null;
            URLConnection conn = url.openConnection();
            BufferedInputStream bin = new BufferedInputStream(conn.getInputStream());
            byte[] contextByte = new byte[1024];
            //noinspection UnusedAssignment
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

    private class getData extends AsyncTask<String, Void, String> {

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

            // Create instance of ParseJSON.jar
            com.GarciaEric.networkcheck.ParseJSON parseJSON = new com.GarciaEric.networkcheck.ParseJSON();

            // Set listView with return from ParseJSON.jar
            setListView(parseJSON.getRecipesJSON(s));

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
