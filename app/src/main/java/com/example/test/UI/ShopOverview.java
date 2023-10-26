package com.example.test.UI;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.test.utils.Camera;

public class ShopOverview extends UIElement{

    private int x, y;
    private int width = 800, height = 1800;
    private Camera camera;

    private boolean show;

    private Paint paint;
    public ShopOverview(int x, int y, Camera camera) {
        this.x = x;
        this.y = y;
        this.camera = camera;

        this.show = false;

        this.paint = new Paint();
        this.paint.setColor(Color.GRAY);
    }

    @Override
    public void update(long now) {

    }

    @Override
    public void render(Canvas canvas) {
        Rect rect = new Rect(camera.TransformX(this.x), camera.TransformY(this.y), camera.TransformX(this.x+this.width), camera.TransformY(this.y+this.height));
        canvas.drawRect(rect, this.paint);
    }

    @Override
    public void event(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return;
            case MotionEvent.ACTION_MOVE:
                return;
            case MotionEvent.ACTION_UP:
                return;
        }
    }

    @Override
    public int getTX() {
        return this.camera.TransformX(this.x);
    }

    @Override
    public int getTY() {
        return this.camera.TransformY(this.y);
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }


    public void setShow(boolean x) {
        this.show = x;
    }

    public boolean getShow() {
        return this.show;
    }

}
