package com.iucosoft.android.eventdrivelab4;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.location.Location;
import android.support.v4.util.Pair;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Corina on 4/16/17.
 */

public class CustomView extends View implements Runnable{
    // CONSTANTS
    final static int CIRCLE_SHAPE=1;
    final int SQUARE_SHAPE=2;
    
    
    static int shape1=1, shape2=1, shape3=1;
    
    Paint paint;
    public static ArrayList<Ball> ballsList=new ArrayList<>();
    public static ArrayList<Ball> newCreatedBallsList=new ArrayList<>();
    public static List<Square> squaresList=new ArrayList<>();
    
    private int[] colors;
    int screenWidth;
    int screenHeight;
    
    int ballRadius=100;
    float x1,x2,y1,y2,x3,y3;


    public void initBallsList(){
        int violet=Color.parseColor("#390251");
        ballsList.add(new Ball(new Coordinates(100,150),1,10,violet));
        ballsList.add(new Ball(new Coordinates(300,150),1,5,Color.MAGENTA));
        ballsList.add(new Ball(new Coordinates(400,300),4,1,Color.GREEN));
    }

    public CustomView(Context context, AttributeSet attrs){
        super(context, attrs);
        
        setFocusable(true);
        setFocusableInTouchMode(true);
        
        setup();
    }


