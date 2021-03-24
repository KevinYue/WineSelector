package com.example.kevinyue.wineselector.Element;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.kevinyue.wineselector.R;

public class ElementsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elements);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wineTypes:
                Intent typesIntent = new Intent(this, WinetypesActivity.class);
                startActivity(typesIntent);
                break;
            case R.id.food:
                Intent foodIntent = new Intent(this, FoodListActivity.class);
                startActivity(foodIntent);
                break;
            case R.id.beginners:
                Intent beginnersIntent = new Intent(this, NewBeginnersActivity.class);
                startActivity(beginnersIntent);
                break;
            case R.id.favorites:
                Intent favoriteIntent = new Intent(this, FavoriteActivity.class);
                startActivity(favoriteIntent);
                break;
            case R.id.search:
                Intent searchIntent = new Intent(this, SearchWineActivity.class);
                startActivity(searchIntent);
                break;
        }
    }

}
