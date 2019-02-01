package org.loginregister.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import org.loginregister.androiddev.R;
import org.loginregister.fragments.FragmentA;
import org.loginregister.fragments.FragmentB;
import org.loginregister.fragments.FragmentC;
import org.loginregister.fragments.FragmentD;

public class NavigationAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(itemSelectedListener);
        navigation.setOnNavigationItemReselectedListener(itemReselectedListener);

        getSupportFragmentManager().beginTransaction().replace(
                R.id.fragment_container_navigationact,
                new FragmentA()
        ).commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_a:
                    getSupportFragmentManager().beginTransaction().replace(
                            R.id.fragment_container_navigationact,
                            new FragmentA()
                    ).commit();
                    return true;
                case R.id.navigation_b:
                    getSupportFragmentManager().beginTransaction().replace(
                            R.id.fragment_container_navigationact,
                            new FragmentB()
                    ).commit();
                    return true;
                case R.id.navigation_c:
                    getSupportFragmentManager().beginTransaction().replace(
                            R.id.fragment_container_navigationact,
                            new FragmentC()
                    ).commit();
                    return true;
                case R.id.navigation_d:
                    getSupportFragmentManager().beginTransaction().replace(
                            R.id.fragment_container_navigationact,
                            new FragmentD()
                    ).commit();
                    return true;
            }
            return false;
        }
    };


    private BottomNavigationView.OnNavigationItemReselectedListener itemReselectedListener = new BottomNavigationView.OnNavigationItemReselectedListener() {
        @Override
        public void onNavigationItemReselected(@NonNull MenuItem menuItem) {

        }
    };
}
