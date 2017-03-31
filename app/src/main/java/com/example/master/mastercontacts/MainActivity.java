package com.example.master.mastercontacts;

import android.Manifest;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.master.mastercontacts.adapters.ViewPagerAdapter;
import com.example.master.mastercontacts.fragments.CallFragment;
import com.example.master.mastercontacts.fragments.ContactsFragment;
import com.example.master.mastercontacts.fragments.GroupsFragment;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1 ;
    private ViewPager mViewPager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_call:
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_groups:
                    mViewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }

    };
    private ContactsFragment contactsFragment;
    private CallFragment callFragment;
    private GroupsFragment groupsFragment;
    private BottomNavigationView navigation;
    private MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mViewPager= (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(mViewPager);
        validateContactsPermission();
    }

    private void validateContactsPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS) || ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CALL_PHONE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("The permissions are so we give you a better experience")
                        .setPositiveButton("OK", listener)
                        .setNegativeButton("Cancel", listener)
                        .create()
                        .show();
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.CALL_PHONE},
                        PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        }
    }
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {

        final int BUTTON_NEGATIVE = -2;
        final int BUTTON_POSITIVE = -1;

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case BUTTON_NEGATIVE:
                    // int which = -2
                    dialog.dismiss();
                    break;

                case BUTTON_POSITIVE:
                    // int which = -1
                    ActivityCompat.requestPermissions(
                            MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.CALL_PHONE},
                            PERMISSIONS_REQUEST_READ_CONTACTS);
                    dialog.dismiss();
                    break;
            }
        }
    };
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_READ_CONTACTS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContactsFromDevice();
                }else{
                    Snackbar.make(findViewById(R.id.viewpager),"We could not receive the permission to read your contacts", Snackbar.LENGTH_SHORT).show();
                }
                break;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void readContactsFromDevice() {

    }

    private void setupViewPager(ViewPager mViewPager) {
        addListernersForViewPager(mViewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        contactsFragment=new ContactsFragment();
        callFragment=new CallFragment();
        groupsFragment=new GroupsFragment();
        adapter.addFragment(contactsFragment);
        adapter.addFragment(callFragment);
        adapter.addFragment(groupsFragment);
        mViewPager.setAdapter(adapter);
    }

    private void addListernersForViewPager(ViewPager mViewPager) {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                navigation.getMenu().getItem(position).setChecked(true);
                prevMenuItem = navigation.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}

