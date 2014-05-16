// Eric Garcia
// Full Sail University MDVBS
// Java 1
// May 15, 2014

package com.GarciaEric.userinteraction.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity {

    private static final String LOGTAG = "MainActivity";
    private Context mContext;
    private String[] coursesArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Log message
        Log.d(LOGTAG, "onCreate entered");

        // Set up spinner
        mContext = this;
        coursesArray = getResources().getStringArray(R.array.courses_array);
        setSpinner();

        // Create button listeners
        //setButtons();

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

        // Apply adapter to spinner
        courseSpinner.setAdapter(adapter);

        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast item selected
                Toast.makeText(mContext, "You have selected: " + coursesArray[position], Toast.LENGTH_LONG).show();

                // Temp log
                Log.d(LOGTAG, "An item was selected from the spinner");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    // Get recipies method
    private void getRecipe(String searchTerm){
        // Log message
        Log.d(LOGTAG, "getRecipe entered");
    }

    private void setButtons(){

        // Obtain buttons
        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        Button btnCancel = (Button) findViewById(R.id.btnCancel);

        // Event for search
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log message
                Log.d(LOGTAG, "Search button pressed");
            }
        });

        // Event for cancel
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log message
                Log.d(LOGTAG, "Cancel button pressed");
            }
        });
    }

    // Cancel button action
    public void onCancel(View v){
        // Log message
        Log.d(LOGTAG, "Cancel button clicked");
    }

    // Save button action
    public void onSearch(View v){
        // Log message
        Log.d(LOGTAG, "Save button clicked");
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
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
