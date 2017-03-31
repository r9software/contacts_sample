package com.example.master.mastercontacts;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.master.mastercontacts.dao.AbstractBaseSqliteDao;
import com.example.master.mastercontacts.dao.GroupsSqliteImpl;
import com.example.master.mastercontacts.model.Group;


public class NewGroupActivity extends AppCompatActivity {

    private EditText descriptionEdit;
    private EditText nameEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);
        nameEdit = (EditText) findViewById(R.id.nameEditText);
        descriptionEdit = (EditText) findViewById(R.id.descriptionEditText);
        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(nameEdit.getText().toString())) {
                    Group mGroup = new Group();
                    mGroup.setName(nameEdit.getText().toString());
                    mGroup.setDescription(descriptionEdit.getText().toString());
                    GroupsSqliteImpl dao = new GroupsSqliteImpl();
                    dao.insertGroup(mGroup, new AbstractBaseSqliteDao.DbInsertInterface() {
                        @Override
                        public void onInsert(long id) {
                            Intent intent = NewGroupActivity.this.getIntent();
                            NewGroupActivity.this.setResult(RESULT_OK, intent);
                            finish();
                        }
                    });
                }else {
                    Snackbar.make(findViewById(R.id.saveButton),"Write a name for the group",Snackbar.LENGTH_SHORT).show();
                }
            }
        });

    }
}
