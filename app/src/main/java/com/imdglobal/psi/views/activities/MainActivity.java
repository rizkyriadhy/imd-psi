package com.imdglobal.psi.views.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.imdglobal.psi.R;
import com.imdglobal.psi.views.fragments.MapPsiFragment;
import com.imdglobal.psi.views.fragments.DefaultFragment;
import com.imdglobal.psi.views.fragments.StatisticFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rizkyriadhy on 19/06/17.
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Context context;
    private Toolbar toolbar;
    private FrameLayout frameLayout;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    private Fragment fragmentMap;
    private Fragment fragmentStatistic;

    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        declareView();
        initData();
    }

    /**
     * method for initialize all view on on this activity
     */
    private void declareView(){
        DateFormat df = new SimpleDateFormat("dd MMM yyyy");
        date = df.format(new Date());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("PSI Map for "+date);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_map);

    }

    /**
     * method for initialize data fragment on this activity
     */
    private void initData(){
        fragmentMap = new MapPsiFragment();
        fragmentStatistic = new StatisticFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_content, fragmentMap)
                .commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_map) {
            switchContent(fragmentMap);
            setTitle("PSI Map for "+date);
        } else if (id == R.id.nav_24hr_psi) {
            switchContent(fragmentStatistic);
            setTitle("24-hr PSI for "+date);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * method to switch content fragment
     *
     * @param fragment
     */
    public void switchContent(final Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_content, fragment)
                .commit();
    }
}
