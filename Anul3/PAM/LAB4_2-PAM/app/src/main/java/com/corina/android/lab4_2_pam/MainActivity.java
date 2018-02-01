package com.corina.android.lab4_2_pam;

import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageVewLoadingBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageVewLoadingBar = (ImageView) findViewById(R.id.image_view_loading);
        imageVewLoadingBar.setVisibility(View.VISIBLE);
        textView = (TextView) findViewById(R.id.text_view_init_loading);
        final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imageVewLoadingBar.invalidate();
                    showAnimationLoading(imageVewLoadingBar);
                    handler.postDelayed(this, 5000);
                }
            }, 5000);
    }


    public void showAnimationLoading(final ImageView imageVewLoadingBar){
        imageVewLoadingBar.setVisibility(View.VISIBLE);
        Drawable drawable=imageVewLoadingBar.getDrawable();
        if (drawable instanceof AnimatedVectorDrawableCompat) {
            final AnimatedVectorDrawableCompat animatedVDC = (AnimatedVectorDrawableCompat) drawable;
            animatedVDC.start();
        } else if (drawable instanceof AnimatedVectorDrawable) {
            AnimatedVectorDrawable animateVD = (AnimatedVectorDrawable) drawable;
            animateVD.start();
        }
    }

}
