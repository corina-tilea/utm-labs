
**Implemented Tasks Description:**

1. An Android Graphics Application has been created. The Main activity of the application consists of a CustomView class Which extends View and Runnable. In CustomView has been implemented onDraw metdhod. In this method is implemented the Drawing algorithm for the animation.
```java
    @Override
    protected void onDraw(Canvas canvas) {
	/////implimentation
    } 
```

2. Increasing and Decreasing animation speed was implemented using Volume_Up_Key.
```java
   @Override
   public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_VOLUME_UP:
```

3. 3 animated objects had been added which interact with each other. Balls that have different velocity and moving angles. In order to work with balls, a Ball class had Been created.

4. On interaction of balls with each other because they are of the same class(Circle), they change their colors.

5. On interaction with the right and left wall (the margins of the window), balls are transformed into squares.

6. On interaction with the top and bottom of the window - the figures  increase their velocity.


