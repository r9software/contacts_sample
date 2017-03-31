package com.example.master.mastercontacts.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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
    private RecyclerViewAdapter recyclerViewAdapter;

    public ContactsFragment() {
        // Required empty public constructor
    }

    private TextWatcher searchTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            filterAdapter(s.toString());
        }
    };

    private void filterAdapter(String s) {
        List<Contact> temp = new ArrayList();
        for (Contact c : contacts) {
            //or use .contains(text)
            if (c.getName().toLowerCase().contains(s.toLowerCase()) ||
                    c.getPhone1().toLowerCase().contains(s.toLowerCase()) ||
                    c.getPhone2().toLowerCase().contains(s.toLowerCase()) ||
                    c.getEmail().toLowerCase().contains(s.toLowerCase())) {
                temp.add(c);
            }
        }
        //update recyclerview
        recyclerViewAdapter.updateList(temp);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parentView = inflater.inflate(R.layout.fragment_contacts, container, false);
        EditText mEdit = (EditText) parentView.findViewById(R.id.searchContactTextView);
        mEdit.addTextChangedListener(searchTextWatcher);
        recyclerView = (RecyclerView) parentView.findViewById(R.id.reciclerView);
        FloatingActionButton addContactButton = (FloatingActionButton) parentView.findViewById(R.id.addNewFloatingAction);
        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getActivity(), NewContactActivity.class);
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
        recyclerViewAdapter = new RecyclerViewAdapter(contacts, getActivity());
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
