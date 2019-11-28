package com.example.android.rhymesandstories;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vanshika on 12/5/17.
 *
 * {@link RhymeAdapter} knows how to create a list of {@link Rhyme} objects.
 */
public class RhymeAdapter extends ArrayAdapter<Rhyme> {

    /**
     * Create a new {@link RhymeAdapter} object.
     * @param context is the current context that the adapter is being created in
     * @param words is the list of {@link Rhyme}s to be displayed.
     */
    public RhymeAdapter(Context context, ArrayList<Rhyme> words) {
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Rhyme} object located at this position in the list
        Rhyme currentRhyme = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.rhyme_name);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(currentRhyme.getRhymeName());

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

}
