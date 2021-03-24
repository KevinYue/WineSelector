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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.kevinyue.wineselector.Element.Wine;
import com.example.kevinyue.wineselector.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by kevinyue on 20/04/2016.
 */
public class SearchAdapter extends BaseAdapter implements Filterable {
    public Context context;

    private List<Wine>originalData = null;
    private List<Wine> filteredData = null;
    private LayoutInflater mInflater;
    private ItemFilter mFilter = new ItemFilter();

    //private WineTable wineTable;

    public SearchAdapter(Context context, List<Wine> data) {
        this.filteredData = data ;
        this.originalData = data ;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return filteredData.size();
    }

    @Override
    public Wine getItem(int position) {
        return filteredData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View result;

        if(convertView == null) {
            result = mInflater.inflate(R.layout.wine_for_food_adapter_item, parent, false);
        }else {
            result = convertView;
        }

        final Wine wine = getItem(position);

        // Get (declare) the text and image from the wine_for_food_adapter_item.xml
        TextView type = (TextView) result.findViewById(R.id.textType);
        TextView name = (TextView) result.findViewById(R.id.textName);
        TextView country = (TextView) result.findViewById(R.id.textCountry);
        TextView dice = (TextView) result.findViewById(R.id.textDice);
        TextView description = (TextView) result.findViewById(R.id.textDescription);
        TextView price = (TextView) result.findViewById(R.id.textPrice);

        RatingBar ratingBar = (RatingBar) result.findViewById(R.id.rating);

        type.setText("Type: " + wine.getType().substring(0, 1).toUpperCase() + wine.getType().substring(1).toLowerCase()); // Set the first letter to uppercase;
        name.setText("Name: " + wine.getName());
        country.setText("Country: " + wine.getCountry());
        dice.setText("" + wine.getDice());
        description.setText("Description: " + wine.getDescription());
        price.setText("Price: " + wine.getPrice());

        //final String typeUppercase = wine.getType().substring(0, 1).toUpperCase() + wine.getType().substring(1).toLowerCase();
        //final String nameUppercase = wine.getName().substring(0, 1).toUpperCase() + wine.getName().substring(1).toLowerCase();

        ratingBar.setRating(wine.getDice());

        LayerDrawable stars = (LayerDrawable) ratingBar
                .getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#FFFF00"), //set the color to Yellow
                PorterDuff.Mode.SRC_ATOP); // for filled stars

        /*result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "You Clicked, " + typeUppercase + " " + nameUppercase, Toast.LENGTH_LONG).show();
            }
        });*/

        return result;
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    public class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<Wine> list = originalData;

            int count = list.size();
            final ArrayList<Wine> nlist = new ArrayList<>(count);

            Wine filterableWine;

            /*wineTable = new WineTable();
            try {
                wineTable.open();

                wineTable.deleteAllWines();*/
                for (int i = 0; i < count; i++) {
                    filterableWine = list.get(i);
                    if (filterableWine.getType().toLowerCase().contains(filterString)) {
                        nlist.add(filterableWine);
                        //Log.d("Search", "Found match:" + filterableWine.getName());
                    }
                    else if(filterableWine.getName().toLowerCase().contains(filterString)) {
                        nlist.add(filterableWine);
                    }
                    else if(filterableWine.getCountry().toLowerCase().contains(filterString)) {
                        nlist.add(filterableWine);
                    }
                    else if(filterableWine.getPrice().toLowerCase().contains(filterString)) {
                        nlist.add(filterableWine);
                    }
                    else {
                        nlist.contains(filterableWine);
                    }
                }
            /*} catch (SQLException e) {
                e.printStackTrace();
            }*/

            Log.d("Search", "searching");

            results.values = nlist;
            results.count = nlist.size();

            /*if (wineTable != null) {
                wineTable.close();
            }

            wineTable.createWinesList(nlist);*/

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<Wine>) results.values;
            notifyDataSetChanged();
        }

    }
}
