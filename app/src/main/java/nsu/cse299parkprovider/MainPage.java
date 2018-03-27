package nsu.cse299parkprovider;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainPage extends AppCompatActivity {

    Toolbar mainToolbar;


    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        mainToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mainToolbar);


        navView = (NavigationView) findViewById(R.id.navigationView);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mainToolbar, R.string.drawer_open, R.string.drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, new MapDriver());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Search space");



        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switchFragment(item.getItemId());

                return false;
            }
        });

    }





        private void switchFragment(int item) {


            switch (item) {
                case R.id.history:
//                    NewTrip ft = new NewTrip();
//                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction.replace(R.id.main_container, ft);
//                    fragmentTransaction.commit();
//                    getSupportActionBar().setTitle("");
//                    //item.setChecked(true);
//                    drawerLayout.closeDrawers();

                    Toast.makeText(this, "History page will be opened", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.rating:
//                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction.replace(R.id.main_container, new YourTrips());
//                    fragmentTransaction.commit();
//                    getSupportActionBar().setTitle("");
//                    //item.setChecked(true);
//                    drawerLayout.closeDrawers();
                    Toast.makeText(this, "Rating pop-up will be opened", Toast.LENGTH_SHORT).show();

                    break;


                case R.id.help:
//                    NewTrip ft = new NewTrip();
//                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction.replace(R.id.main_container, ft);
//                    fragmentTransaction.commit();
//                    getSupportActionBar().setTitle("");
//                    //item.setChecked(true);
//                    drawerLayout.closeDrawers();
                    Toast.makeText(this, "Help page will be opened", Toast.LENGTH_SHORT).show();

                    break;

                case R.id.provider:
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_container, new MapProvider());
                    fragmentTransaction.commit();
                    getSupportActionBar().setTitle("");
                    drawerLayout.closeDrawers();
                    break;

                case R.id.become:
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_container, new SignupProvider());
                    fragmentTransaction.commit();
                    getSupportActionBar().setTitle("");
                    drawerLayout.closeDrawers();
                    break;



                case R.id.logout:




                FirebaseAuth.getInstance().signOut();
                       Intent intent = new Intent(MainPage.this, LoginPage.class);
                       startActivity(intent);
                        finish();

                drawerLayout.closeDrawers();

                    break;




            }


        }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        actionBarDrawerToggle.syncState();
    }

    }

