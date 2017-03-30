package com.example.master.mastercontacts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.master.mastercontacts.adapters.ViewPagerAdapter;
import com.example.master.mastercontacts.fragments.CallFragment;
import com.example.master.mastercontacts.fragments.ContactsFragment;
import com.example.master.mastercontacts.fragments.GroupsFragment;

public class MainActivity extends AppCompatActivity {

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

