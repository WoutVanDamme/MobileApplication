package com.example.test.GameObjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.test.utils.Camera;

public class House extends GameObject{

    private int x, y;
    private int capacity;
    private Camera camera;

// TEMP
    private int width = 256, height = 256;
    private Paint paint;

    public House(int x, int y, int capacity, Camera camera) {
        this.x = x;
        this.y = y;
        this.capacity = capacity;
        this.paint = new Paint();
        this.paint.setColor(Color.rgb(200, 150, 50));

        this.camera = camera;
    }


    @Override
    public void update(long now) {
    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawRect(new Rect(camera.TransformX(this.x), camera.TransformY(this.y), camera.TransformX(this.x+this.width), camera.TransformY(this.y+this.height)), this.paint);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCapacity() {
        return capacity;
    }
}
