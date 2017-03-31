package com.example.master.mastercontacts.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.master.mastercontacts.model.Contact;
import com.example.master.mastercontacts.model.Group;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
import static com.example.master.mastercontacts.dao.DataBaseOpenHelper.GROUPS_TABLE;
import static com.example.master.mastercontacts.dao.DataBaseOpenHelper.TABLE_CONTACTS;


/**
 * Created by master on 31/03/17.
 */

public class GroupsSqliteImpl extends AbstractBaseSqliteDao {
    public void insertGroup(Group group, DbInsertInterface dbInsert){
        ContentValues cont= new ContentValues();
        cont.put(DataBaseOpenHelper.COLUMN_NAME,group.getName());
        cont.put(DataBaseOpenHelper.COLUMN_DESCRIPTION,group.getDescription());
        insert(DataBaseOpenHelper.GROUPS_TABLE,cont,null,dbInsert);
    }

      public List<Group> getGroups() {
        StringBuffer sql = new StringBuffer("Select ");
        sql.append(COLUMN_ID).append(",");
        sql.append(DataBaseOpenHelper.COLUMN_NAME).append(",");
        sql.append(DataBaseOpenHelper.COLUMN_DESCRIPTION).append(" from ");
        sql.append(DataBaseOpenHelper.GROUPS_TABLE);
        String[] args = new String[]{

        };

        final List<Group> result = new ArrayList<Group>();
        query(sql, args, new DbQueryInterface() {
            @Override
            public void onCursor(Cursor cursor) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Group group = new Group();
                    group.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                    group.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                    group.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)));
                    result.add(group);
                    cursor.moveToNext();
                }
            }
        });
        return result;
    }
    public HashMap<String,Integer> getHashGroups() {
        StringBuffer sql = new StringBuffer("Select ");
        sql.append(COLUMN_ID).append(",");
        sql.append(DataBaseOpenHelper.COLUMN_NAME).append(",");
        sql.append(DataBaseOpenHelper.COLUMN_DESCRIPTION).append(" from ");
        sql.append(DataBaseOpenHelper.GROUPS_TABLE);
        String[] args = new String[]{

        };

        final HashMap<String,Integer> result = new HashMap<>();
        query(sql, args, new DbQueryInterface() {
            @Override
            public void onCursor(Cursor cursor) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {

                    result.put(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                    cursor.moveToNext();
                }
            }
        });
        return result;
    }

    public List<Contact> getContacts(int groupId) {
        StringBuffer sql = new StringBuffer("Select * from ");
        sql.append(TABLE_CONTACTS).append(" where ");
        sql.append(COLUMN_GROUP_ID).append(" = ?");
        String[] args = new String[]{
            String.valueOf(groupId)
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