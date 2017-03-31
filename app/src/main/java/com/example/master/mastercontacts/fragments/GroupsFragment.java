package com.example.master.mastercontacts.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.master.mastercontacts.GroupDetailActivity;
import com.example.master.mastercontacts.NewGroupActivity;
import com.example.master.mastercontacts.R;
import com.example.master.mastercontacts.adapters.GroupsArrayAdapter;
import com.example.master.mastercontacts.dao.GroupsSqliteImpl;
import com.example.master.mastercontacts.model.Contact;
import com.example.master.mastercontacts.model.Group;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupsFragment extends Fragment {


    private static final int ADD_GROUP = 100;
    private List<Group> groups= new ArrayList<>();
    private GroupsArrayAdapter mAdapter;

    public GroupsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_groups, container, false);

        ListView mList=(ListView)view.findViewById(R.id.group_list_view);
        mAdapter= new GroupsArrayAdapter(getContext(),groups);
        view.findViewById(R.id.addNewFloatingAction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getContext(), NewGroupActivity.class);
                startActivityForResult(mIntent,ADD_GROUP);
            }
        });
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mIntent = new Intent(getContext(), GroupDetailActivity.class);
                mIntent.putExtra(GroupDetailActivity.GROUP_ID, groups.get(position).getId());
                startActivity(mIntent);
            }
        });
        mList.setAdapter(mAdapter);
        updateGroups();
        return view;
    }

    private void updateGroups() {
        GroupsSqliteImpl dao= new GroupsSqliteImpl();
        groups.clear();
        groups.addAll(dao.getGroups());
        mAdapter.notifyDataSetChanged();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == ADD_GROUP) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                updateGroups();
            }
        }
    }
}
