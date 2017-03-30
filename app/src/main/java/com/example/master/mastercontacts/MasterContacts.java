package com.example.master.mastercontacts;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.example.master.mastercontacts.dao.DataBaseOpenHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.sql.SQLException;

import io.fabric.sdk.android.Fabric;

/**
 * Created by master on 30/03/17.
 */

public class MasterContacts extends Application {
    private static final String TAG = Application.class.getCanonicalName();
    private static final String IS_FIRST_TIME = TAG + "_IS_FIRST_TIME";
    private DataBaseOpenHelper datasource;

    @Override
    public void onCreate() {
        super.onCreate();
        setUpFabric();
        Context context = getBaseContext();
        setUpDatabase(context);
    }

    private void setUpDatabase(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        boolean isFirstTime = sharedPref.getBoolean(IS_FIRST_TIME, true);
        //moveDatabase();
        if (isFirstTime) {
            //set up database
            Log.w(TAG, "Set up of DB");
            datasource = new DataBaseOpenHelper(this);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean(IS_FIRST_TIME, false);
            editor.apply();
        }else{
            DataBaseOpenHelper.init(context);
        }
    }

    private void setUpFabric() {
        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(true)
                .build();
        Fabric.with(fabric);
    }

    private void moveDatabase() {
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "/data/data/" + getPackageName() + "/databases/dao.db";
                Log.d("Path", currentDBPath);
                String backupDBPath = "backupname.db";
                File currentDB = new File(currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
            }
        } catch (Exception e) {

        }
    }
}
