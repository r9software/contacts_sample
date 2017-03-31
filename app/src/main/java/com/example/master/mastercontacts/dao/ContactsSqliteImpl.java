package com.example.master.mastercontacts.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.master.mastercontacts.model.Contact;
import com.example.master.mastercontacts.model.Group;

import java.util.ArrayList;
import java.util.List;

import static com.example.master.mastercontacts.dao.DataBaseOpenHelper.COLUMN_ADDRESS;
import static com.example.master.mastercontacts.dao.DataBaseOpenHelper.COLUMN_BACKGROUND_IMAGE;
import static com.example.master.mastercontacts.dao.DataBaseOpenHelper.COLUMN_CORPORATION;
import static com.example.master.mastercontacts.dao.DataBaseOpenHelper.COLUMN_DESCRIPTION;
import static com.example.master.mastercontacts.dao.DataBaseOpenHelper.COLUMN_EMAIL;
import static com.example.master.mastercontacts.dao.DataBaseOpenHelper.COLUMN_GROUP_ID;
import static com.example.master.mastercontacts.dao.DataBaseOpenHelper.COLUMN_ID;
import static com.example.master.mastercontacts.dao.DataBaseOpenHelper.COLUMN_IMAGE;
import static com.example.master.mastercontacts.dao.DataBaseOpenHelper.COLUMN_LAST_NAME;
import static com.example.master.mastercontacts.dao.DataBaseOpenHelper.COLUMN_NAME;
import static com.example.master.mastercontacts.dao.DataBaseOpenHelper.COLUMN_NICK_NAME;
import static com.example.master.mastercontacts.dao.DataBaseOpenHelper.COLUMN_NOTES;
import static com.example.master.mastercontacts.dao.DataBaseOpenHelper.COLUMN_PHONE1;
import static com.example.master.mastercontacts.dao.DataBaseOpenHelper.COLUMN_PHONE2;
import static com.example.master.mastercontacts.dao.DataBaseOpenHelper.COLUMN_PHONE3;
import static com.example.master.mastercontacts.dao.DataBaseOpenHelper.COLUMN_TITLE;
import static com.example.master.mastercontacts.dao.DataBaseOpenHelper.COLUMN_TYPE1;
import static com.example.master.mastercontacts.dao.DataBaseOpenHelper.COLUMN_TYPE2;
import static com.example.master.mastercontacts.dao.DataBaseOpenHelper.COLUMN_TYPE3;
import static com.example.master.mastercontacts.dao.DataBaseOpenHelper.COLUMN_WEBSITE;
import static com.example.master.mastercontacts.dao.DataBaseOpenHelper.TABLE_CONTACTS;

/**
 * Created by master on 30/03/17.
 */

