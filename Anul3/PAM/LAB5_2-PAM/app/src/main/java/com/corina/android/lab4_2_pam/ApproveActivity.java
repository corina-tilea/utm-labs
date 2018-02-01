package com.corina.android.lab4_2_pam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Corina on 28.12.2017.
 */

public class ApproveActivity extends Activity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(ApproveActivity.this, HomeActivity.class);
                    startActivity(intent);
                case R.id.navigation_notifications:
                    Intent intent2 = new Intent(ApproveActivity.this, LoginActivity.class);
                    startActivity(intent2);
                case R.id.plus:
                    Intent intent3 = new Intent(ApproveActivity.this, HomeActivity.class);
                    startActivity(intent3);
                case R.id.schedule:
                    Intent intent4 = new Intent(ApproveActivity.this, LoginActivity.class);
                    startActivity(intent4);
                case R.id.profile:
                    Intent intent5 = new Intent(ApproveActivity.this, ContactDoctorActivity.class);
                    startActivity(intent5);
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_approved);
    }
    public void btnConfirm(View view)
    {
        Intent intent = new Intent(ApproveActivity.this, ContactDoctorActivity.class);
        startActivity(intent);
    }

    public void btnCancel(View view)
    {
        Intent intent = new Intent(ApproveActivity.this, ContactDoctorActivity.class);
        startActivity(intent);
    }
}
