package e.acer_aspire.fooddeliveryservice;
/*
 * This is main activity, where all necessary things are place such
 * as bottom navigation view, and fragments are placed)
 * created by: Abdurakhmon Kodirov
 * contributed by Jasurbek Nabijonov
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import e.acer_aspire.fooddeliveryservice.adapters.ViewPagerAdapter;
import e.acer_aspire.fooddeliveryservice.fragments.MainFragment;
import e.acer_aspire.fooddeliveryservice.fragments.MenuFragment;
import e.acer_aspire.fooddeliveryservice.fragments.OrdersFragment;
import e.acer_aspire.fooddeliveryservice.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    // For managing logs
    private final String TAG = "myLogs";

    // For DrawerLayout which is in activity_main
    @BindView(R.id.nav_view) NavigationView navView;
    // For Navigation view
    @BindView(R.id.main_drawer_layout) DrawerLayout drawerLayout;
    // For View pager (used to swap between bottom navigation view)
//    @Bind(R.id.tab_toolbar) Toolbar mToolBar;
    // Bottom navigation view which is used to change fragments like main, menu, orders and profile
    @BindView(R.id.main_navigation) BottomNavigationView bottomNavigationView;
    private static final int REQUEST_LOGIN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeLoginOrSignUp();
        // Bind all attributes with resource ids
        ButterKnife.bind(this);

        init();
    /**
     * Initialization function which configures view
     * and set necessary parameters
     */

    }



    private void init() {
        /*  Sets action bar to the app,
            that is needed to call navigation view.
            In application situated in upfront
         */
        /*setSupportActionBar(mToolBar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }*/

        if (navView != null) {
            setupDrawerContent(navView);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(bottomItemSelected);
//        bottomNavigationView.

    }

    ////////////////////////////////////////////////////// View Pager
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new MainFragment(), "Main");
        adapter.addFrag(new MenuFragment(), "Menu");
        adapter.addFrag(new OrdersFragment(), "Orders");
        adapter.addFrag(new ProfileFragment(), "Profile");
        viewPager.setAdapter(adapter);
    }

    //////////////////////////////////////////////////////


    ////////////////////////////////////////////////////// Navigation View

    // Click Listener which changes fragments according to position of MenuItem
    private BottomNavigationView.OnNavigationItemSelectedListener bottomItemSelected = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_main:
                    Toast.makeText(MainActivity.this, "Navigation Main", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.nav_menu:
                    Toast.makeText(MainActivity.this, "Navigation Menu", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.nav_orders:
                    Toast.makeText(MainActivity.this, "Navigation Orders", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.nav_profile:
                    Toast.makeText(MainActivity.this, "Navigation Profile", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };


    /**
     * Function to take action when chosen navigation view
     * @param navigationView NavigationView
     */
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.drawer_nav_profile:
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    /**
     * Going to Login Activity
     * User should be initialized in order to use application
     * @onActivityResult function checks it
     */
    private void makeLoginOrSignUp() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, REQUEST_LOGIN);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_LOGIN) {
            if (resultCode == RESULT_OK) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Toast.makeText(this, "in main activity" + user.getEmail()
                            , Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
