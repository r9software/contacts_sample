package com.example.master.mastercontacts.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dao.db";
    private static final String TABLE_CONTACTS = "CONTACTS";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_NICK_NAME = "nick_name";
    private static final String COLUMN_CORPORATION = "corporation";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_PHONE1 = "phone1";
    private static final String COLUMN_TYPE1 = "type1";
    private static final String COLUMN_PHONE2 = "phone2";
    private static final String COLUMN_TYPE2 = "type2";
    private static final String COLUMN_PHONE3 = "phone3";
    private static final String COLUMN_TYPE3 = "type3";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_WEBSITE = "website";
    private static final String COLUMN_GROUP_ID = "group_id";
    private static final String COLUMN_NOTES = "notes";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_BACKGROUND_IMAGE = "background_image";
    private static final String CONTACTS_TABLE_CREATE = "CREATE TABLE `" + TABLE_CONTACTS + "` " +
            "( `" + COLUMN_ID + "` INTEGER PRIMARY KEY AUTOINCREMENT," +
            " `" + COLUMN_NAME + "` TEXT, `" + COLUMN_LAST_NAME + "` TEXT, `" + COLUMN_NICK_NAME + "` TEXT, " +
            "`" + COLUMN_CORPORATION + "` TEXT, `" + COLUMN_TITLE + "` TEXT, `" + COLUMN_PHONE1 + "` TEXT, " +
            "`" + COLUMN_TYPE1 + "` INTEGER, `" + COLUMN_PHONE2 + "` TEXT, `" + COLUMN_TYPE2 + "` INTEGER, " +
            "`" + COLUMN_PHONE3 + "` TEXT, `" + COLUMN_TYPE3 + "` INTEGER, `" + COLUMN_EMAIL + "` TEXT, " +
            "`" + COLUMN_ADDRESS + "` TEXT, `" + COLUMN_WEBSITE + "` TEXT, `" + COLUMN_GROUP_ID + "` INTEGER, " +
            "`" + COLUMN_NOTES + "` TEXT, `" + COLUMN_IMAGE + "` TEXT, `" + COLUMN_BACKGROUND_IMAGE + "` TEXT )";
    private static final String GROUPS_TABLE = "GROUPS";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String GROUPS_TABLE_CREATE = "CREATE TABLE `" + GROUPS_TABLE + "` ( `"+COLUMN_ID+"` INTEGER PRIMARY KEY AUTOINCREMENT, `"+COLUMN_NAME+ "` TEXT, `" + COLUMN_DESCRIPTION + "` TEXT )";
    private static  Context appContext;
    private static DataBaseOpenHelper instance;
    private String DATABASE_PATH;


    public static void init(Context context) {
        Log.d("DATABASE HELPER", "SQLiteDatabaseHelper.init()");
        appContext = context.getApplicationContext();
    }
    public DataBaseOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CONTACTS_TABLE_CREATE);
        sqLiteDatabase.execSQL(GROUPS_TABLE_CREATE);
        DATABASE_PATH=sqLiteDatabase.getPath();
    }
    public static DataBaseOpenHelper getInstance() {
        if (instance == null) {
            if (appContext == null) {
                Log.e("DATABASE HELPER", "SQLiteDatabaseHelper.getInstance(): app context == NULL");
                return null;
            }
            instance = new DataBaseOpenHelper(appContext);
        }
        return instance;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(DataBaseOpenHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(sqLiteDatabase);
    }
    public SQLiteDatabase open(boolean write) {
        int typeOfDBM;
        if (write)
            typeOfDBM = SQLiteDatabase.OPEN_READWRITE;
        else
            typeOfDBM = SQLiteDatabase.OPEN_READONLY;
        SQLiteDatabase database = SQLiteDatabase.openDatabase(DATABASE_PATH, null, typeOfDBM);
        return database;
    }

    /**
     * Provides an instance of a writable database using parents method checking if a database is
     * already created or copying from assets if not.
     *
     * @return SQLiteDatabase containing the latest update or a copy from assets when not created.
     */
    public SQLiteDatabase openWritable() {
        return super.getWritableDatabase();
    }

}