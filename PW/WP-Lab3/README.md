
**Implemented Tasks Description:**

1. An Android Graphics Application has been created. The Main activity of the application consists of LinearLayout. In this layout had been added another LinearLayout for the upper 'menu bar' and another for the lower 'menu bar'. between 2 'menu bars' has been added  a drawing view Object: com.iucosoft.android.graphicsdrawdemo.DrawingView. Drawing view is a custom class, which extends View, and represents the area available for drawing. 

2. While working with Android Graphics, the drawing action happens on a Canvas. Paint class defines the style of drawing: color, fill/stroke, strokeWidth, shader, etc. Another important class is Path, this class provides methods like moveTo(), pathTo(), addRect, addCircle, etc.

3. A touch-related functionality that has been created implies drawing using touch coordinates as we draw in paint using a brush. A menu of colors had been added under the canvas, where we can select the color we want to draw with. Also we can select between 3 available brush size.
In order to draw on touch has been overrided onTouchEvent. From the event we get the touch coordinates. 3 MotionEvents has to be processed: MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP.

4.  Use mouse(touch) as an eraser. In the menu above the canvas has been added erase button. On this button is opened an Alert Dialog which provides a menu of 3 different size of the eraser.
In order to activate the eraser the following method has been implemented
```java
  public void setErase(boolean isErase){
        //set erase true or false
        erase=isErase;
        if(erase) drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        else drawPaint.setXfermode(null);
    }
```

5. Clear canvas button has been added to the menu which provides the possibility to clear the entire canvas, of course asking for a confirmation.
```java
public void startNew(){
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }
```

6. Save image to gallery is another implemented feature. Using MediaStore.Images.Media.insertImage(getContentResolver(), drawView.getDrawingCache(), UUID.randomUUID().toString()+".png", "drawing") method the chached image is saved to the specified folder.

