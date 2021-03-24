package com.example.kevinyue.wineselector.Element;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.kevinyue.wineselector.R;

public class WineDetailActivity extends AppCompatActivity {
    private RatingBar ratingBar;
    //private ImageView imageView;
    private TextView textType;
    private TextView textName;
    private TextView textCountry;
    private TextView textDice;
    private TextView textDescription;
    private TextView textPrice;

    // Have some Information from here: https://github.com/codepath/android_guides/wiki/Book-Search-Tutorial
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_detail);

        // Declare the ratingbar
        ratingBar = (RatingBar) findViewById(R.id.rating);

        textType = (TextView) findViewById(R.id.txtType);
        textName = (TextView) findViewById(R.id.txtName);
        textCountry = (TextView) findViewById(R.id.txtCountry);
        textDice = (TextView) findViewById(R.id.txtDice);
        textDescription = (TextView) findViewById(R.id.txtDescription);
        textPrice = (TextView) findViewById(R.id.txtPrice);

        // Fetching data from a parcelable object
        Bundle b = getIntent().getExtras();
        Wine wine = b.getParcelable("wine");

        // Set title
        this.setTitle(wine.getType().substring(6) + " Wine Detail Information");

        textType.setText(wine.getType());
        textName.setText(wine.getName());
        textCountry.setText(wine.getCountry());
        textDice.setText("Dice: " + String.valueOf(wine.getDice()));
        textDescription.setText(wine.getDescription());
        textPrice.setText(wine.getPrice());

        ratingBar.setRating(wine.getDice());

        LayerDrawable stars = (LayerDrawable) ratingBar
                .getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#FFFF00"), //set the color to Yellow
                PorterDuff.Mode.SRC_ATOP); // for filled stars

        //textType.setText(wine.getType());

        //Log.d("WineDetailActivity", wine.toString() + " ");
        //jsonParser(wine);

    }
}
