package com.szraksy.pam_lab5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Sezer on 7.12.2017.
 */

public class Login extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }


    public void btnLogin(View view)
    {
        Intent intent = new Intent(Login.this, Home.class);
        startActivity(intent);
    }

    public void btnSignup(View view)
    {
        Intent intent = new Intent(Login.this, Sign_Up.class);
        startActivity(intent);
    }
}
