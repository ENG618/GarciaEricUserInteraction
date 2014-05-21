// Eric Garcia
// Full Sail University MDVBS
// Java 1
// May 15, 2014

package com.GarciaEric.userinteraction.Data;

import org.json.JSONException;
import org.json.JSONObject;

public class JSON {

    public static JSONObject constructJSON(){



        // Create recipe JSONObject
        JSONObject recipeObject = new JSONObject();

        try {

            // Create query JSONObject
            JSONObject queryObject = new JSONObject();

//            // Create recipe object in query
//            for (Recipes recipes : Recipes.values()) {
//
//                // Create individual recipe object
//                JSONObject recipeObj = new JSONObject();
//
//
//                recipeObj.put("title", recipes.setTitle);
//                recipeObj.put("serving", recipes.setServingSize);
//                recipeObj.put("cookTime", recipes.setCookTime);
//                recipeObj.put("description", recipes.setDescription);
//
//                queryObject.put(recipes.name().toString(), recipeObj);
//
//            }


            recipeObject.put("query", queryObject);


        }catch (JSONException e) {
            e.printStackTrace();
        }


        return recipeObject;
    }
}
