package com.baskom.masakbanyak;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
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

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity
        implements HomeFragment.HomeFragmentInteractionListener,
        TransactionFragment.TransactionFragmentInteractionListener,
        CateringFragment.CateringFragmentInteractionListener,
        NotificationFragment.NotificationFragmentInteractionListener,
        ProfileFragment.ProfileFragmentInteractionListener,
        PacketFragment.PacketFragmentInteractionListener {

    private Toolbar mToolbar;
    private BottomNavigationView mBottomNavigation;
    private SearchView mSearchView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            Fragment fragment = manager.findFragmentById(R.id.content);

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (!(fragment instanceof HomeFragment)) {
                        transaction.replace(R.id.content, HomeFragment.newInstance(getCateringList()));
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                    return true;
                case R.id.navigation_transaction:
                    if (!(fragment instanceof TransactionFragment)) {
                        transaction.replace(R.id.content, TransactionFragment.newInstance("01", "02"));
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                    return true;

                case R.id.navigation_notification:
                    if (!(fragment instanceof NotificationFragment)) {
                        transaction.replace(R.id.content, NotificationFragment.newInstance("01", "02"));
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                    return true;
                case R.id.navigation_profile:
                    if (!(fragment instanceof ProfileFragment)) {
                        transaction.replace(R.id.content, ProfileFragment.newInstance("01", "02"));
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

        mBottomNavigation = findViewById(R.id.navigation);
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
        if(!mSearchView.isIconified()) {
            mSearchView.onActionViewCollapsed();
        }else {
            super.onBackPressed();

            FragmentManager manager = getSupportFragmentManager();
            Fragment fragment = manager.findFragmentById(R.id.content);
            if (fragment instanceof HomeFragment) {
                mBottomNavigation.setSelectedItemId(R.id.navigation_home);
            } else if (fragment instanceof TransactionFragment) {
                mBottomNavigation.setSelectedItemId(R.id.navigation_transaction);
            } else if (fragment instanceof NotificationFragment) {
                mBottomNavigation.setSelectedItemId(R.id.navigation_notification);
            } else if (fragment instanceof ProfileFragment) {
                mBottomNavigation.setSelectedItemId(R.id.navigation_profile);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        mSearchView = (SearchView) searchItem.getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                ArrayList<Catering> filteredCateringList = new ArrayList<>(Collections2
                        .filter(getCateringList(), new CateringFilter(query)));
                transaction.replace(R.id.content, HomeFragment.newInstance(filteredCateringList));
                transaction.addToBackStack(null);
                transaction.commit();

                return true;
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
    public void onHomeFragmentInteraction(Catering catering) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content, CateringFragment.newInstance(catering));
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onCateringFragmentInteraction(Packet packet) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment previousFragment = manager.findFragmentByTag("Packet");
        if(previousFragment != null){
            transaction.remove(previousFragment);
        }
        transaction.addToBackStack(null);
        PacketFragment fragment = PacketFragment.newInstance(packet);
        fragment.show(transaction, "Packet");
    }

    @Override
    public void onPacketFragmentInteraction(Packet packet) {

    }

    @Override
    public void onTransactionFragmentInteraction(Uri uri) {

    }

    @Override
    public void onNotificationFragmentInteraction(Uri uri) {

    }

    @Override
    public void onProfileFragmentInteraction(Uri uri) {

    }

    public ArrayList<Catering> getCateringList() {
        Random random = new Random();
        ArrayList<Catering> cateringList = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            ArrayList<Packet> packetList = new ArrayList<>();

            for(int x = 0; x < 7; x++){
                packetList.add(new Packet("Packet0"+(x+1), x*10000));
            }

            cateringList.add(new Catering("Catering0" + (i + 1),
                    "AddressofCatering0" + (i + 1),
                    random.nextInt(5), packetList));
        }

        return cateringList;
    }

}