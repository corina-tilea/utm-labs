package com.corina.android.lab4_2_pam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Corina on 7.12.2017.
 */

public class SignUpActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
    }

    public void btnNext(View view)
    {
        Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
