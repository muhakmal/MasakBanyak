package com.baskom.masakbanyak;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements HomeFragment.HomeFragmentInteractionListener,
        TransactionFragment.TransactionFragmentInteractionListener {
    private Toolbar mToolbar;
    private BottomNavigationView mBottomNavigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            Fragment fragment = manager.findFragmentById(R.id.content);

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (fragment instanceof TransactionFragment) {
                        transaction.replace(R.id.content, HomeFragment.newInstance(getCateringList()));
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                    return true;
                case R.id.navigation_transaksi:
                    if (fragment instanceof HomeFragment) {
                        transaction.replace(R.id.content, TransactionFragment.newInstance("01", "02"));
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                    return true;

                case R.id.navigation_notifikasi:
                    if (fragment instanceof NotifikasiFragment) {
                        transaction.replace(R.id.content, TransactionFragment.newInstance("01", "02"));
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                    return true;
                case R.id.navigation_akun:
                    if (fragment instanceof AkunFragment) {
                        transaction.replace(R.id.content, TransactionFragment.newInstance("01", "02"));
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigation = (BottomNavigationView) findViewById(R.id.navigation);
        mBottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //instance yang gw panggil buat hack BottomNavigationView-nya
        BottomNavigationHelper.removeShiftMode(mBottomNavigation);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content, HomeFragment.newInstance(getCateringList()));
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = manager.findFragmentById(R.id.content);

        if (fragment instanceof HomeFragment) {
            mBottomNavigation.setSelectedItemId(R.id.navigation_home);
        } else {
            mBottomNavigation.setSelectedItemId(R.id.navigation_transaksi);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onHomeFragmentInteraction(String parameter) {
        Toast.makeText(this, parameter, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransactionFragmentInteraction(Uri uri) {

    }

    public ArrayList<Catering> getCateringList() {
        Random random = new Random();
        ArrayList<Catering> cateringList = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            cateringList.add(new Catering("Catering0" + (i + 1),
                    "AddressofCatering0" + (i + 1),
                    random.nextInt(5)));
        }

        return cateringList;
    }
}