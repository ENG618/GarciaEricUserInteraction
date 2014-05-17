// Eric Garcia
// Full Sail University MDVBS
// Java 1
// May 15, 2014

package com.GarciaEric.userinteraction.Data;

public enum Recipes {
    setTitle, setServingSize, setCookTime, setDescription;

    public enum recipes {

        // Recipes
        ROASTBEEF("Simple Roast Beef", 4, 45, "Super tender recipe"),
        CHICKEN("Rusty Chicken", 1, 40, "Grilled chicken done right"),
        CHEESECAKE("Pumpkin Cheesecake", 8, 60, "Pumpkin pie meets cheesecake");

        // Variables
        public String recipeTitle;
        public int recipeServingSize;
        public int recipeCookTime;
        public String recipeDescription;

        // Constructor method
        recipes(String title, int servingSize, int cookTime, String description) {
            this.recipeTitle = title;
            this.recipeServingSize = servingSize;
            this.recipeCookTime = cookTime;
            this.recipeDescription = description;
        }

        // Return title
        public String setTitle(){
            return recipeTitle;
        }

        // Return serving size
        public int setServingSize(){
            return recipeServingSize;
        }

        // Return cook time
        public int setCookTime(){
            return recipeCookTime;
        }

        // Return description
        public String setDescription(){
            return recipeDescription;
        }

    }
}
