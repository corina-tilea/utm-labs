package com.iucosoft.android.eventdriven_lab3_corina;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public int []lineColors={Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA, Color.CYAN};
    public static LineCoordinates[] lineCoordinates;
    static{
        lineCoordinates=new LineCoordinates[5];
        int xStart=10;
        int yStart=10;
        int yEnd=400;
        for (int i = 0; i < 5; i++) {
            LineCoordinates lC=new LineCoordinates(xStart,yStart,xStart,yEnd);
            xStart+=60;
            lineCoordinates[i]=lC;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(new MyDrayView(this));
    }

    public class MyDrayView extends View {

        public MyDrayView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int width=getWidth();
            int heigh=getHeight();
            Paint paint=new Paint();
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawPaint(paint);

            for (int i = 0; i < 5; i++) {
                paint.setColor(lineColors[i]);
                paint.setStrokeWidth(i*5+5);
                canvas.drawLine(lineCoordinates[i].getFromX(),lineCoordinates[i].getFromY(), lineCoordinates[i].getToX(), lineCoordinates[i].getToY(),paint);
            }
            //Draw Bezier Curve
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            Path path = new Path();
            paint.setColor(Color.RED);
            path.moveTo(334, 159);
            path.cubicTo(368, 51, 586, 250, 636, 152);
            canvas.drawPath(path, paint);

            path.moveTo(334, 359);
            path.cubicTo(368, 300, 600, 500, 650, 350);
            canvas.drawPath(path, paint);

            //Draw Plane Objects
            paint.setColor(Color.YELLOW);
            canvas.drawCircle(100,550,100,paint);

            paint.setColor(Color.parseColor("#660033"));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(300,450, 600,600,paint);

            paint.setColor(Color.parseColor("#00ff99"));
            PointF[] points ={
                new PointF(650, 450),
                        new PointF(650, 600),
                        new PointF(620, 790),
                        new PointF(650, 450)

            };
           // float points[]={600,450,650,500,620,390,600,450};

             path = new Path();
            // Set the first point, that the drawing will start from.
            path.moveTo(points[0].x, points[0].y);
            for (int i = 1; i < points.length; i++){
                // Draw a line from the previous point in the path to the new point.
                path.lineTo(points[i].x, points[i].y);
            }
            canvas.drawPath(path, paint);

        }
    }
}
