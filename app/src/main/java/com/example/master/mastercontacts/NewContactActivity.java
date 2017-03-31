package com.example.master.mastercontacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.master.mastercontacts.model.Contact;

public class NewContactActivity extends AppCompatActivity {

    public static final String CONTACT = "contact";
    public static final String PHONE = "phone";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        findViewById(R.id.showMoreButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMoreViews();
            }
        });
        if (getIntent().getExtras() != null) {
            Contact contact = (Contact) getIntent().getExtras().getSerializable(CONTACT);
            if (contact != null) {
                fillFields(contact);
            }
        }
    }

    private void showMoreViews() {
        findViewById(R.id.NickNameEditText).setVisibility(View.VISIBLE);
        findViewById(R.id.corporationEditText).setVisibility(View.VISIBLE);
        findViewById(R.id.titleEditText).setVisibility(View.VISIBLE);
        findViewById(R.id.phone2EditText).setVisibility(View.VISIBLE);
        findViewById(R.id.phone3EditText).setVisibility(View.VISIBLE);
        findViewById(R.id.type2EditText).setVisibility(View.VISIBLE);
        findViewById(R.id.type3EditText).setVisibility(View.VISIBLE);
        findViewById(R.id.addressEditText).setVisibility(View.VISIBLE);
        findViewById(R.id.werbsiteEditText).setVisibility(View.VISIBLE);
        findViewById(R.id.groupEditText).setVisibility(View.VISIBLE);
        findViewById(R.id.notesEditText).setVisibility(View.VISIBLE);




    }

    private void fillFields(Contact contact) {

    }
}
