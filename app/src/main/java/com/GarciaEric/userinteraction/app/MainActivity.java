// Eric Garcia
// Full Sail University MDVBS
// Java 1
// May 15, 2014

package com.GarciaEric.userinteraction.app;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.Toast;

/**
 * Created by: Eric Garcia on 5/15/14
 * Full Sail University
 */
public class MainActivity extends Activity {

    // Class fields
    private static final String LOGTAG = "MainActivity";
    private Context mContext;
    private String[] coursesArray;

    // User input Fields
    private EditText searchField;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Log message
        Log.d(LOGTAG, "onCreate entered");

        // Obtain fields
        searchField = (EditText)findViewById(R.id.etSearch);

        // Set up spinner & listView
        mContext = this;
        coursesArray = getResources().getStringArray(R.array.courses_array);
        setSpinner();
        setListView();

        // Check network connection
        if (checkNetworkStatus(mContext)){
            Toast.makeText(mContext, "You have a network connection", Toast.LENGTH_SHORT).show();
        }


    }

    // Check network status
    public Boolean checkNetworkStatus(Context context){
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
                conn = true;
            }
        }


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
                Log.d(LOGTAG, "An item was selected from the spinner");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setListView() {
        // Log message
        Log.d(LOGTAG, "setListView entered");

        // Obtain ListView
        ListView searchResults = (ListView) findViewById(R.id.searchResultsLV);

        // List adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, coursesArray);

        // Specify layout
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        // Set adapter to listView
        searchResults.setAdapter(adapter);

        searchResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO finish on click method
                Toast.makeText(mContext, "You have selected: " + coursesArray[position], Toast.LENGTH_SHORT).show();

            }
        });

    }

    // Get recipes method
    public void getRecipe(String searchTerm){
        // Log message
        Log.d(LOGTAG, "getRecipe entered");
        Log.d(LOGTAG, "Searching for: " + searchTerm);
    }

    // Cancel button method
    public void onCancel(View v){
        // Log message
        Log.d(LOGTAG, "Cancel button clicked");
    }

    // Search button action
    public void onSearch(View v){
        // Log message
        Log.d(LOGTAG, "Search button clicked");

        // Local variable
        String search = searchField.getText().toString();

        // Check search term
        if (search.matches("")) {
            // Toast error message
            Toast.makeText(mContext, "Please enter a search term", Toast.LENGTH_SHORT).show();
            return;
        }else {
            getRecipe(searchField.getText().toString());
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

        switch (item.getItemId()){
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
