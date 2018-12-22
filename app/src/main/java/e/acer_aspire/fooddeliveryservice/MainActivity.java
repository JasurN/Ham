package e.acer_aspire.fooddeliveryservice;
/*
 * This is main activity, where all necessary things are place such
 * as bottom navigation view, and fragments are placed)
 * created by: Abdurakhmon Kodirov
 * contributed by Jasurbek Nabijonov
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.IOException;
import java.util.UUID;
import butterknife.BindView;
import butterknife.ButterKnife;
import e.acer_aspire.fooddeliveryservice.fragments.FavouriteFragment;
import e.acer_aspire.fooddeliveryservice.fragments.MenuFragment;
import e.acer_aspire.fooddeliveryservice.fragments.OrdersFragment;
import e.acer_aspire.fooddeliveryservice.fragments.ProfileFragment;
import e.acer_aspire.fooddeliveryservice.handlers.Database;
import e.acer_aspire.fooddeliveryservice.handlers.Storage;

public class MainActivity extends AppCompatActivity {
    // For managing logs
    private final String TAG = "myLogs";

    private final int PICK_IMAGE_REQUEST = 71;

    // For Navigation view which is inside drawer layout
    @BindView(R.id.nav_view) NavigationView navigationView;
    // For drawer layout
    @BindView(R.id.main_drawer_layout)
    DrawerLayout drawerLayout;
    // Bottom navigation view which is used to change fragments like main, menu, orders and profile
    @BindView(R.id.main_navigation)
    BottomNavigationView bottomNavigationView;

    private static final int REQUEST_LOGIN = 1;
    private final int[] navIndex = {1, 2, 3, 0};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeLoginOrSignUp();
        // Bind all attributes with resource ids
        ButterKnife.bind(this);
        loadFragment(new FavouriteFragment());
        /** If Navigation view is not drawn
         *  then draw, else do not draw
         */
        setupDrawerContent(navigationView);
        navigationView.getMenu().getItem(navIndex[0]).setChecked(true);
        /**
          Set item select listener for BottomNavigationView */
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomItemSelected);
        test();
    }

    //TODO: Delete test function before publish
    private void test() {

        Database database = new Database();
        database.getPersonByEmail("some");
//        database.getOrderByStatus(0);
//        database.getAllMeals();
//        testAddMeal("plov", "Uzbek National Food", "rice, carrot, oit, meat", 13000);
//        //testAddMeal("somsa", "some info", "p1ls", 12000);


    }

    private void testAddMeal(String name, String description, String ingredients, float price) {
        Database database = new Database();
        database.insertNewMeal(name, description, ingredients, price);
    }
    //delete untill here

    ////////////////////////////////////////////////////// Bottom Navigation View
    // Click Listener which changes fragments according to position of MenuItem
    private BottomNavigationView.OnNavigationItemSelectedListener bottomItemSelected = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.nav_favourites:
                    fragment = new FavouriteFragment();
                    navigationView.getMenu().getItem(navIndex[0]).setChecked(true);
                    Toast.makeText(MainActivity.this, "Navigation Main", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_menu:
                    fragment = new MenuFragment();
                    navigationView.getMenu().getItem(navIndex[1]).setChecked(true);
                    Toast.makeText(MainActivity.this, "Navigation Menu", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_orders:
                    fragment = new OrdersFragment();
                    navigationView.getMenu().getItem(navIndex[2]).setChecked(true);
                    Toast.makeText(MainActivity.this, "Navigation Orders", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_profile:
                    fragment = new ProfileFragment();
                    navigationView.getMenu().getItem(navIndex[3]).setChecked(true);
                    Toast.makeText(MainActivity.this, "Navigation Profile", Toast.LENGTH_SHORT).show();
                    break;
            }
            drawerLayout.closeDrawers();
            return loadFragment(fragment);
        }
    };

    /**
     * Loads selected fragment.
     * It can be selected via NavigationView,
     * or BottomNavigationView.
     *
     * @param fragment Fragment
     * @return boolean
     */
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }



    ////////////////////////////////////////////////////// Navigation View
    /**
     * Function to take action when chosen navigation view
     *
     * @param navigationView NavigationView
     */
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.drawer_nav_profile:
                        fragment = new ProfileFragment();
                        bottomNavigationView.setSelectedItemId(R.id.nav_profile);
                        break;
                    case R.id.drawer_nav_favourite:
                        fragment = new FavouriteFragment();
                        bottomNavigationView.setSelectedItemId(R.id.nav_favourites);
                        break;
                    case R.id.drawer_nav_meals:
                        fragment = new MenuFragment();
                        bottomNavigationView.setSelectedItemId(R.id.nav_menu);
                        break;
                    case R.id.drawer_nav_order:
                        fragment = new OrdersFragment();
                        bottomNavigationView.setSelectedItemId(R.id.nav_orders);
                        break;
                    case R.id.drawer_nav_logout:
                        fragment = new OrdersFragment();
                        break;
                }
                drawerLayout.closeDrawers();
                return loadFragment(fragment);
            }
        });
    }

    //////////////////////////////////////////////////////


    /**
     * Going to Login Activity
     * User should be initialized in order to use application
     *
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

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


    /**
     * Handler for signOut
     * It signOut from current user and start Login page
     */
    public void signOutOnClick(View view) {
        FirebaseAuth.getInstance().signOut();
        makeLoginOrSignUp();
    }

    private Uri filePath;
    FirebaseStorage storage;
    StorageReference storageReference;

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private void uploadImage() {
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }
}
