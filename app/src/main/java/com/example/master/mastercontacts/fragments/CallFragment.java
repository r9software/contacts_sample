package com.example.master.mastercontacts.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.master.mastercontacts.NewContactActivity;
import com.example.master.mastercontacts.R;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 */
public class CallFragment extends Fragment implements View.OnClickListener {


    private TextView phoneTextView;
    private String mPhoneString="";

    public CallFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_call, container, false);
        phoneTextView=(TextView) view.findViewById(R.id.phoneTextView);
        view.findViewById(R.id.one).setOnClickListener(this);
        view.findViewById(R.id.two).setOnClickListener(this);
        view.findViewById(R.id.three).setOnClickListener(this);
        view.findViewById(R.id.four).setOnClickListener(this);
        view.findViewById(R.id.five).setOnClickListener(this);
        view.findViewById(R.id.six).setOnClickListener(this);
        view.findViewById(R.id.seven).setOnClickListener(this);
        view.findViewById(R.id.eight).setOnClickListener(this);
        view.findViewById(R.id.nine).setOnClickListener(this);
        view.findViewById(R.id.zero).setOnClickListener(this);
        view.findViewById(R.id.asterisk).setOnClickListener(this);
        view.findViewById(R.id.hashtag).setOnClickListener(this);
        view.findViewById(R.id.call).setOnClickListener(this);
        view.findViewById(R.id.addContactView).setOnClickListener(this);
        view.findViewById(R.id.deleteImageView).setOnClickListener(this);
        view.findViewById(R.id.deleteImageView).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                phoneTextView.setText("");
                mPhoneString="";
                return true;
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.one:
            case R.id.two:
            case R.id.three:
            case R.id.four:
            case R.id.five:
            case R.id.six:
            case R.id.seven:
            case R.id.eight:
            case R.id.nine:
            case R.id.zero:
            case R.id.asterisk:
            case R.id.hashtag: {
                mPhoneString+=((TextView) v).getText();
                phoneTextView.setText(mPhoneString);
                break;
            }
            case R.id.deleteImageView:{
                mPhoneString=removeLastChar(mPhoneString);
                phoneTextView.setText(mPhoneString);
                break;
            }
            case R.id.call:{
                if(TextUtils.isEmpty(mPhoneString))
                    return;
                String uri = "tel:" + mPhoneString.trim();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(uri));
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                   startActivity(intent);
                }else{
                    Snackbar.make(v,"We need your permission to perform this action", Snackbar.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.addContactView:{
                Intent mIntent = new Intent(getActivity(), NewContactActivity.class);
                if(!TextUtils.isEmpty(mPhoneString))
                    mIntent.putExtra(NewContactActivity.PHONE,mPhoneString);
                startActivity(mIntent);
                break;
            }
        }
    }
    public String removeLastChar(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return s.substring(0, s.length()-1);
    }

}
