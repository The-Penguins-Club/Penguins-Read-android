package club.thepenguins.android.activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;


import java.util.ArrayList;
import java.util.List;

import club.thepenguins.android.R;

import club.thepenguins.android.api.APIService;
import club.thepenguins.android.data.Category;
import club.thepenguins.android.data.CategoryModel;
import club.thepenguins.android.fragments.AboutFragment;
import club.thepenguins.android.fragments.HomeFragment;
import club.thepenguins.android.fragments.LinuxFragment;
import club.thepenguins.android.utils.Constants;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static club.thepenguins.android.utils.Constants.noInternet;

public class MainActivity extends AppCompatActivity {


    private DrawerLayout mDrawer;
    private Toolbar toolbar;

    private NavigationView nvView;
    private ActionBarDrawerToggle drawerToggle;
    private static String TAG = "MainActivity";
    private List<Category> categories;
    private ArrayList<CategoryModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawer = findViewById(R.id.drawer_layout);

        nvView = findViewById(R.id.nvView);

        setupDrawerContent(nvView);


        mDrawer = findViewById(R.id.drawer_layout);

        drawerToggle = setupDrawerToggle();

        drawerToggle.setDrawerIndicatorEnabled(true);

        drawerToggle.syncState();

        mDrawer.addDrawerListener(drawerToggle);


        Menu menu = nvView.getMenu();

        categories = new ArrayList<>();
        list = new ArrayList<>();

        getCategories(MainActivity.this, menu);

        menu.add(0, 707, 5, "About Us");

        FCM();


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

            default:
                mDrawer.openDrawer(GravityCompat.START);

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


        FragmentManager fragmentManager = getSupportFragmentManager();

        if (menuItem.getItemId() == R.id.home) {

            fragmentManager.beginTransaction().replace(R.id.flContent, HomeFragment.newInstance("null", "null")).commit();

        } else if (menuItem.getItemId() == 707) {

            fragmentManager.beginTransaction().replace(R.id.flContent, AboutFragment.newInstance("null", "null")).commit();

        } else {

            fragmentManager.beginTransaction().replace(R.id.flContent, LinuxFragment.newInstance(String.valueOf(menuItem.getItemId()), null)).commit();

        }

        menuItem.setChecked(false);

        mDrawer.closeDrawers();

    }

    private void FCM() {

        FirebaseMessaging.getInstance().subscribeToTopic("NewPost").addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d(TAG, "Successfully Subscribed to Notification Service");

            } else {
                Log.d(TAG, "Subscription to Notification Service failed");
            }
        });

    }

    private void getCategories(Context context, Menu menu1) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);
        Call<List<Category>> call = service.getCategories();


        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {

                categories = response.body();
                for (int i = 0; i < response.body().size(); i++) {

                    list.add(new CategoryModel(response.body().get(i).getName(), response.body().get(i).getId()));

                }

                Menu submenu = menu1.addSubMenu("Categories");
                for (int i = 0; i < list.size(); i++) {
                    submenu.add(i, list.get(i).getId(), i, list.get(i).getCatName());
                }


            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

                Toasty.error(context, noInternet, Toast.LENGTH_SHORT, true).show();
            }
        });

    }


}