package com.example.master.mastercontacts.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.master.mastercontacts.R;
import com.example.master.mastercontacts.model.Group;

import java.util.List;

/**
 * Created by master on 30/03/17.
 */

public class GroupsArrayAdapter extends ArrayAdapter<Group> {


    public GroupsArrayAdapter(@NonNull Context context, @NonNull List<Group> objects) {
        super(context, 0, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Group group = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.group_item, parent, false);
        }
        // Lookup view for data population
        TextView textViewName = (TextView) convertView.findViewById(R.id.nameTextView);
        TextView textViewDescription = (TextView) convertView.findViewById(R.id.descriptionTextView);
        // Populate the data into the template view using the data object
        textViewName.setText(group.getName());
        if(!TextUtils.isEmpty(group.getDescription()))
        textViewDescription.setText(group.getDescription());
        // Return the completed view to render on screen
        return convertView;
    }
}
