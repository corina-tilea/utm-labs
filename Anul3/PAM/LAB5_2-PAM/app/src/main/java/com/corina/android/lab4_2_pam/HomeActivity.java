package com.corina.android.lab4_2_pam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Corina on 28.12.2017.
 */

public class HomeActivity extends Activity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.navigation_notifications:
                    Intent intent2 = new Intent(HomeActivity.this, ApproveActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.plus:
                    Intent intent3 = new Intent(HomeActivity.this, HomeActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.schedule:
                    Intent intent4 = new Intent(HomeActivity.this, DoctorsActivity.class);
                    startActivity(intent4);
                    break;
                case R.id.profile:
                    Intent intent5 = new Intent(HomeActivity.this, ContactDoctorActivity.class);
                    startActivity(intent5);
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    public void btnRequest(View view)
    {
        Intent intent = new Intent(HomeActivity.this, ApproveActivity.class);
        startActivity(intent);
    }


}