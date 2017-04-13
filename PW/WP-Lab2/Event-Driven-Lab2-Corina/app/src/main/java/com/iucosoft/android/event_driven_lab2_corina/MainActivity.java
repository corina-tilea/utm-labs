package com.iucosoft.android.event_driven_lab2_corina;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnKeyListener, GestureDetector.OnGestureListener {

    TextView textView;
    GestureDetectorCompat gestureDetector;


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button buttonOpenDialog=(Button)findViewById(R.id.buttonOpenDialog);
        buttonOpenDialog.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View viewId){
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create(); //Read Update
                        alertDialog.setTitle("hi");
                        alertDialog.setMessage("this is my app");

                        // alertDialog.setButton("Continue..", new DialogInterface.OnClickListener(){
                        //     public void onClick(DialogInterface dialog, int which) {
                        // here you can add functions
                        //     }
                        //  });

                        alertDialog.show();  //<-- See This!
                    }

                }
        );
        EditText editText=(EditText)findViewById(R.id.edit_text_field);
        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                switch (keyCode) {
                    case KeyEvent.KEYCODE_BACK:
                        RelativeLayout main_view=(RelativeLayout)findViewById(R.id.main_view);
                        main_view.setBackgroundColor(Color.MAGENTA);
                        v.playSoundEffect(SoundEffectConstants.CLICK);
                        return true;
                }
                return false;
            }
        });

        Button buttonGoToListView=(Button)findViewById(R.id.button_go_to_list_view);
        buttonGoToListView.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View viewId){
                        Intent INT=new Intent(MainActivity.this,ListViewActivity.class);
                        INT.putExtra("hello", "Hello");
                        startActivity(INT);
                    }

                }
        );

        textView=(TextView)findViewById(R.id.textView3);

        this.gestureDetector=new GestureDetectorCompat(this, this);







        ////////LIST_VIEW
       // final ListView listview = (ListView) findViewById(R.id.list_view);
        //String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
         //       "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
          //      "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
           //     "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
            //    "Android", "iPhone", "WindowsMobile" };

        //final ArrayList<String> list = new ArrayList<String>();
        //for (int i = 0; i < values.length; ++i) {
        //    list.add(values[i]);
        //}





    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        RelativeLayout main_view;
        switch (keyCode) {

            case KeyEvent.KEYCODE_VOLUME_DOWN:
                main_view=(RelativeLayout)findViewById(R.id.main_view);
                main_view.setBackgroundColor(Color.WHITE);
                break;
        }

        return true;

        //return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        RelativeLayout main_view=(RelativeLayout)findViewById(R.id.main_view);

        switch(item.getItemId()){
            case R.id.menu_red:
                if(item.isChecked()){
                    item.setChecked(Boolean.FALSE);
                }else{
                    item.setChecked(Boolean.TRUE);
                    main_view.setBackgroundColor(Color.RED);
                }
                return true;// true- we handled this event
            case R.id.menu_green:
                if(item.isChecked()){
                    item.setChecked(Boolean.FALSE);
                }else{
                    item.setChecked(Boolean.TRUE);
                    main_view.setBackgroundColor(Color.GREEN);
                }
                return true;// true- we handled this event
            case R.id.menu_yellow:
                if(item.isChecked()){
                    item.setChecked(Boolean.FALSE);
                }else{
                    item.setChecked(Boolean.TRUE);
                    main_view.setBackgroundColor(Color.YELLOW);
                }
                return true;// true- we handled this event
            default:
                    return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                RelativeLayout main_view=(RelativeLayout)findViewById(R.id.main_view);
                main_view.setBackgroundColor(Color.MAGENTA);
                return true;
        }
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        textView.setTextColor(Color.BLACK);
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        textView.setTextColor(Color.RED);
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        return true;
    }
}