public class ContactsSqliteImpl extends AbstractBaseSqliteDao {
    public void insertContact(Contact contact, DbInsertInterface dbInsert){
        ContentValues cont= new ContentValues();
        cont.put(DataBaseOpenHelper.COLUMN_NAME,contact.getName());
        cont.put(DataBaseOpenHelper.COLUMN_LAST_NAME,contact.getLastName());
        cont.put(DataBaseOpenHelper.COLUMN_NICK_NAME,contact.getNickName());
        cont.put(DataBaseOpenHelper.COLUMN_CORPORATION,contact.getCorporation());
        cont.put(DataBaseOpenHelper.COLUMN_TITLE,contact.getTitle());
        cont.put(DataBaseOpenHelper.COLUMN_PHONE1,contact.getPhone1());
        cont.put(DataBaseOpenHelper.COLUMN_PHONE2,contact.getPhone2());
        cont.put(DataBaseOpenHelper.COLUMN_PHONE3,contact.getPhone3());
        cont.put(DataBaseOpenHelper.COLUMN_TYPE1,contact.getType1());
        cont.put(DataBaseOpenHelper.COLUMN_TYPE2,contact.getType2());
        cont.put(DataBaseOpenHelper.COLUMN_TYPE3,contact.getType3());
        cont.put(DataBaseOpenHelper.COLUMN_EMAIL,contact.getEmail());
        cont.put(DataBaseOpenHelper.COLUMN_ADDRESS,contact.getAddress());
        cont.put(DataBaseOpenHelper.COLUMN_WEBSITE,contact.getWebsite());
        cont.put(DataBaseOpenHelper.COLUMN_GROUP_ID,contact.getGroupId());
        cont.put(DataBaseOpenHelper.COLUMN_NOTES,contact.getNotes());
        cont.put(DataBaseOpenHelper.COLUMN_IMAGE,contact.getImagePath());
        cont.put(DataBaseOpenHelper.COLUMN_BACKGROUND_IMAGE,contact.getBackgroundImagePath());
        insert(TABLE_CONTACTS,cont,null,dbInsert);
    }
    public void updateContact(Contact contact){
        ContentValues cont= new ContentValues();
        cont.put(DataBaseOpenHelper.COLUMN_NAME,contact.getName());
        cont.put(DataBaseOpenHelper.COLUMN_LAST_NAME,contact.getLastName());
        cont.put(DataBaseOpenHelper.COLUMN_NICK_NAME,contact.getNickName());
        cont.put(DataBaseOpenHelper.COLUMN_CORPORATION,contact.getCorporation());
        cont.put(DataBaseOpenHelper.COLUMN_TITLE,contact.getTitle());
        cont.put(DataBaseOpenHelper.COLUMN_PHONE1,contact.getPhone1());
        cont.put(DataBaseOpenHelper.COLUMN_PHONE2,contact.getPhone2());
        cont.put(DataBaseOpenHelper.COLUMN_PHONE3,contact.getPhone3());
        cont.put(DataBaseOpenHelper.COLUMN_TYPE1,contact.getType1());
        cont.put(DataBaseOpenHelper.COLUMN_TYPE2,contact.getType2());
        cont.put(DataBaseOpenHelper.COLUMN_TYPE3,contact.getType3());
        cont.put(DataBaseOpenHelper.COLUMN_EMAIL,contact.getEmail());
        cont.put(DataBaseOpenHelper.COLUMN_ADDRESS,contact.getAddress());
        cont.put(DataBaseOpenHelper.COLUMN_WEBSITE,contact.getWebsite());
        cont.put(DataBaseOpenHelper.COLUMN_GROUP_ID,contact.getGroupId());
        cont.put(DataBaseOpenHelper.COLUMN_NOTES,contact.getNotes());
        cont.put(DataBaseOpenHelper.COLUMN_IMAGE,contact.getImagePath());
        cont.put(DataBaseOpenHelper.COLUMN_BACKGROUND_IMAGE,contact.getBackgroundImagePath());
        update(TABLE_CONTACTS,cont,COLUMN_ID+"=?",new String[]{String.valueOf(contact.getId())});
    }
    public List<Contact> getContacts() {
        StringBuffer sql = new StringBuffer("Select * from ");
        sql.append(DataBaseOpenHelper.TABLE_CONTACTS);
        String[] args = new String[]{
        };

        final List<Contact> result = new ArrayList<Contact>();
        query(sql, args, new DbQueryInterface() {
            @Override
            public void onCursor(Cursor cursor) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Contact contact = new Contact();
                    contact.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                    contact.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                    contact.setLastName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LAST_NAME)));
                    contact.setNickName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NICK_NAME)));
                    contact.setCorporation(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CORPORATION)));
                    contact.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)));
                    contact.setPhone1(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE1)));
                    contact.setPhone2(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE2)));
                    contact.setPhone3(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE3)));
                    contact.setType1(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TYPE1)));
                    contact.setType2(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TYPE2)));
                    contact.setType3(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TYPE3)));
                    contact.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)));
                    contact.setAddress(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS)));
                    contact.setWebsite(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_WEBSITE)));
                    contact.setGroupId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_GROUP_ID)));
                    contact.setNotes(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTES)));
                    contact.setImagePath(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE)));
                    contact.setBackgroundImagePath(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BACKGROUND_IMAGE)));
                    result.add(contact);
                    cursor.moveToNext();
                }
            }
        });
        return result;
    }
}
