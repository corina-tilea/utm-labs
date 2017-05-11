package com.iucosoft.android.eventdrivelab4;

/**
 * Created by Corina on 4/26/17.
 */

public class Coordinates {
    private int xAxisPosition;
    private int yAxisPosition;
    private int initialX;
    private int initialY;

    public Coordinates(int xAxisPosition, int yAxisPosition) {
        this.xAxisPosition = xAxisPosition;
        this.yAxisPosition = yAxisPosition;
        this.initialX=xAxisPosition;
        this.initialY=yAxisPosition;

    }
    public Coordinates(){
        this.xAxisPosition =0;
        this.yAxisPosition =0;
        this.initialX=0;
        this.initialY=0;
    }

    public int getxAxisPosition() {
        return xAxisPosition;
    }

    public void setxAxisPosition(int xAxisPosition) {
        this.xAxisPosition = xAxisPosition;
    }

    public int getyAxisPosition() {
        return yAxisPosition;
    }

    public void setyAxisPosition(int yAxisPosition) {
        this.yAxisPosition = yAxisPosition;
    }

    public int getInitialX() {
        return initialX;
    }

    public int getInitialY() {
        return initialY;
    }
}
