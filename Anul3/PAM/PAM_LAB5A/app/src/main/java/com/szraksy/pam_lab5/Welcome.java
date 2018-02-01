package com.szraksy.pam_lab5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Sezer on 7.12.2017.
 */

public class Welcome extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
    }

    public void btnSignup(View view)
    {
        Intent intent = new Intent(Welcome.this, Sign_Up.class);
        startActivity(intent);
    }

    public void btnLogin(View view)
    {
        Intent intent = new Intent(Welcome.this, Login.class);
        startActivity(intent);
    }
}
