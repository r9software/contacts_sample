package com.example.master.mastercontacts.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.master.mastercontacts.R;
import com.example.master.mastercontacts.model.Contact;
import com.example.master.mastercontacts.model.Group;

import java.util.List;

/**
 * Created by master on 30/03/17.
 */

public class ContactsArrayAdapter extends ArrayAdapter<Contact> {


    public ContactsArrayAdapter(@NonNull Context context, @NonNull List<Contact> objects) {
        super(context, 0, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Contact contact = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.group_item, parent, false);
        }
        // Lookup view for data population
        TextView textViewName = (TextView) convertView.findViewById(R.id.nameTextView);
        TextView textViewDescription = (TextView) convertView.findViewById(R.id.descriptionTextView);
        convertView.findViewById(R.id.photoImageView).setVisibility(View.VISIBLE);
        // Populate the data into the template view using the data object
        textViewName.setText(contact.getName());
        if(!TextUtils.isEmpty(contact.getPhone1()))
        textViewDescription.setText(contact.getPhone1());
        // Return the completed view to render on screen
        return convertView;
    }
}
