package com.iucosoft.android.eventdrivelab4;

/**
 * Created by Corina on 4/26/17.
 */

public class Square {
    private int right;
    private int top;
    private int left;
    private int bottom;

    public Square(int right, int top, int left, int bottom) {
        this.right = right;
        this.top = top;
        this.left = left;
        this.bottom = bottom;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }
}
