package com.corina.android.lab4_1_pam;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void animate(View view) {
        ImageView imgView=(ImageView)view;
        Drawable drawable=((ImageView) view).getDrawable();
        if(drawable instanceof AnimatedVectorDrawableCompat){
            AnimatedVectorDrawableCompat animatedVDC=(AnimatedVectorDrawableCompat)drawable;
            animatedVDC.start();

        }else if(drawable instanceof AnimatedVectorDrawable){
            AnimatedVectorDrawable animateVD=(AnimatedVectorDrawable)drawable;
            animateVD.start();
        }

    }
}
