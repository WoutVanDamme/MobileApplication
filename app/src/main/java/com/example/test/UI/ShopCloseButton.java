package com.example.test.UI;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.test.utils.Camera;

public class ShopCloseButton extends UIElement{

    private int x, y;
    private int width = 128, height = 128;
    private Camera camera;

    private ShopOverview shopOverview;

    //TEMP
    private Paint paint;
    public ShopCloseButton(int x, int y, Camera camera, ShopOverview shopOverview) {
        this.x = x;
        this.y = y;
        this.camera = camera;

        this.paint = new Paint();
        this.paint.setColor(Color.RED);

        this.shopOverview = shopOverview;
    }

    public void update(long now) {

    }

    public void render(Canvas canvas) {
        Rect rect = new Rect(camera.TransformX(this.x), camera.TransformY(this.y), camera.TransformX(this.x+this.width), camera.TransformY(this.y+this.height));
        canvas.drawRect(rect, this.paint);
    }

    @Override
    public void event(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("test-ui", "exit");
                shopOverview.setActive(false);
                return;
            case MotionEvent.ACTION_MOVE:
                return;
            case MotionEvent.ACTION_UP:
                Log.d("test-ui", "exit");
                shopOverview.setActive(false);
                return;
        }


    }

    @Override
    public int getTX() {
        return camera.TransformX(x);
    }

    @Override
    public int getTY() {
        return camera.TransformY(y);
    }

    @Override
    public int getTRight() {
        return camera.TransformX(x+width);
    }

    @Override
    public int getTBottom() {
        return camera.TransformY(y+height);
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void setActive(boolean x) {

    }

    @Override
    public boolean isActive() {
        return true;
    }

}
