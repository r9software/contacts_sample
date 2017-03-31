package com.example.master.mastercontacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.master.mastercontacts.dao.AbstractBaseSqliteDao;
import com.example.master.mastercontacts.dao.ContactsSqliteImpl;
import com.example.master.mastercontacts.dao.GroupsSqliteImpl;
import com.example.master.mastercontacts.model.Contact;
import com.example.master.mastercontacts.model.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewContactActivity extends AppCompatActivity {

    public static final String CONTACT = "contact";
    public static final String PHONE = "phone";
    private EditText nameEdit;
    private EditText lastNameEdit;
    private EditText emailNameEdit;
    private EditText nickNameEdit;
    private EditText corporationEdit;
    private EditText titleEdit;
    private EditText phone1Edit;
    private EditText phone2Edit;
    private Spinner type1Edit;
    private EditText phone3Edit;
    private Spinner type2Edit;
    private Spinner type3Edit;
    private EditText addressEdit;
    private EditText websiteEdit;
    private Spinner groupEdit;
    private EditText notesEdit;
    private boolean useUpdate = false;
    private Contact updateContact;
    private HashMap<String, Integer> groups;
    private boolean showMore;


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
        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contact = getContactFromViews();
                ContactsSqliteImpl dao = new ContactsSqliteImpl();
                if (useUpdate && updateContact != null) {
                    contact.setId(updateContact.getId());
                    dao.updateContact(contact);
                    Intent intent = NewContactActivity.this.getIntent();
                    NewContactActivity.this.setResult(RESULT_OK, intent);
                    finish();
                } else {
                    dao.insertContact(contact, new AbstractBaseSqliteDao.DbInsertInterface() {
                        @Override
                        public void onInsert(long id) {
                            Intent intent = NewContactActivity.this.getIntent();
                            NewContactActivity.this.setResult(RESULT_OK, intent);
                            finish();
                        }
                    });
                }

            }
        });

        initViews();
        if (getIntent().getExtras() != null) {
            updateContact = (Contact) getIntent().getExtras().getSerializable(CONTACT);
            String phone = getIntent().getExtras().getString(PHONE, null);
            if (updateContact != null) {
                useUpdate = true;
                fillFields(updateContact);
            } else if (phone != null) {
                phone1Edit.setText(phone);
            }

        }
    }

    private Contact getContactFromViews() {
        Contact contact = new Contact();
        contact.setName(nameEdit.getText().toString());
        contact.setLastName(lastNameEdit.getText().toString());
        contact.setPhone1(phone1Edit.getText().toString());
        contact.setType1(String.valueOf(type1Edit.getSelectedItemPosition()));
        contact.setEmail(emailNameEdit.getText().toString());
        if (showMore) {
            contact.setNickName(nickNameEdit.getText().toString());
            contact.setCorporation(corporationEdit.getText().toString());
            contact.setTitle(titleEdit.getText().toString());
            contact.setPhone2(phone2Edit.getText().toString());
            contact.setPhone3(phone3Edit.getText().toString());
            contact.setType2((String.valueOf(type2Edit.getSelectedItemPosition())));
            contact.setType3((String.valueOf(type3Edit.getSelectedItemPosition())));
            contact.setAddress(addressEdit.getText().toString());
            contact.setWebsite(websiteEdit.getText().toString());
            contact.setGroupId(groups.get(groupEdit.getSelectedItem().toString()));
            contact.setNotes(notesEdit.getText().toString());
        }
        //contact.setImagePath();
        //contact.setBackgroundImagePath();
        return contact;
    }

    private void initViews() {
        nameEdit = (EditText) findViewById(R.id.nameEditText);
        lastNameEdit = (EditText) findViewById(R.id.lastNameEditText);
        emailNameEdit = (EditText) findViewById(R.id.emailEditText);
        nickNameEdit = (EditText) findViewById(R.id.NickNameEditText);
        corporationEdit = (EditText) findViewById(R.id.corporationEditText);
        titleEdit = (EditText) findViewById(R.id.titleEditText);
        phone1Edit = (EditText) findViewById(R.id.phone1EditText);
        phone2Edit = (EditText) findViewById(R.id.phone2EditText);
        type1Edit = (Spinner) findViewById(R.id.type1EditText);
        phone3Edit = (EditText) findViewById(R.id.phone3EditText);
        type2Edit = (Spinner) findViewById(R.id.type2EditText);
        type3Edit = (Spinner) findViewById(R.id.type3EditText);
        addressEdit = (EditText) findViewById(R.id.addressEditText);
        websiteEdit = (EditText) findViewById(R.id.werbsiteEditText);
        groupEdit = (Spinner) findViewById(R.id.groupEditText);
        notesEdit = (EditText) findViewById(R.id.notesEditText);
    }

    private void showMoreViews() {
        showMore = true;
        GroupsSqliteImpl dao = new GroupsSqliteImpl();
        groups = dao.getHashGroups();
        List<String> groupValues = new ArrayList<>();
        for (Group g : dao.getGroups()) {
            groupValues.add(g.getName());
        }
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, groupValues);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupEdit.setAdapter(adp);
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
        if (contact.getName() != null)
            nameEdit.setText(contact.getName());
        if (contact.getLastName() != null)
            lastNameEdit.setText(contact.getLastName());
        if (contact.getNickName() != null)
            nickNameEdit.setText(contact.getNickName());
        if (contact.getCorporation() != null)
            corporationEdit.setText(contact.getCorporation());
        if (contact.getTitle() != null)
            titleEdit.setText(contact.getTitle());
        if (contact.getPhone1() != null)
            phone1Edit.setText(contact.getPhone1());
        if (contact.getPhone2() != null)
            phone2Edit.setText(contact.getPhone2());
        if (contact.getPhone3() != null)
            phone3Edit.setText(contact.getPhone3());
        if (contact.getType1() != null)
            type1Edit.setSelection(Integer.parseInt(contact.getType1()));
        if (contact.getType2() != null)
            type2Edit.setSelection(Integer.parseInt(contact.getType2()));
        if (contact.getType3() != null)
            type3Edit.setSelection(Integer.parseInt(contact.getType3()));
        if (contact.getEmail() != null)
            emailNameEdit.setText(contact.getEmail());
        if (contact.getAddress() != null)
            addressEdit.setText(contact.getAddress());
        if (contact.getWebsite() != null)
            websiteEdit.setText(contact.getWebsite());
        if (contact.getGroupId() != 0)
            groupEdit.setSelection(contact.getGroupId());
        if (contact.getNotes() != null)
            notesEdit.setText(contact.getNotes());
    }
}
