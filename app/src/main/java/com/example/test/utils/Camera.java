package com.example.test.utils;

import android.util.Log;

public class Camera {

    /*
    virtual world (1000x2000)
    to
    screen (variable size)
     */

    private int width, height;
    public Camera(int width, int height) {
        this.width = width;
        this.height = height;
    }


    public int TransformX(int X) {
        return (int)((X/1000.0) * this.width);
    }

    public int TransformY(int Y) {
        return (int)((Y/2000.0) * this.height);
    }

}
