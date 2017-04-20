package com.iucosoft.android.graphicsdrawdemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import java.util.UUID;
import android.provider.MediaStore;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    private DrawingView drawView;
    private ImageButton currPaint, drawBtn, eraseBtn, newBtn, saveBtn, linesBtn;
    private float smallBrush, mediumBrush, largeBrush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawView = (DrawingView)findViewById(R.id.drawing);
        LinearLayout paintLayout = (LinearLayout)findViewById(R.id.paint_colors);
        currPaint = (ImageButton)paintLayout.getChildAt(0);
        currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));

        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);

        drawBtn = (ImageButton)findViewById(R.id.draw_btn);

        drawBtn.setOnClickListener(this);

        drawView.setBrushSize(mediumBrush);

        eraseBtn = (ImageButton)findViewById(R.id.erase_btn);
        eraseBtn.setOnClickListener(this);

        newBtn = (ImageButton)findViewById(R.id.new_btn);
        newBtn.setOnClickListener(this);

        saveBtn = (ImageButton)findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(this);

        linesBtn = (ImageButton)findViewById(R.id.draw_lines);
        linesBtn.setOnClickListener(this);
    }

    public void paintClicked(View view){
        //use chosen color
        if(view!=currPaint){
            //update color
            ImageButton imgView = (ImageButton)view;
            String color = view.getTag().toString();

            drawView.setColor(color);

            imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
            currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
            currPaint=(ImageButton)view;
        }
        drawView.setErase(false);
        drawView.setBrushSize(drawView.getLastBrushSize());

    }

    @Override
    public void onClick(View view){
        //respond to clicks
        if(view.getId()==R.id.draw_btn){
            //draw button clicked
            final Dialog brushDialog = new Dialog(this);
            brushDialog.setTitle("Brush size:");

            brushDialog.setContentView(R.layout.brush_chooser);

            ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
            smallBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(smallBrush);
                    drawView.setLastBrushSize(smallBrush);
                    drawView.setErase(false);
                    brushDialog.dismiss();
                }
            });

            ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(mediumBrush);
                    drawView.setLastBrushSize(mediumBrush);
                    drawView.setErase(false);
                    brushDialog.dismiss();
                }
            });

            ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(largeBrush);
                    drawView.setLastBrushSize(largeBrush);
                    drawView.setErase(false);
                    brushDialog.dismiss();
                }
            });
            brushDialog.show();


        }

        else if(view.getId()==R.id.erase_btn){
            //switch to erase - choose size
            final Dialog brushDialog = new Dialog(this);
            brushDialog.setTitle("Eraser size:");
            brushDialog.setContentView(R.layout.brush_chooser);

            ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
            smallBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setErase(true);
                    drawView.setBrushSize(smallBrush);
                    brushDialog.dismiss();
                }
            });
            ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setErase(true);
                    drawView.setBrushSize(mediumBrush);
                    brushDialog.dismiss();
                }
            });
            ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setErase(true);
                    drawView.setBrushSize(largeBrush);
                    brushDialog.dismiss();
                }
            });
            brushDialog.show();
        }

        else if(view.getId()==R.id.new_btn){
            //new button
            AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
            newDialog.setTitle("New drawing");
            newDialog.setMessage("Start new drawing (you will lose the current drawing)?");
            newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    drawView.startNew();
                    dialog.dismiss();
                }
            });
            newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            newDialog.show();
        }

        else if(view.getId()==R.id.save_btn){
            //save drawing
            AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
            saveDialog.setTitle("Save drawing");
            saveDialog.setMessage("Save drawing to device Gallery?");
            saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    //save drawing
                    //enabling the drawing cache on the custom View:
                    drawView.setDrawingCacheEnabled(true);

                    String imgSaved = MediaStore.Images.Media.insertImage(
                            getContentResolver(), drawView.getDrawingCache(),
                            UUID.randomUUID().toString()+".png", "drawing");

                    if(imgSaved!=null){
                        Toast savedToast = Toast.makeText(getApplicationContext(),
                                "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
                        savedToast.show();
                    }
                    else{
                        Toast unsavedToast = Toast.makeText(getApplicationContext(),
                                "Oops! Image could not be saved.", Toast.LENGTH_SHORT);
                        unsavedToast.show();
                    }
                }
            });
            drawView.destroyDrawingCache();
            saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            saveDialog.show();
        }

        else if(view.getId()==R.id.draw_lines){
            //drawView.drawLines();


            final Dialog drawOptionsDialog = new Dialog(this);
            drawOptionsDialog.setTitle("Draw Options:");

            drawOptionsDialog.setContentView(R.layout.action_chooser);

            Button btnLine0 = (Button)drawOptionsDialog.findViewById(R.id.draw_line_0);
            btnLine0.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.drawLine0();
                    drawOptionsDialog.dismiss();
                }
            });

            Button btnLine1 = (Button)drawOptionsDialog.findViewById(R.id.draw_line_1);
            btnLine1.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.drawLine1();
                    drawOptionsDialog.dismiss();
                }
            });
            Button btnLine2 = (Button)drawOptionsDialog.findViewById(R.id.draw_line_2);
            btnLine2.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.drawLine2();
                    drawOptionsDialog.dismiss();
                }
            });
            Button btnLine3 = (Button)drawOptionsDialog.findViewById(R.id.draw_line_3);
            btnLine3.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.drawLine3();
                    drawOptionsDialog.dismiss();
                }
            });
            Button btnLine4 = (Button)drawOptionsDialog.findViewById(R.id.draw_line_4);
            btnLine4.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.drawLine4();
                    drawOptionsDialog.dismiss();
                }
            });

            Button btnRectangle=(Button)drawOptionsDialog.findViewById(R.id.draw_rectangle);
            btnRectangle.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawView.drawRectangle();
                    drawOptionsDialog.dismiss();
                }
            });
            Button btnCircle=(Button)drawOptionsDialog.findViewById(R.id.draw_circle);
            btnCircle.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawView.drawCircle();
                    drawOptionsDialog.dismiss();
                }
            });
            Button btnBezier=(Button)drawOptionsDialog.findViewById(R.id.draw_bezier_curves);
            btnBezier.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawView.drawBezierCurves();
                    drawOptionsDialog.dismiss();
                }
            });
            drawOptionsDialog.show();
        }
    }
}
//https://code.tutsplus.com/tutorials/android-sdk-create-a-drawing-app-essential-functionality--mobile-19328

//https://www.iconfinder.com/search/?q=paint&maximum=32&price=free