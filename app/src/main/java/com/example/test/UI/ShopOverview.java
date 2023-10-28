package com.example.test.UI;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.test.utils.Camera;

import java.util.ArrayList;

public class ShopOverview extends UIElement{

    private int x, y;
    private int width = 800, height = 1800;
    private Camera camera;

    private boolean active = false;

    private ArrayList<UIElement> elements;


    private Paint paint;
    public ShopOverview(int x, int y, Camera camera) {
        this.x = x;
        this.y = y;
        this.camera = camera;


        this.paint = new Paint();
        this.paint.setColor(Color.GRAY);

        this.elements = new ArrayList<>();
        this.elements.add(new ShopCloseButton(this.x+this.width-128, this.y,camera, this));

    }

    @Override
    public void update(long now) {

    }

    @Override
    public void render(Canvas canvas) {
        Rect rect = new Rect(camera.TransformX(this.x), camera.TransformY(this.y), camera.TransformX(this.x+this.width), camera.TransformY(this.y+this.height));
        canvas.drawRect(rect, this.paint);
        for(UIElement elem: elements) {
            if(elem.isActive())
                elem.render(canvas);
        }
    }

    @Override
    public void event(MotionEvent event) {
        Log.d("test-ui", "testing");

        for(UIElement element: elements) {

            int x = (int)event.getX();
            int y = (int)event.getY();

            if(element.isActive() && x > element.getTX() && x < element.getTX() + element.getWidth() && y > element.getTY() && y < element.getTY()+element.getHeight()) {
                element.event(event);
            }
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

    @Override
    public void setActive(boolean x) {
        active=x;
    }

    @Override
    public boolean isActive() {
        return active;
    }




}
