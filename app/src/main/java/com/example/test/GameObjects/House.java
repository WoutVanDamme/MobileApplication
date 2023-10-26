package com.example.test.GameObjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class House extends GameObject{

    private int x, y;
    private int capacity;

// TEMP
    private int width = 256, height = 256;
    private Paint paint;

    public House(int x, int y, int capacity) {
        this.x = x;
        this.y = y;
        this.capacity = capacity;
        this.paint = new Paint();
        this.paint.setColor(Color.rgb(200, 150, 50));
    }


    @Override
    public void update() {
    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawRect(new Rect(this.x, this.y, this.x+this.width, this.y+this.height), this.paint);
    }
}
