package com.example.master.mastercontacts.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.master.mastercontacts.R;
import com.example.master.mastercontacts.model.Contact;

import java.util.List;

/**
 * Created by master on 30/03/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ContactViewHolder> {

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView personName;
        TextView personPhone;
        ImageView personPhoto;

        ContactViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cardView);
            personName = (TextView)itemView.findViewById(R.id.nameTextView);
            personPhone = (TextView)itemView.findViewById(R.id.phoneTextView);
            personPhoto = (ImageView)itemView.findViewById(R.id.photoImageView);
        }
    }

    List<Contact> contactsList;

    public RecyclerViewAdapter(List<Contact> contacts){
        this.contactsList = contacts;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contact_item, viewGroup, false);
        ContactViewHolder contactViewHolder = new ContactViewHolder(v);
        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(contactsList.get(i).getName());
        personViewHolder.personPhone.setText(contactsList.get(i).getPhone1());
        personViewHolder.personPhoto.setImageResource(R.mipmap.selfie);
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }
}
