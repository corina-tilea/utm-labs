package com.szraksy.pam_lab4;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity implements AnimationListener {

    ImageView imageview;
    Button start;
    Button stop;
    Animation Animasyonumuz;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private TextView textView;
    private Handler handler = new Handler();

    protected void onCreate(Bundle savedInstanceState) {
// TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageview = (ImageView) findViewById(R.id.imageview);
        start = (Button) findViewById(R.id.startButton);
        stop = (Button) findViewById(R.id.stopButon);
        textView = (TextView) findViewById(R.id.textView1);

        final ProgressBar simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);
        final Button startButton = (Button) findViewById(R.id.startButton);
        // perform click event on button
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // visible the progress bar


            }

        });


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.VISIBLE);
                simpleProgressBar.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.INVISIBLE);
                imageview.setVisibility(View.VISIBLE);
            }
        }, 5000);



        Animasyonumuz = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.anim_file);
        Animasyonumuz.setAnimationListener(this);
        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                imageview.startAnimation(Animasyonumuz);

            }
        });
        stop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                imageview.clearAnimation();

            }
        });


    }

    @Override
    public void onAnimationEnd(Animation animation) {
// Take any action after completing the animation

// check for zoom in animation
        if (animation ==Animasyonumuz) {
        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {
// TODO Auto-generated method stub

    }

    @Override
    public void onAnimationStart(Animation animation) {
// TODO Auto-generated method stub

    }


}