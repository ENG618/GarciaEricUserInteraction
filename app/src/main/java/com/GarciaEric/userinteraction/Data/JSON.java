// Eric Garcia
// Full Sail University MDVBS
// Java 1
// May 15, 2014

package com.GarciaEric.userinteraction.Data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSON {

    private static final String LOGTAG = "JSON";

    public static void constructJSON(String JSONString) {
        // Log message
        Log.i(LOGTAG, "constructJSON entered");
        Log.i(LOGTAG, "String: " + JSONString);

        try {
            // Create JSON
            JSONObject data = new JSONObject(JSONString);

            // Create Array of recipes
            JSONArray matches = new JSONArray(data.getJSONArray("matches").toString());

            Log.i(LOGTAG, "matches JSONArray: " + matches.toString());

            // Loop through JSONArray for desired info
            for (int i = 0; i < matches.length(); i++) {
                Log.i(LOGTAG, "In for loop at index: " + i + " Object: " + matches.getJSONObject(i));

                // Create ArrayList for ingredients
                ArrayList<String> ingredients = new ArrayList<String>();

                // Obtain Fields
                String recipeName = matches.getJSONObject(i).getString("recipeName");
                String recipeID = matches.getJSONObject(i).getString("id");
                String sourceDisplayName = matches.getJSONObject(i).getString("sourceDisplayName");
                JSONArray ingredientsJSON = matches.getJSONObject(i).getJSONArray("ingredients");
                int totalTimeInSeconds;
                // Check for null value in totalTimeInSeconds
                if (matches.getJSONObject(i).getString("totalTimeInSeconds") != null) {
                    totalTimeInSeconds = 0;
                } else {
                    totalTimeInSeconds = matches.getJSONObject(i).getInt("totalTimeInSeconds");
                }

                // Loop through ingredientsJSON to add ingredients in ArrayList
                for (int j = 0; j < ingredientsJSON.length(); j++) {
                    String tempIngredient = ingredientsJSON.getString(j);
                    ingredients.add(tempIngredient);
                }

                // Create custom recipe item
                Recipe tempRecipe = new Recipe(recipeName, recipeID, sourceDisplayName, ingredients, totalTimeInSeconds);
                // Add to RecipeDate
                // TODO: add to recipe list
                Log.i(LOGTAG, tempRecipe.toString());
            }
            // Log message completed for loop
            Log.i(LOGTAG, "Completed for loop");
        } catch (JSONException e) {
            Log.e(LOGTAG, "Error: ", e);
            e.printStackTrace();
        }
    }
}
