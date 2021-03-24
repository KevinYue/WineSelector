package com.example.kevinyue.wineselector.Element;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.kevinyue.wineselector.Element.Adapter.SearchAdapter;
import com.example.kevinyue.wineselector.Element.Database.WineTable;
import com.example.kevinyue.wineselector.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

;

public class SearchWineActivity extends AppCompatActivity {
    public Context context;
    public View view;

    public static final String WINE_DETAIL_KEY = "wine";

    private SearchView searchView;
    private WineTable wineTable;
    private ListView listView;

    private TextView tvType;
    private TextView tvName;
    private TextView tvCountry;
    private TextView tvDice;
    private TextView tvDescription;
    private TextView tvPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_wine);

        this.setTitle("Search Wine List");

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.search_icon);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);

        jsonParser();

        itemWineListenerSelected();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_search_list, menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionsmenu, menu);

        //jsonParser();

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        /* Handle action bar item clicks here. The action bar will
         * automatically handle clicks on the Home/Up button, so long
         * as you specify a parent activity in AndroidManifest.xml.
         * Making available to click on the action bar
         */
        switch (item.getItemId()) {
            // finding activity location menu bar
            case R.id.action_elementactivity:
                Intent elementIntent = new Intent(SearchWineActivity.this, ElementsActivity.class);
                startActivity(elementIntent);
                return true;
            case R.id.action_foodactivity:
                Intent foodIntent = new Intent(SearchWineActivity.this, FoodListActivity.class);
                startActivity(foodIntent);
                return true;
            case R.id.action_redactivity:
                Intent redIntent = new Intent(SearchWineActivity.this, RedWineActivity.class);
                startActivity(redIntent);
                return true;
            case R.id.action_whiteactivity:
                Intent whiteIntent = new Intent(SearchWineActivity.this, WhiteWineActivity.class);
                startActivity(whiteIntent);
                return true;
            case R.id.action_roseactivity:
                Intent roseIntent = new Intent(SearchWineActivity.this, RoseWineActivity.class);
                startActivity(roseIntent);
                return true;
            case R.id.action_sparklingactivity:
                Intent sparklingIntent = new Intent(SearchWineActivity.this, SparklingWineActivity.class);
                startActivity(sparklingIntent);
            case R.id.action_newactivity:
                Intent beginnersIntent = new Intent(SearchWineActivity.this, NewBeginnersActivity.class);
                startActivity(beginnersIntent);
                return true;
            /*case R.id.action_settings:
                Toast.makeText(getApplicationContext(), "Settings option selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.search_id:
                Toast.makeText(getApplicationContext(), "Search option selected", Toast.LENGTH_LONG).show();
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void itemWineListenerSelected() {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Getting values from ListView
                tvType = (TextView) view.findViewById(R.id.textType);
                tvName = (TextView) view.findViewById(R.id.textName);
                tvCountry = (TextView) view.findViewById(R.id.textCountry);
                tvDice = (TextView) view.findViewById(R.id.textDice);
                tvDescription = (TextView) view.findViewById(R.id.textDescription);
                tvPrice = (TextView) view.findViewById(R.id.textPrice);
                Log.d("Wine ", tvType.getText().toString());
                // Creating an instance of Wine class with user input data
                Wine wine = new Wine(tvType.getText().toString(),
                        tvName.getText().toString(),
                        tvCountry.getText().toString(),
                        Integer.parseInt(tvDice.getText().toString()),
                        tvDescription.getText().toString(),
                        tvPrice.getText().toString());

                // Creating an intent to open the WineDetailActivity
                Intent intent = new Intent(getApplicationContext(), WineDetailActivity.class);
                // Passing data as a parcelable object to WineDetailActivity
                intent.putExtra(WINE_DETAIL_KEY, (Parcelable) wine);
                startActivity(intent);
            }
        });
    }

    public String loadJSONAssetFile() {
        String json;

        // Error exception
        try {
            // Open the json file
            InputStream inputStream = getAssets().open("viner.json");

            int size = inputStream.available();

            byte[] buffer = new byte[size];

            // Read the file
            inputStream.read(buffer);

            // Close the file
            inputStream.close();

            // What kind of text the file should read example UTF-8
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            Log.e("log_tag", "Error in JSON file connection" + e.toString());
            return null;
        }
        return json;
    }

    public void jsonParser() {
        final ArrayList<Wine> wineList = new ArrayList<>();

        try {

            JSONObject jsonObject = new JSONObject(loadJSONAssetFile());

            // Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonObject.getJSONArray("wines");

            // Repeat array and print the info in JSONObjects
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                // Storing each json item in variable
                String type = object.getString("type");
                String name = object.getString("name");
                String country = object.getString("country");
                int dice = Integer.parseInt(object.optString("dice"));
                String price = object.getString("price");

                Wine tempWine = new Wine();
                tempWine.setType(type);
                tempWine.setName(name);
                tempWine.setCountry(country);
                tempWine.setDice(dice);
                tempWine.setPrice(price);
                //Log.d("RedWineActivity", tempWine + "");

                wineList.add(tempWine);
            }
        } catch (JSONException e) {
            Log.e("log_tag", "Error to parsing data from JSON file " + e.toString());
        }

        // Identify and get the listview
        listView = (ListView) findViewById(R.id.searchList);

        searchView = (SearchView) findViewById(R.id.searchView);

        //context = this;

        //wineTable = new WineTable(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                final ArrayList<Wine> tempList = new ArrayList<>();

                /**
                 * Go through wineList and find match for keywords
                 * if match with the keywords, add to the tempList
                 */
                try {
                    // open the database connection
                    //wineTable.open();
                    JSONObject jsonObject = new JSONObject(loadJSONAssetFile());

                    // Get the instance of JSONArray that contains JSONObjects
                    JSONArray jsonArray = jsonObject.getJSONArray("wines");
                    //wineTable.deleteAllWines();
                    // Repeat array and print the info in JSONObjects

                    //Log.d("SearchWineActivity", jsonArray.length() + "");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        //Log.d("SearchWineActivity", "in for loop: " + i);

                        JSONObject object = jsonArray.getJSONObject(i);

                        // Take each json item in variable
                        String type = object.getString("type");
                        String name = object.getString("name");
                        String country = object.getString("country");
                        int dice = Integer.parseInt(object.optString("dice"));
                        String description = object.getString("description");
                        String price = object.getString("price");

                        Wine tempWine = new Wine();
                        tempWine.setType(type);
                        tempWine.setName(name);
                        tempWine.setCountry(country);
                        tempWine.setDice(dice);
                        tempWine.setDescription(description);
                        tempWine.setPrice(price);

                        tempList.add(tempWine);


                    }
                } catch (JSONException e) {
                    Log.e("log_tag", "Error to parsing data from JSON file " + e.toString());
                } /*catch (SQLException e) {
                    Log.e("database source", "Error for database connection");
                }*/

                //wineTable.createWinesList(tempList);

                final SearchAdapter searchAdapter = new SearchAdapter(getApplicationContext(), tempList);
                listView.setAdapter(searchAdapter);
                searchAdapter.getFilter().filter(query);

                /*if (wineTable != null) {
                    wineTable.close();
                }*/

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(TextUtils.isEmpty(newText)) {
                    listView.clearTextFilter();
                }
                else {
                    listView.setFilterText(newText);
                }

                return true;
            }
        });
    }

    /*private void displayResults(String query) throws SQLException {
        //listView = (ListView) findViewById(R.id.searchList)
        Cursor cursor = wineTable.searchWines((query != null ? query.toString() : "@@@@"));

        if (cursor != null) {
            // Specify the columns we want to display in the result
            String[] from = new String[]{
                    WineTable.COLUMN_TYPE,
                    WineTable.COLUMN_NAME,
                    WineTable.COLUMN_COUNTRY,
                    WineTable.COLUMN_DICE,
                    WineTable.COLUMN_DESCRIPTION,
                    WineTable.COLUMN_PRICE};

            // Specify the corresponding layout elements where we want the columns to go
            int[] to = new int[]{
                    R.id.textType,
                    R.id.textName,
                    R.id.textCountry,
                    R.id.textDice,
                    R.id.textDescription,
                    R.id.textPrice
            };
            // Crete a simple cursor adapter for the definitions and apply them to the ListView
            SimpleCursorAdapter wines = new SimpleCursorAdapter(this, R.layout.wine_for_food_adapter_item, cursor, from, to);
            listView.setAdapter(wines);
        }
    }*/

}
