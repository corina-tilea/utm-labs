package com.iucosoft.android.eventdrivenlab1;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String MY_VIOLET_COLOR="#240226";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create relativeLayout for this activity
        RelativeLayout mainActivityLayout=new RelativeLayout(this);

        /////////////////////////// Components ///////////////////////////////////
        //Create Text component
        TextView textView=new TextView(this);
        textView.setId(1);
        textView.setText("Done with Pride and Prejudice by Corina Tilea!");
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        textView.setGravity(Gravity.CENTER);

        //Here is The Default Text definition
        final TextView textViewDefault=new TextView(this);
        textViewDefault.setId(4);
        textViewDefault.setText("Default Style Text");
        textViewDefault.setGravity(Gravity.CENTER);

        //Here is The Default Text definition
        final TextView textViewCustom=new TextView(this);
        textViewCustom.setId(5);
        textViewCustom.setText("Custom Style Text");
        textViewCustom.setTextColor(Color.BLACK);
        textViewCustom.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28);
        textViewCustom.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
        textViewCustom.setGravity(Gravity.CENTER);
        textViewCustom.setTextColor(Color.parseColor(MY_VIOLET_COLOR));


        Button buttonDefault=new Button(this);
        buttonDefault.setId(2);
        buttonDefault.setText("Default Btn");

        Button buttonCustom=new Button(this);
        buttonCustom.setId(3);
        buttonCustom.setText("Custom Btn");
        buttonCustom.setTextColor(Color.WHITE);
        buttonCustom.setBackgroundColor(Color.parseColor(MY_VIOLET_COLOR));
        buttonCustom.setPadding(15,15,15,15);
        buttonCustom.setTypeface(Typeface.DEFAULT_BOLD);

        //via this get information about device dimenstions height/width
        Resources resourcesRef=getResources();
        int customBtnWidthPx=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 260,
                resourcesRef.getDisplayMetrics());

        buttonCustom.setWidth(customBtnWidthPx);



        //////////////////////Relative Layouts//////////////////////////////

        //Create the Relative Layout for TextView - Pride and Prejudice
        RelativeLayout.LayoutParams textViewDetails=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        textViewDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        textViewDetails.addRule(RelativeLayout.CENTER_VERTICAL);

        //Create the Relative Layout Default Button
        RelativeLayout.LayoutParams btnDefaultDetails=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        btnDefaultDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        btnDefaultDetails.addRule(RelativeLayout.CENTER_VERTICAL);
        btnDefaultDetails.addRule(RelativeLayout.BELOW, textView.getId());
        btnDefaultDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        btnDefaultDetails.setMargins(0, 50, 0, 0);

        //Create the Relative Layout Custom Button
        RelativeLayout.LayoutParams btnCustomDetails=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        btnCustomDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        btnCustomDetails.addRule(RelativeLayout.CENTER_VERTICAL);
        btnCustomDetails.addRule(RelativeLayout.BELOW, textView.getId());
        btnCustomDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        btnCustomDetails.setMargins(0, 200, 0, 0);

        //Create the Relative Layout Text View Default
        RelativeLayout.LayoutParams textViewDefaultDetails=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        textViewDefaultDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        textViewDefaultDetails.addRule(RelativeLayout.CENTER_VERTICAL);
        textViewDefaultDetails.addRule(RelativeLayout.ABOVE, textView.getId());
        textViewDefaultDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        textViewDefaultDetails.setMargins(0,100,0,100);

        //Create the Relative Layout Text View Custom
        RelativeLayout.LayoutParams textViewCustomDetails=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        textViewCustomDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        textViewCustomDetails.addRule(RelativeLayout.CENTER_VERTICAL);
        textViewCustomDetails.addRule(RelativeLayout.ABOVE, textViewDefault.getId());
        textViewCustomDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        textViewCustomDetails.setMargins(0,30,0,30);





        /////////////Add Component to the layout, with specific relative Layout////////////
        mainActivityLayout.addView(textView, textViewDetails);
        mainActivityLayout.addView(textViewDefault,textViewDefaultDetails);
        mainActivityLayout.addView(textViewCustom,textViewCustomDetails);
        mainActivityLayout.addView(buttonDefault, btnDefaultDetails);
        mainActivityLayout.addView(buttonCustom, btnCustomDetails);
        setContentView(mainActivityLayout);


        ////////////////Event Listeners///////////////////////////
        buttonCustom.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View viewId){
                        textViewCustom.setTextColor(Color.RED);
                        textViewCustom.setText("Good Job, The Text Has Been Changed!");
                    }

                }
        );

       textViewDefault.setOnLongClickListener(
               new TextView.OnLongClickListener(){
                   public boolean onLongClick(View viewId){
                       textViewDefault.setText("");
                       return true;
                   }
               }
       );

    }


}