    public void setup(){
        colors=new int[]{Color.GREEN,Color.BLUE,Color.RED,Color.MAGENTA,Color.CYAN,Color.BLACK,
                         Color.DKGRAY,Color.GRAY,Color.YELLOW, Color.LTGRAY};

        initBallsList();

        screenWidth=getScreenWidth();
        screenHeight=getScreenHeight();

        //Init Paint Object Used later to draw
        paint=new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.MITER);
        paint.setStrokeCap(Paint.Cap.SQUARE);

    }


    @Override
    protected void onDraw(Canvas canvas) {

        double distance_1_2,distance_2_3,distance_1_3;

                Ball ball1=ballsList.get(0);
                Ball ball2=ballsList.get(1);
                Ball ball3=ballsList.get(2);

                Square square1=null;
                Square square2=null;
                Square square3=null;

                //Iterate The Balls LIST

                 for (int i = 0; i < 3; i++) {
                    Ball ballObject=ballsList.get(i);

                    if(ballObject.getShape()==CIRCLE_SHAPE){
                        paint.setColor(ballObject.getColor());
                        paint.setStrokeWidth(5);
                        paint.setStyle(Paint.Style.FILL);

                        int posX=ballObject.getCoordinates().getxAxisPosition();
                        int posY=ballObject.getCoordinates().getyAxisPosition();

                        canvas.drawCircle(posX,posY,ballRadius, paint);

                        checkBounds(ballObject);

                        posX=ballObject.getCoordinates().getxAxisPosition();
                        posY=ballObject.getCoordinates().getyAxisPosition();

                        checkForInversion(posX,posY,ballObject);
                        checkForTouchingTopBottomBounds(posY,ballObject);

                        if(ballObject.getCoordinates().getxAxisPosition()<=ballRadius || ballObject.getCoordinates().getxAxisPosition()>=screenWidth-ballRadius){

                            paint.setColor(ballObject.getColor());
                            paint.setStrokeWidth(5);
                            paint.setStyle(Paint.Style.FILL);
                            int lastPosX=ballObject.getCoordinates().getxAxisPosition();
                            int lastPosY=ballObject.getCoordinates().getyAxisPosition();
                            if(i==0) {
                                shape1 = SQUARE_SHAPE;
                                square1=new Square(lastPosX-ballRadius,lastPosY-ballRadius,lastPosX+ballRadius,lastPosY+ballRadius);
                                canvas.drawRect(square1.getLeft(),square1.getTop(),square1.getRight(),square1.getBottom(),paint);
                            }else if(i==1){
                                shape2 = SQUARE_SHAPE;
                                square2=new Square(lastPosX-ballRadius,lastPosY-ballRadius,lastPosX+ballRadius,lastPosY+ballRadius);
                                canvas.drawRect(square2.getLeft(),square2.getTop(),square2.getRight(),square2.getBottom(),paint);
                            }else{
                                shape3 = SQUARE_SHAPE;
                                square3=new Square(lastPosX-ballRadius,lastPosY-ballRadius,lastPosX+ballRadius,lastPosY+ballRadius);
                                canvas.drawRect(square3.getLeft(),square3.getTop(),square3.getRight(),square3.getBottom(),paint);
                            }
                            ballObject.setShape(SQUARE_SHAPE);
                            invalidate();
                            paint.reset();
                        }
                        invalidate();
                        paint.reset();
                    }
                     if(i==0) {
                         ball1=ballObject;
                     }else if(i==1){
                         ball2=ballObject;
                     }else{
                         ball3=ballObject;
                     }

                }

            x1=ballsList.get(0).getCoordinates().getxAxisPosition();
            y1=ballsList.get(0).getCoordinates().getyAxisPosition();
            x2=ballsList.get(1).getCoordinates().getxAxisPosition();
            y2=ballsList.get(1).getCoordinates().getyAxisPosition();
            x3=ballsList.get(2).getCoordinates().getxAxisPosition();
            y3=ballsList.get(2).getCoordinates().getyAxisPosition();
            distance_1_2=Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
            distance_2_3=Math.sqrt(Math.pow(x3-x2,2)+Math.pow(y3-y2,2));
            distance_1_3=Math.sqrt(Math.pow(x3-x1,2)+Math.pow(y3-y1,2));

            checkTransformations(distance_1_2, distance_2_3 ,distance_1_3);


        if(shape1==SQUARE_SHAPE){
            getCoordsAndDraw(ball1,paint,canvas);
        }

        if(shape2==SQUARE_SHAPE){
            getCoordsAndDraw(ball2,paint,canvas);
        }

        if(shape3==SQUARE_SHAPE){
            getCoordsAndDraw(ball3,paint,canvas);
        }

                if(!newCreatedBallsList.isEmpty()){
                    for (Ball ballObject : newCreatedBallsList){
                        if(ballObject.getShape()==CIRCLE_SHAPE) {
                            paint.setColor(Color.parseColor("#f4e542"));
                            paint.setStrokeWidth(5);
                            paint.setStyle(Paint.Style.FILL);

                            int posX = ballObject.getCoordinates().getxAxisPosition();
                            int posY = ballObject.getCoordinates().getyAxisPosition();
                            canvas.drawCircle(posX, posY, ballRadius, paint);
                            checkBounds(ballObject);
                            posX = ballObject.getCoordinates().getxAxisPosition();
                            posY = ballObject.getCoordinates().getyAxisPosition();
                            checkForInversion(posX, posY, ballObject);
                            checkForTouchingTopBottomBounds(posY, ballObject);
                        }

                    }

                }



    }

    private void getCoordsAndDraw(Ball ball, Paint paint, Canvas canvas){
        paint.setColor(ball.getColor());
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.FILL);
        int lastPosX=ball.getCoordinates().getxAxisPosition();
        int lastPosY=ball.getCoordinates().getyAxisPosition();
        canvas.drawRect(lastPosX-ballRadius,lastPosY-ballRadius,lastPosX+ballRadius,lastPosY+ballRadius,paint);
        invalidate();
        paint.reset();
    }


    private void checkBounds(Ball ball) {
        int nextY=ball.getCoordinates().getyAxisPosition()+ ball.getStepSizeY();
        int nextX=ball.getCoordinates().getxAxisPosition() + ball.getStepSizeX();
        if(nextY<screenHeight-ballRadius && nextY>ballRadius){
            ball.getCoordinates().setyAxisPosition(nextY);
            ball.getCoordinates().setxAxisPosition(nextX);
        } else {
            ball.setStepSizeY(-ball.getStepSizeY());
            ball.setStepSizeX(-ball.getStepSizeX());
        }
    }
    private void setSizeWithRandom(Ball ball1, Ball ball2){
        Random random=new Random();
        int pos=random.nextInt(10);

        ball1.setColor(colors[pos]);
        ball1.setStepSizeY(invertStepSign(ball1.getStepSizeY()));

        pos=random.nextInt(10);
        ball2.setColor(colors[pos]);
        ball2.setStepSizeY(invertStepSign(ball2.getStepSizeY()));
    }

    private void createAddNewBalls(Ball ball1, Ball ball2){
        Coordinates newCoordinates1=new Coordinates(ball1.getCoordinates().getxAxisPosition(),ball1.getCoordinates().getyAxisPosition());
        newCreatedBallsList.add(new Ball(newCoordinates1,3,ball1.getStepSizeY(), Color.RED));

        Coordinates newCoordinates2=new Coordinates(ball2.getCoordinates().getxAxisPosition(),ball2.getCoordinates().getyAxisPosition());
        newCreatedBallsList.add(new Ball(newCoordinates2,3,ball2.getStepSizeY(), Color.RED));
    }

    private void checkTransformations(double distance_1_2, double distance_2_3, double distance_1_3) {
        if(shape1==CIRCLE_SHAPE && shape2==CIRCLE_SHAPE && distance_1_2<2*ballRadius){

                Ball ball1=ballsList.get(0);
                Ball ball2=ballsList.get(1);

                setSizeWithRandom(ball1, ball2);
                createAddNewBalls(ball1, ball2);


        } else if(shape2==CIRCLE_SHAPE && shape3==CIRCLE_SHAPE && distance_2_3<2*ballRadius){

                Ball ball2=ballsList.get(1);
                Ball ball3=ballsList.get(2);
                setSizeWithRandom(ball2, ball3);
                createAddNewBalls(ball2, ball3);

        } else if(shape1==CIRCLE_SHAPE && shape3==CIRCLE_SHAPE && distance_1_3<2*ballRadius){
                Ball ball1=ballsList.get(0);
                Ball ball3=ballsList.get(2);

                setSizeWithRandom(ball1, ball3);
                createAddNewBalls(ball1, ball3);
        }

    }

    public void checkForInversion(int posX, int posY , Ball ball){
        int invertedX=0;
        int invertedY=0;
        if(posX>(screenWidth-ballRadius) || posY>(screenHeight-ballRadius)){
            invertedX=invertStepSign(ball.getStepSizeX());
            invertedY=invertStepSign(ball.getStepSizeY());
            ball.setStepSizeX(invertedX);
            ball.setStepSizeY(invertedY);
        }

        if(posX<ballRadius || posY<ballRadius){
            invertedX=invertStepSign(ball.getStepSizeX());
            invertedY=invertStepSign(ball.getStepSizeY());
            ball.setStepSizeX(invertedX);
            ball.setStepSizeY(invertedY);
        }

    }

    public void checkForTouchingTopBottomBounds(int posY ,Ball ball){
        if (posY<=ballRadius+10 || posY>=screenHeight-ballRadius+10){
            int stepX=ball.getStepSizeX();
            int stepY=ball.getStepSizeY();
            if(stepX>0 && stepY>0){
                ball.setStepSizeY(stepY+5);
            } else{
                ball.setStepSizeY(stepY-5);
            }
        }

    }

    public int invertStepSign(int step) {
            step=-step;
        return step;
    }

    @Override
    public void run() {
        try
        {
            Thread.sleep(1000, 1);
        }catch(InterruptedException e){}
    }



    public int getScreenWidth(){
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public int getScreenHeight(){
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Ball ball1=ballsList.get(0);
        Ball ball2=ballsList.get(1);
        Ball ball3=ballsList.get(2);
        switch(keyCode){
            case KeyEvent.KEYCODE_VOLUME_UP:
                for(Ball ball: ballsList){
                    if(ball.getStepSizeY()>0){
                        ball.setStepSizeY(ball.getStepSizeY()+10);
                    } else {
                        ball.setStepSizeY(ball.getStepSizeY()-10);
                    }
                }
                if(!newCreatedBallsList.isEmpty()){
                    for(Ball ball : newCreatedBallsList){
                        if(ball.getStepSizeY()>0){
                            ball.setStepSizeY(ball.getStepSizeY()+10);
                        } else {
                            ball.setStepSizeY(ball.getStepSizeY()-10);
                        }
                    }
                }
                break;
            case KeyEvent.KEYCODE_VOLUME_DOWN:

                for(Ball ball: ballsList){
                    if(ball.getStepSizeY()>0 && ball.getStepSizeY()-10>0){
                        ball.setStepSizeY(ball.getStepSizeY()-10);
                    } else {
                        ball.setStepSizeY(ball.getStepSizeY()+10);
                    }
                }

                if(!newCreatedBallsList.isEmpty()){
                    for(Ball ball : newCreatedBallsList){
                        if(ball.getStepSizeY()>0 && ball.getStepSizeY()-10>0){
                            ball.setStepSizeY(ball.getStepSizeY()-10);
                        } else {
                            ball.setStepSizeY(ball.getStepSizeY()+10);
                        }
                    }
                }
                break;
        }

        return super.onKeyUp(keyCode, event);
    }

}
