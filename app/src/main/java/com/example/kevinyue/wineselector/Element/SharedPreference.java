package com.example.kevinyue.wineselector.Element;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Got inspiration from here: http://androidopentutorials.com/android-how-to-store-list-of-values-in-sharedpreferences/
 */
public class SharedPreference {
    public static final String PREFERENCES_NAME = "WINE_APP";
    public static final String FAVORITES = "Wine_Favorite";

    //List<Wine> favorites;

    public SharedPreference() {
        super();
    }

    // Include the method to favorites, this method to save the favorites
    public void saveFavorites(Context context, List<Wine> favorites) {
        // Used to save favorites in json format (Gson)
        SharedPreferences settings;
        Editor editor;

        Log.d("Favorite", "saving to sharedpreferences");

        settings = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        //editor.clear();

        // Using a google gson with include this, compile 'com.google.code.gson:gson:2.6.2' in build.gradle
        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        //editor.clear();

        // set a preference value, and be commited back
        editor.putString(FAVORITES, jsonFavorites);

        // Change back from this Editor to SharedPreferences object
        editor.commit();
    }

    // Add to favorites
    public void addFavorites(Context context, Wine wine) {
        List<Wine> favorites = getFavorites(context);

        if (favorites == null) {
            favorites = new ArrayList<Wine>();

            Log.d("Favorite", "adding to sharedpreferences: " + wine.getName());
            favorites.add(wine);

            Log.d("Favorite", "last element in list:" + favorites.get(favorites.size() - 1));
            saveFavorites(context, favorites);
        }
    }

    public void removeFavorites(Context context, Wine wine) {
        ArrayList<Wine> favorites = getFavorites(context);

        if (favorites != null) {
            Log.d("Favorite", "removing from sharedpreferences");
            favorites.remove(wine);
            saveFavorites(context, favorites);
        } else {
            Log.d("Favorite", "not removing");
        }
    }

    public ArrayList<Wine> getFavorites(Context context) {
        // Getting favorites from json formatted string
        SharedPreferences settings;
        List<Wine> favorites;

        settings = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {

            Log.d("Favorite", "getting favorites from sharedpreferences");
            String jsonFavorites = settings.getString(FAVORITES, null);

            Gson gson = new Gson();

            Wine[] favoriteItems = gson.fromJson(jsonFavorites, Wine[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList(favorites);
            //favorites.addAll(Arrays.asList(favoriteItems));
        } else {
            Log.d("Favorite", "no favorite in list");
            return null;
        }

        return (ArrayList<Wine>) favorites;
    }
}
