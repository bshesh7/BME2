package com.example.bme_003;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import Fragments.CompeteFragment;
import Fragments.ExerciseFragment;
import Fragments.FoodFragment;
import Fragments.HomeFragment;
import Fragments.RoutineFragment;


public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Fragment selectorFragment;

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topbarapp,menu);
        return super.onCreateOptionsMenu(menu);
    }
    // three dot menu top bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                Log.i("Item Selected","Settings");
                return true;
            case R.id.help:
                Log.i("Item Selected","Help");
                return true;
            case R.id.level:
                Log.i("Item Selected","Level");
                Intent intent = new Intent(this, LevelActivity.class);
                startActivity(intent);
                return true;
            case R.id.profile:
                Log.i("Item Selected","Profile");
                Intent intent2 = new Intent(this, ProfileActivity.class);
                startActivity(intent2);
                return true;
            default:
                return true;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        selectorFragment = new HomeFragment();
                        break;

                    case R.id.nav_routine:
                        selectorFragment = new RoutineFragment();
                        break;
                    case R.id.nav_food:
                        selectorFragment = new FoodFragment();
                        break;
                    case R.id.nav_exercise:
                        selectorFragment = new ExerciseFragment();
                        break;
                    case R.id.nav_people:
                        selectorFragment = new CompeteFragment();
                        break;
                }
                if (selectorFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectorFragment).commit();
                }
                return true;
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }
}
