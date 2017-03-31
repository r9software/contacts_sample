package com.example.master.mastercontacts.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.master.mastercontacts.NewContactActivity;
import com.example.master.mastercontacts.R;
import com.example.master.mastercontacts.adapters.RecyclerViewAdapter;
import com.example.master.mastercontacts.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {
    private List<Contact> contacts;
    private RecyclerView recyclerView;

    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parentView = inflater.inflate(R.layout.fragment_contacts, container, false);
        recyclerView = (RecyclerView) parentView.findViewById(R.id.reciclerView);
        FloatingActionButton addContactButton= (FloatingActionButton) parentView.findViewById(R.id.addNewFloatingAction);
        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent= new Intent(getActivity(), NewContactActivity.class);
                startActivity(mIntent);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        initializeData();
        initializeAdapter();
        return parentView;
    }

    private void initializeAdapter() {
        RecyclerViewAdapter recyclerViewAdapter= new RecyclerViewAdapter(contacts,getActivity());
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void initializeData() {
        contacts = new ArrayList<>();
        for (int x = 0; x < 10; x++) {
            Contact contact = new Contact();
            contact.setName("Contact " + x);
            contact.setPhone1("111-222-333-" + x);
            contact.setEmail("correo@correo.com");
            contact.setGroupId(1);
            contacts.add(contact);
        }
    }

}
