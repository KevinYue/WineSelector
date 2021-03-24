package com.example.kevinyue.wineselector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.kevinyue.wineselector.Login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    //private ImageView logo;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Showing the image in onCreate
        logo();
    }

    public void startLoginActivity(View view) {
        // Open up a new activity with a intent
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void logo() {

        // Get the logo from the imageView id
        //logo = (ImageView) findViewById(R.id.logo);

        // Get the image from the imageView id
        imageView = (ImageView) findViewById(R.id.image);

        // Setting the size of the image
        android.view.ViewGroup.LayoutParams layoutParams = imageView
                .getLayoutParams();
        layoutParams.width = 300;
        layoutParams.height = 300;
        imageView.setLayoutParams(layoutParams);
    }
}
