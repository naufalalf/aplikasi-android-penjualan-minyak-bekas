package com.gigih.wasteoil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);

        final HomeFragment homeFragment = new HomeFragment();
        final CartFragment cartFragment = new CartFragment();
        final ProfileFragment profileFragment = new ProfileFragment();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.home) {
                    setFragment(homeFragment);
                    return true;
                } else if (id == R.id.cart) {
                    setFragment(cartFragment);
                    return true;
                } else if (id == R.id.profile) {

                    auth = FirebaseAuth.getInstance();
                    if (auth.getCurrentUser() != null) {
                        AfterLoginFragment fragtry = new AfterLoginFragment();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_layout, fragtry)
                                .commit();
                    }else {
                        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    return true;
                }
                return false;
            }
        });

        navigationView.setSelectedItemId(R.id.home);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
