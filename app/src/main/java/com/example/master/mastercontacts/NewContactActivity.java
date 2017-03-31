package com.example.master.mastercontacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.master.mastercontacts.model.Contact;

public class NewContactActivity extends AppCompatActivity {

    public static final String CONTACT = "contact";
    public static final String PHONE = "phone";
    private TextView mtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        mtext = (TextView) findViewById(R.id.nameTextView);
        if (getIntent().getExtras() != null) {
            Contact contact = (Contact) getIntent().getExtras().getSerializable(CONTACT);
            if (contact != null) {
                fillFields(contact);
            }
        }
    }

    private void fillFields(Contact contact) {
        mtext.setText(contact.getName());
    }
}
