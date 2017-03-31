package com.example.master.mastercontacts.adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.master.mastercontacts.GroupDetailActivity;
import com.example.master.mastercontacts.NewContactActivity;
import com.example.master.mastercontacts.R;
import com.example.master.mastercontacts.model.Contact;

import java.io.Serializable;
import java.util.List;

/**
 * Created by master on 30/03/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ContactViewHolder> {

    private final Context context;

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        private final ImageView callButton;
        private final ImageView mailButton;
        private final ImageView groupButton;
        private final ImageView editButton;
        private CardView cardView;
        private TextView personName;
        private TextView personPhone;
        private ImageView personPhoto;

        ContactViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            personName = (TextView) itemView.findViewById(R.id.nameTextView);
            personPhone = (TextView) itemView.findViewById(R.id.phoneTextView);
            personPhoto = (ImageView) itemView.findViewById(R.id.photoImageView);
            callButton = (ImageView) itemView.findViewById(R.id.callImageView);
            mailButton = (ImageView) itemView.findViewById(R.id.mailImageView);
            groupButton = (ImageView) itemView.findViewById(R.id.groupImageView);
            editButton = (ImageView) itemView.findViewById(R.id.editImageView);
        }
    }

    List<Contact> contactsList;

    public RecyclerViewAdapter(List<Contact> contacts, Context context) {
        this.contactsList = contacts;
        this.context = context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public void updateList(List<Contact> list){
        contactsList = list;
        notifyDataSetChanged();
    }
    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contact_item, viewGroup, false);
        ContactViewHolder contactViewHolder = new ContactViewHolder(v);
        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(final ContactViewHolder personViewHolder, final int i) {
        personViewHolder.personName.setText(contactsList.get(i).getName());
        personViewHolder.personPhone.setText(contactsList.get(i).getPhone1());
        personViewHolder.personPhoto.setImageResource(R.mipmap.selfie);
        personViewHolder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context, NewContactActivity.class);
                mIntent.putExtra(NewContactActivity.CONTACT, (Serializable) contactsList.get(i));
                context.startActivity(mIntent);
            }
        });
        personViewHolder.callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "tel:" + contactsList.get(i).getPhone1().trim();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(uri));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    context.startActivity(intent);
                }else{
                    Snackbar.make(personViewHolder.cardView,"We need your permission to perform this action", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        personViewHolder.mailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent= new Intent(Intent.ACTION_SENDTO);
                String uriText = "mailto:" + Uri.encode(contactsList.get(i).getEmail());
                Uri uri = Uri.parse(uriText);
                mIntent.setData(uri);
                context.startActivity(Intent.createChooser(mIntent, "Send mail..."));
            }
        });
        personViewHolder.groupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context, GroupDetailActivity.class);
                mIntent.putExtra(GroupDetailActivity.GROUP_ID, contactsList.get(i).getGroupId());
                context.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }
}
