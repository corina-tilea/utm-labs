package com.szraksy.pam_lab5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Sezer on 9.12.2017.
 */

public class Doctor_Contact extends Activity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(Doctor_Contact.this, Home.class);
                    startActivity(intent);
                case R.id.navigation_notifications:
                    Intent intent2 = new Intent(Doctor_Contact.this, Login.class);
                    startActivity(intent2);
                case R.id.plus:
                    Intent intent3 = new Intent(Doctor_Contact.this, Home.class);
                    startActivity(intent3);
                case R.id.schedule:
                    Intent intent4 = new Intent(Doctor_Contact.this, Login.class);
                    startActivity(intent4);
                case R.id.profile:
                    Intent intent5 = new Intent(Doctor_Contact.this, Doctor_Contact.class);
                    startActivity(intent5);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_contact);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    public void btnRequest(View view)
    {
        Intent intent = new Intent(Doctor_Contact.this, Approve.class);
        startActivity(intent);
    }


}
