package com.iucosoft.android.graphicsdrawdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.TypedValue;

import java.io.File;

/**
 * Created by corina on 4/17/17.
 */
public class DrawingView extends View{



    public DrawingView(Context context, AttributeSet attrs){
        super(context, attrs);
        setupDrawing();
    }
    //drawing path
    private Path drawPath;
    //drawing and canvas paint
    private Paint drawPaint, canvasPaint;
    //initial color
    private int paintColor = 0xFF660000;
    //canvas
    private Canvas drawCanvas;
    //canvas bitmap
    private Bitmap canvasBitmap;

    private Bitmap mBitmapFromSdcard;

    private float brushSize, lastBrushSize;

    private boolean erase=false;

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

    public void setupDrawing(){

        brushSize = getResources().getInteger(R.integer.medium_size);
        lastBrushSize = brushSize;

        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);

        drawPaint.setAntiAlias(true);

        drawPaint.setStrokeWidth(brushSize);// drawPaint.setStrokeWidth(20);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    public void drawLine0(){
        drawPath.reset();

        drawPaint.setColor(lineColors[0]);
        drawPaint.setStrokeWidth(0 * 5 + 5);

        drawPath.moveTo(lineCoordinates[0].getFromX(), lineCoordinates[0].getFromY());
        drawPath.lineTo(lineCoordinates[0].getToX(), lineCoordinates[0].getToY());


    }
    public void drawLine1(){

        drawPath.reset();

        drawPaint.setColor(lineColors[1]);
        drawPaint.setStrokeWidth(1 * 5 + 5);

        drawPath.moveTo(lineCoordinates[1].getFromX(), lineCoordinates[1].getFromY());
        drawPath.lineTo(lineCoordinates[1].getToX(), lineCoordinates[1].getToY());

        invalidate();
    }

    public void drawLine2(){

        drawPath.reset();

        drawPaint.setColor(lineColors[2]);
        drawPaint.setStrokeWidth(2 * 5 + 5);

        drawPath.moveTo(lineCoordinates[2].getFromX(), lineCoordinates[2].getFromY());
        drawPath.lineTo(lineCoordinates[2].getToX(), lineCoordinates[2].getToY());

        invalidate();
    }

    public void drawLine3(){

        drawPath.reset();

        drawPaint.setColor(lineColors[3]);
        drawPaint.setStrokeWidth(3 * 5 + 5);

        drawPath.moveTo(lineCoordinates[3].getFromX(), lineCoordinates[3].getFromY());
        drawPath.lineTo(lineCoordinates[3].getToX(), lineCoordinates[3].getToY());

        invalidate();
    }

    public void drawLine4(){

        drawPath.reset();

        drawPaint.setColor(lineColors[4]);
        drawPaint.setStrokeWidth(4 * 5 + 5);

        drawPath.moveTo(lineCoordinates[4].getFromX(), lineCoordinates[4].getFromY());
        drawPath.lineTo(lineCoordinates[4].getToX(), lineCoordinates[4].getToY());

        invalidate();
    }

    public void drawRectangle(){
        drawPath.reset();
        drawPaint.setColor(Color.parseColor("#660033"));
       // drawPaint.setShader(new LinearGradient(300, 450, 600, 600, Color.RED, Color.WHITE, Shader.TileMode.MIRROR));

        int[] rainbow = getRainbowColors();
        Shader shader = new LinearGradient(300,450,600,600, rainbow,
                null, Shader.TileMode.MIRROR);

        drawPaint.setShader(shader);
        drawPaint.setStyle(Paint.Style.FILL);
        drawPath.addRect(300,450, 600,600,Path.Direction.CCW);
        invalidate();
    }

    private int[] getRainbowColors() {
        return new int[] {
                Color.RED,
                Color.YELLOW,
                Color.GREEN,
                Color.BLUE,
                Color.MAGENTA
        };
    }

    public void drawCircle(){
        drawPath.reset();
        drawPaint.setColor(Color.YELLOW);
        drawPath.addCircle(100,550,100, Path.Direction.CCW);
        invalidate();
    }



    public void drawLines(){
        for (int i = 0; i < 5; i++) {
            drawPaint.setColor(lineColors[i]);
            drawPaint.setStrokeWidth(i * 5 + 5);

            drawPath.moveTo(lineCoordinates[i].getFromX(), lineCoordinates[i].getFromY());
            drawPath.lineTo(lineCoordinates[i].getToX(), lineCoordinates[i].getToY());

            drawCanvas.drawPath(drawPath, drawPaint);
        }
        //
        //drawPath.reset();
        invalidate();
    }

    public void drawBezierCurves(){
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeWidth(10);
       // Path path = new Path();
        drawPaint.setColor(Color.RED);
        drawPath.moveTo(334, 159);
        drawPath.cubicTo(368, 51, 586, 250, 636, 152);
       // drawCanvas.drawPath(path, drawPaint);

        drawPath.moveTo(334, 359);
        drawPath.cubicTo(368, 300, 600, 500, 650, 350);
      //  drawCanvas.drawPath(path, drawPaint);


    }

    public void setBrushSize(float newSize){
        //update size
        float pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                newSize, getResources().getDisplayMetrics());
        brushSize=pixelAmount;
        drawPaint.setStrokeWidth(brushSize);
    }

    public void startNew(){
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //view given size
        super.onSizeChanged(w, h, oldw, oldh);

        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw view
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);

        /////////DRAW BITMAP/////////////////////
        String imageName="/storage/emulated/0/storage/emulated/0/mario.png";
        File sd = Environment.getExternalStorageDirectory();
        File image = new File(sd, imageName);
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        mBitmapFromSdcard = BitmapFactory.decodeFile(imageName,bmOptions);
        drawCanvas.drawBitmap(mBitmapFromSdcard, 10, 900, null);



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //detect user touch
        float touchX = event.getX();
        float touchY = event.getY();
        drawPaint.setShader(null);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }

    public void setColor(String newColor){
        //set color
        invalidate();
        paintColor = Color.parseColor(newColor);
        drawPaint.setColor(paintColor);

    }

    public void setErase(boolean isErase){
        //set erase true or false
        erase=isErase;
        if(erase) drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        else drawPaint.setXfermode(null);

    }

    public void setLastBrushSize(float lastSize){
        lastBrushSize=lastSize;
    }
    public float getLastBrushSize(){
        return lastBrushSize;
    }




}
