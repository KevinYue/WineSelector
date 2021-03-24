package com.example.kevinyue.wineselector.Element;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.kevinyue.wineselector.R;

public class WinetypesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winetypes);
    }

    public void onClick(View view) {
        // Which button was click
        switch (view.getId()) {
            case R.id.redWine:
                Intent redWineIntent = new Intent(this, RedWineActivity.class);
                startActivity(redWineIntent);
                break;
            case R.id.whiteWine:
                Intent whiteWineIntent = new Intent(this, WhiteWineActivity.class);
                startActivity(whiteWineIntent);
                break;
            case R.id.roseWine:
                Intent roseWineIntent = new Intent(this, RoseWineActivity.class);
                startActivity(roseWineIntent);
                break;
            case R.id.sparklingWine:
                Intent sparklingWineIntent = new Intent(this, SparklingWineActivity.class);
                startActivity(sparklingWineIntent);
                break;
        }
    }
}
