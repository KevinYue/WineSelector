package com.example.kevinyue.wineselector.Element.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kevinyue.wineselector.Element.SharedPreference;
import com.example.kevinyue.wineselector.Element.Wine;
import com.example.kevinyue.wineselector.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevinyue on 06.04.16.
 */
public class WineAdapter extends BaseAdapter {
    private final ArrayList mData;
    private Context context;

    public static final String FAVORITES = "Wine_Favorite";

    public ImageView favoriteImage;
    public ImageView whiteWineImage;

    public SharedPreference sharedPreference;

    public WineAdapter(Context context, ArrayList<Wine> list) {
        mData = new ArrayList<>();
        mData.addAll(list);
        this.context = context;
        sharedPreference = new SharedPreference();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Wine getItem(int position) {
        return (Wine) mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // Declare a View variable to use findViewByID() on each row
        final View result;

        //inflate a view to reuse it directly, when convertView accept it
        if(convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.wine_for_food_adapter_item, parent, false);
        }else {
            result = convertView;
        }

        // Get the data item for this position for Wine class
        //final HashMap<String, String> map = getItem(position);
        final Wine wine = getItem(position);


        // Get the Image from the wine_for_food_adapter_item.xml
        //ImageView imageView = (ImageView) result.findViewById(R.id.whiteTest);

        // Set image
        //imageView.setImageResource(R.drawable.white_wine_test);

        // Get (declare) the text and image from the wine_for_food_adapter_item.xml
        favoriteImage = (ImageView) result.findViewById(R.id.favoritesButton);
        whiteWineImage = (ImageView) result.findViewById(R.id.whiteTest);
        TextView type = (TextView) result.findViewById(R.id.textType);
        TextView name = (TextView) result.findViewById(R.id.textName);
        TextView country = (TextView) result.findViewById(R.id.textCountry);
        TextView dice = (TextView) result.findViewById(R.id.textDice);
        TextView description = (TextView) result.findViewById(R.id.textDescription);
        TextView price = (TextView) result.findViewById(R.id.textPrice);

        // Declare the ratingbar
        RatingBar ratingBar = (RatingBar) result.findViewById(R.id.rating);

        // Set the text
        //Log.d("RedWineAdapter", wine+"");
        Log.d("Favorite", wine + "");
        type.setText("Type: " + wine.getType().substring(0, 1).toUpperCase() + wine.getType().substring(1).toLowerCase()); // Set the first letter to uppercase;
        name.setText("Name: " + wine.getName());
        country.setText("Country: " + wine.getCountry());
        dice.setText("" + wine.getDice());
        description.setText("Description: " + wine.getDescription());
        price.setText("Price: " + wine.getPrice());

        if (wine.getName().equals("Zonnebloem Sauvignon Blanc 2004") && wine.getType().equals("white")) {
            whiteWineImage.setImageResource(R.drawable.white_wine_test);
        }

        ratingBar.setRating(wine.getDice());

        LayerDrawable stars = (LayerDrawable) ratingBar
                .getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#FFFF00"), //set the color to Yellow
                PorterDuff.Mode.SRC_ATOP); // for filled stars

        // This Click listener will show a text on which item we are clicked
        /*result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You Clicked, " + typeUppercase + " " + nameUppercase, Toast.LENGTH_LONG).show();
            }
        });*/

        // Test the product is existing in SharedPreferences, set the favorite image and set color
        if(checkFavoritesItem(wine)) {
            favoriteImage.setBackgroundResource(R.drawable.heart_red);
            //favoriteImage.setBackgroundColor(Color.rgb(255, 0, 0));
            favoriteImage.setTag("red");
        } else {
            favoriteImage.setBackgroundResource(R.drawable.heart_grey);
            //favoriteImage.setBackgroundColor(Color.rgb(224, 224, 224));
            favoriteImage.setTag("grey");
        }

        favoriteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreference = new SharedPreference();
                // Getting the tag we have set
                String tag = favoriteImage.getTag().toString();

                if (tag.equalsIgnoreCase("grey")) {
                    sharedPreference.addFavorites(context, wine);
                    Toast.makeText(context,
                            context.getResources().getString(R.string.add_favorites), Toast.LENGTH_SHORT).show();
                    favoriteImage.setTag("red");
                    v.setBackgroundResource(R.drawable.heart_red);
                    //editor.commit();
                } else {
                    sharedPreference.removeFavorites(context, wine);
                    favoriteImage.setTag("grey");
                    v.setBackgroundResource(R.drawable.heart_grey);

                    Toast.makeText(context, context.getResources().getString(R.string.remove_favorites), Toast.LENGTH_SHORT).show();
                    //editor.commit();
                }
            }
        });

        return result;
    }

    // Test the wines will be exists in SharedPreferences
    public boolean checkFavoritesItem(Wine checkWines) {
        boolean check = false;

        List<Wine> favorites = sharedPreference.getFavorites(context);

        if (favorites != null) {
            for (Wine wine : favorites) {
                if(wine != null) {
                    if (wine.equals(checkWines)) {
                        Log.d("Favorite", "Favorite found: " + wine);
                        check = true;
                        break;
                    }
                }
                else
                    Log.d("Favorite", "Favorite not found");
            }
        }
        return check;
    }

    // Add the wine
    public void add(Wine wine) {
        //mData.add(wine);
        wine.add(wine);
        notifyDataSetChanged();
    }

    // Remove the wine
    public void remove(Wine wine) {
        //mData.remove(wine);
        wine.remove(wine);
        notifyDataSetChanged();
    }
}
