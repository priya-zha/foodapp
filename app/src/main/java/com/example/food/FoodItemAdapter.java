package com.example.food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class FoodItemAdapter extends BaseAdapter {
    private final Context context;
    private final List<FoodItem> foodItemList;

    public FoodItemAdapter(Context context, List<FoodItem> foodItemList) {
        this.context = context;
        this.foodItemList = foodItemList;
    }

    @Override
    public int getCount() {
        return foodItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_food_item, null);
//            int marginInPixels =16;
        }
//        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//        layoutParams.
//        view.setLayoutParams(layoutParams);

        FoodItem foodItem = foodItemList.get(position);

        TextView textViewDescription = view.findViewById(R.id.textViewDescription);
        TextView textViewDate = view.findViewById(R.id.textViewDate);
        TextView textViewLocation = view.findViewById(R.id.textViewLocation);
        TextView textViewAvailableUntil = view.findViewById(R.id.textViewAvailableUntil);
        TextView textViewQuantity = view.findViewById(R.id.textViewQuantity);
        TextView textViewAllergen = view.findViewById(R.id.textViewAllergen);

        textViewDescription.setText(foodItem.getDescription());
        textViewDate.setText("Posted Date: "+foodItem.getDate());
        textViewLocation.setText("Location: "+foodItem.getLocation());
        textViewAvailableUntil.setText("Available Until: "+foodItem.getAvailableUntil());
        textViewQuantity.setText("Remaining: "+foodItem.getQuantity());
        textViewAllergen.setText("Allergen: "+foodItem.getAllergen());

        // Set values for other TextViews based on your data model

        return view;
    }
}
