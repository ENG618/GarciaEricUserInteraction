package com.GarciaEric.userinteraction.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Eric Garcia on 5/20/14
 * Full Sail University
 */
public class RecipeData {

    // Create List of recipes
    private ArrayList<Recipe> Recipes = new ArrayList<Recipe>();

    // Returns list of recipes
    public List<Recipe> getRecepes() {
        return Recipes;
    }

    // Add new recipe
    public void addItem(Recipe item) {
        Recipes.add(item);
    }
}
