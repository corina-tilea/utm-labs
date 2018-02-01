package com.szraksy.pam_lab5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Sezer on 7.12.2017.
 */

public class Sign_Up extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
    }

    public void btnNext(View view)
    {
        Intent intent = new Intent(Sign_Up.this, Home.class);
        startActivity(intent);
    }
}
