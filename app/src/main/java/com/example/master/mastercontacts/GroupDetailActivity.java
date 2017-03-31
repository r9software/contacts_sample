package com.example.master.mastercontacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.master.mastercontacts.adapters.ContactsArrayAdapter;
import com.example.master.mastercontacts.dao.GroupsSqliteImpl;
import com.example.master.mastercontacts.model.Contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class GroupDetailActivity extends AppCompatActivity {
    public static final String GROUP_ID="group";
    private List<Contact> contacts= new ArrayList<>();
    private ContactsArrayAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        ListView mList=(ListView)findViewById(R.id.group_list_view);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mIntent = new Intent(GroupDetailActivity.this, NewContactActivity.class);
                mIntent.putExtra(NewContactActivity.CONTACT, (Serializable) contacts.get(position));
                startActivity(mIntent);
            }
        });
        int groupId=getIntent().getIntExtra(GROUP_ID,1);
        mAdapter= new ContactsArrayAdapter(this,contacts);
        updateContacts(groupId);
    }

    private void updateContacts(int groupId) {
        GroupsSqliteImpl dao= new GroupsSqliteImpl();
        contacts.clear();
        contacts.addAll(dao.getContacts(groupId));
        mAdapter.notifyDataSetChanged();
    }
}
