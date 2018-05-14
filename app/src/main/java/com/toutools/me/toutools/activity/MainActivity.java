package com.toutools.me.toutools.activity;

import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.toutools.me.toutools.fragment.AboutFragment;
import com.toutools.me.toutools.fragment.CategoriesFragment;
import com.toutools.me.toutools.fragment.ConditionFragment;
import com.toutools.me.toutools.fragment.FavoriteFragment;
import com.toutools.me.toutools.fragment.HelpFragment;
import com.toutools.me.toutools.fragment.HistoryFragment;
import com.toutools.me.toutools.fragment.ListPostFragment;
import com.toutools.me.toutools.fragment.MainFragment;
import com.toutools.me.toutools.fragment.NotiFragment;
import com.toutools.me.toutools.R;


public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;

    ActionBarDrawerToggle drawerToggle;

    CoordinatorLayout rootLayout;
    FloatingActionButton fabBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initInstances();
        //Add Fragment
        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, MainFragment.newInstance())
                    .commit();
        }

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    private void initInstances() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.open_drawer, R.string.close_drawer);

        drawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rootLayout = (CoordinatorLayout) findViewById(R.id.rootLayout);

        navigationView = (NavigationView) findViewById(R.id.navigation);
        setupDrawerContent(navigationView);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//
//        return super.onCreateOptionsMenu(menu);
//    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        selectDrawerItem(item);
                        return true;
                    }
                });
    }

    private void selectDrawerItem(MenuItem item) {

        Fragment fragment = null;
        Class fragmentClass;
        switch (item.getItemId()) {
            case R.id.nav_first_fragment:
                fragmentClass = MainFragment.class;
                break;
            case R.id.nav_second_fragment:
                fragmentClass = CategoriesFragment.class;
                break;
            case R.id.nav_third_fragment:
                fragmentClass = NotiFragment.class;
                break;
            case R.id.nav_fourth_fragment:
                fragmentClass = FavoriteFragment.class;
                break;
            case R.id.nav_fifth_fragment:
                fragmentClass = ListPostFragment.class;
                break;
            case R.id.nav_sixth_fragment:
                fragmentClass = HistoryFragment.class;
                break;
            case R.id.item_about:
                fragmentClass = AboutFragment.class;
                break;
            case R.id.item_condition:
                fragmentClass = ConditionFragment.class;
                break;
            case R.id.item_help:
                fragmentClass = HelpFragment.class;
                break;
            default:
               return;
        }
        try {
            fragment = (Fragment)fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, fragment)
                    .commit();
        }
        item.setChecked(true);
        setTitle(item.getTitle());
        drawerLayout.closeDrawers();


    }


}
