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
import com.example.master.mastercontacts.R;
import com.example.master.mastercontacts.adapters.GroupsArrayAdapter;
import com.example.master.mastercontacts.model.Contact;
import com.example.master.mastercontacts.model.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupsFragment extends Fragment {


    private ArrayList<Group> groups;

    public GroupsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_groups, container, false);
        ListView mList=(ListView)view.findViewById(R.id.group_list_view);
        groups = new ArrayList<>();
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mIntent = new Intent(getContext(), GroupDetailActivity.class);
                mIntent.putExtra(GroupDetailActivity.GROUP_ID, groups.get(position).getId());
                startActivity(mIntent);
            }
        });

        for (int x = 0; x < 10; x++) {
            Group group = new Group();
            group.setId(x);
            group.setName("Group " + x);
            group.setDescription("111-222-333-" + x);
            groups.add(group);
        }
        ArrayAdapter<Group> mAdapter= new GroupsArrayAdapter(getContext(),groups);
        mList.setAdapter(mAdapter);
        return view;
    }

}
