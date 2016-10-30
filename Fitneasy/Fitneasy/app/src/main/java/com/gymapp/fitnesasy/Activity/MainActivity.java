package com.gymapp.fitnesasy.Activity;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.gymapp.fitnesasy.Adapter.ViewPagerAdapter;
import com.gymapp.fitnesasy.Fragment.ListScheduleFragment;
import com.gymapp.fitnesasy.Fragment.LoveExerciseFragment;
import com.gymapp.fitnesasy.R;

import static com.gymapp.fitnesasy.DataManager.FavoriteExerciseDataManager.email;


public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener, NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    Menu menu;
    MenuItem itemDangNhap, menuITDangXuat,itThongBao;
    GoogleApiClient mGoogleApiClient;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        if (KiemTraKetNoiMang() == false)
            Toast.makeText(MainActivity.this, "Vui lòng kết nối mạng", Toast.LENGTH_SHORT).show();


        //   FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        //  nav_view = (NavigationView) findViewById(R.id.nav_view);
        // expandableListView = (ExpandableListView) findViewById(R.id.epMenu);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        // collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        //setSupportActionBar(toolbar);
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

      //  appBarLayout.addOnOffsetChangedListener(this);
        Firebase.setAndroidContext(this);

        user = FirebaseAuth.getInstance().getCurrentUser();

    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();
        switch (id) {
//        case R.id.nav_logout:
//        FirebaseAuth.getInstance().signOut();
//        startActivity(new Intent(this,SignInActivity.class));
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
     //   this.menu = menu;

        itemDangNhap = menu.findItem(R.id.itDangNhap);
        menuITDangXuat = menu.findItem(R.id.itDangXuat);
        itThongBao = menu.findItem(R.id.itThongBao);


        if (user != null) {
            email = user.getEmail();
            itemDangNhap.setTitle(email);
            menuITDangXuat.setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();
        switch (id) {

            case R.id.itDangNhap:

                if (user == null) {
                    Intent intent = new Intent(this, SignInActivity.class);
                    startActivity(intent);
                }
                    else {
                            email = user.getEmail();
                            itemDangNhap.setTitle(email);
                    }
                break;

//                if(accessToken == null && googleSignInResult == null && modelDangNhap.LayCachedDangNhap(this).equals("")){
//                    Intent iDangNhap = new Intent(this, DangNhapActivity.class);
//                    startActivity(iDangNhap);
            // }
            case R.id.itThongBao:
            {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ListScheduleFragment listScheduleFragment = new ListScheduleFragment();
                Bundle bundle = new Bundle();
                bundle.putString("scheduleId", "1");

                bundle.putString("scheduleName", "fdsf");
                bundle.putString("scheduleInfo", "fdsfds");
                listScheduleFragment.setArguments(bundle);
                fragmentTransaction.addToBackStack("TrangChuActivity");
                fragmentTransaction.replace(R.id.themFragment,listScheduleFragment);
                fragmentTransaction.commit();

            }
            break;

            case R.id.itMongMuon:
            {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                LoveExerciseFragment exerciseFragment = new LoveExerciseFragment();
                Bundle bundle = new Bundle();
                bundle.putString("like", "true");
               // bundle.putString("muscleName", muscleType.getName());
                exerciseFragment.setArguments(bundle);
                fragmentTransaction.addToBackStack("MainActivity");
                fragmentTransaction.replace(R.id.themFragment,exerciseFragment);
                fragmentTransaction.commit();


            }
            break;

            case R.id.itDangXuat:
                if(email!=null){
                    FirebaseAuth.getInstance().signOut();
//                    this.menu.clear();
//                    this.onCreateOptionsMenu(this.menu);
                }
//
//                if(googleSignInResult != null){
//                    Auth.GoogleSignInApi.signOut(mGoogleApiClient);
//                    this.menu.clear();
//                    this.onCreateOptionsMenu(this.menu);
//
//                }
//
//                if(!modelDangNhap.LayCachedDangNhap(this).equals("")){
//                    modelDangNhap.CapNhatCachedDangNhap(this,"");
//                    this.menu.clear();
//                    this.onCreateOptionsMenu(this.menu);
//                }

            case R.id.itSearch:
                Intent intentTimKiem = new Intent(this, SearchActivity.class);
                startActivity(intentTimKiem);

                break;
        }

        return true;
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }



    private boolean KiemTraKetNoiMang() {
        ConnectivityManager connectivityManager = (ConnectivityManager) MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }


}
