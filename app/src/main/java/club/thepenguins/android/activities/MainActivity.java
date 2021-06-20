package club.thepenguins.android.activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;


import com.google.android.material.navigation.NavigationView;


import club.thepenguins.android.R;

import club.thepenguins.android.fragments.AboutFragment;
import club.thepenguins.android.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {


    private DrawerLayout mDrawer;
    private Toolbar toolbar;

    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawer = findViewById(R.id.drawer_layout);

        nvDrawer = findViewById(R.id.nvView);

        setupDrawerContent(nvDrawer);


        mDrawer = findViewById(R.id.drawer_layout);

        drawerToggle = setupDrawerToggle();

        drawerToggle.setDrawerIndicatorEnabled(true);

        drawerToggle.syncState();

        mDrawer.addDrawerListener(drawerToggle);


        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.flContent, HomeFragment.newInstance("null", "null")).commit();

    }

    @Override

    protected void onPostCreate(Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);

        drawerToggle.syncState();

    }


    @Override

    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);

        drawerToggle.onConfigurationChanged(newConfig);

    }

    private ActionBarDrawerToggle setupDrawerToggle() {

        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);

    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                mDrawer.openDrawer(GravityCompat.START);

                return true;

        }

        return super.onOptionsItemSelected(item);

    }

    private void setupDrawerContent(NavigationView navigationView) {

        navigationView.setNavigationItemSelectedListener(

                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override

                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        selectDrawerItem(menuItem);

                        return true;

                    }

                });

    }


    public void selectDrawerItem(MenuItem menuItem) {

        Fragment fragment = null;

        Class fragmentClass = null;

        switch (menuItem.getItemId()) {

            case R.id.nav_first_fragment:

                fragmentClass = HomeFragment.class;

                break;

            case R.id.aboutfrag:

                fragmentClass = AboutFragment.class;

                break;

            //case R.id.nav_third_fragment:

            // fragmentClass = ThirdFragment.class;

            // break;

            default:

                // fragmentClass = FirstFragment.class;

        }


        try {

            fragment = (Fragment) fragmentClass.newInstance();

        } catch (Exception e) {

            e.printStackTrace();

        }


        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        menuItem.setChecked(true);

        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();

    }


}