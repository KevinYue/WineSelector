package com.example.kevinyue.wineselector.Element;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kevinyue.wineselector.Element.Adapter.WineAdapter;
import com.example.kevinyue.wineselector.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    public SharedPreference sharedPreference;
    public List<Wine> favorites;
    public FavoriteActivity activity;
    public WineAdapter wineAdapter;

    private ListView favoriteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        this.setTitle("My Favorite Wine List");

        favoriteContent();
    }

    private void favoriteContent() {
        // Get favorite items from SharedPreferences.
        sharedPreference = new SharedPreference();
        favorites = sharedPreference.getFavorites(FavoriteActivity.this);

        if (favorites == null) {
            Log.d("Favorite", "list is null");
            Toast.makeText(FavoriteActivity.this, getString(R.string.no_favorites), Toast.LENGTH_SHORT).show();
            /*showAlert(getResources().getString(R.string.no_favorites), getResources().getString(R.string.long_favorites_press));*/
        } else {
            if (favorites.size() == 0) {
                Log.d("Favorite", "list is empty");
                Toast.makeText(FavoriteActivity.this, getString(R.string.no_favorites), Toast.LENGTH_SHORT).show();
                /*showAlert(getResources().getString(R.string.no_favorites),
                        getResources().getString(R.string.long_favorites_press));*/
            }

            favoriteList = (ListView) findViewById(R.id.favoriteList);

            if (favorites != null) {
                //Log.d("Favorite", "list is not empty:" + favorites.get(0).getName());
                wineAdapter = new WineAdapter(FavoriteActivity.this, (ArrayList<Wine>) favorites);
                favoriteList.setAdapter(wineAdapter);

                favoriteList.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // The SharedPreferences favorite items to be active here
                        //sharedPreference = new SharedPreference();

                        final Wine wine = (Wine) parent.getItemAtPosition(position);
                        Toast.makeText(FavoriteActivity.this, wine.toString(), Toast.LENGTH_SHORT).show();
                        favorites = sharedPreference.getFavorites(FavoriteActivity.this);

                        if (favorites != null) {
                            if (favorites.size() == 0) {
                                showAlert(getResources().getString(R.string.no_favorites),
                                        getResources().getString(R.string.long_favorites_press));
                            }

                            favoriteList = (ListView) view.findViewById(R.id.favoriteList);

                            if (favorites != null) {
                                wineAdapter = new WineAdapter(FavoriteActivity.this, (ArrayList<Wine>) favorites);
                                favoriteList.setAdapter(wineAdapter);

                                favoriteList.setOnItemLongClickListener(new OnItemLongClickListener() {
                                    @Override
                                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                        ImageView favoriteButton = (ImageView) view.findViewById(R.id.favoritesButton);

                                        String tag = favoriteButton.getTag().toString();

                                        if (tag.equalsIgnoreCase("grey")) {
                                            sharedPreference.addFavorites(FavoriteActivity.this, favorites.get(position));
                                            Toast.makeText(FavoriteActivity.this, getString(R.string.add_favorites), Toast.LENGTH_SHORT).show();

                                            favoriteButton.setTag("red");
                                            favoriteButton.setImageResource(R.drawable.heart_red);
                                        } else {
                                            sharedPreference.removeFavorites(FavoriteActivity.this, favorites.get(position));

                                            favoriteButton.setTag("grey");
                                            favoriteButton.setImageResource(R.drawable.heart_grey);

                                            wineAdapter.remove(favorites.get(position));

                                            Toast.makeText(FavoriteActivity.this, getString(R.string.remove_favorites), Toast.LENGTH_SHORT).show();
                                        }

                                        return true;
                                    }
                                });
                            }
                        } else {
                            showAlert(getResources().getString(R.string.no_favorites),
                                    getResources().getString(R.string.long_favorites_press));
                        }
                    }
                });
                /*favoriteList.setOnItemLongClickListener(new OnItemLongClickListener() {

                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        ImageView favoriteButton = (ImageView) view.findViewById(R.id.favoritesButton);

                        String tag = favoriteButton.getTag().toString();

                        if (tag.equalsIgnoreCase("grey")) {
                            sharedPreference.addFavorites(FavoriteActivity.this, favorites.get(position));
                            Toast.makeText(FavoriteActivity.this,
                                    getString(R.string.add_favorites), Toast.LENGTH_SHORT).show();

                            favoriteButton.setTag("red");
                            favoriteButton.setImageResource(R.drawable.heart_red);
                        } else {
                            sharedPreference.removeFavorites(FavoriteActivity.this, favorites.get(position));

                            favoriteButton.setTag("grey");
                            favoriteButton.setImageResource(R.drawable.heart_grey);

                            wineAdapter.remove(favorites.get(position));

                            Toast.makeText(FavoriteActivity.this, getString(R.string.remove_favorites), Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });*/
            }
        }
    }

    private void showAlert(String title, String message) {
        // If activity have content and not finished we make a dialog
        if(FavoriteActivity.this != null && !FavoriteActivity.this.isFinishing()) {
            AlertDialog alertDialog = new AlertDialog.Builder(FavoriteActivity.this).create();

            alertDialog.setTitle(title); // Dialog set title
            alertDialog.setMessage(message); // Dialog set Message
            alertDialog.setCancelable(false); // to be cancel

            // Setting OK Button
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialogInterface, int which) {
                    // Miss the dialog
                    dialogInterface.dismiss();
                    //activity.finish();
                    getFragmentManager().popBackStackImmediate();

                }
            });
            alertDialog.show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
