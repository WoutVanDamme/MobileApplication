package com.example.test.UI;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;

import com.example.test.Game;
import com.example.test.R;
import com.example.test.utils.Camera;

public class ShopButton extends UIElement{

    private int x, y;
    private int width = 200, height = 200;
    private Camera camera;

    private ShopOverview shopOverview;
    private Game game;

    private Bitmap bMap;
    private Drawable d;

    //TEMP
    private Paint paint;
    public ShopButton(int x, int y, Camera camera, ShopOverview shopOverview, Game game) {
        this.x = x;
        this.y = y;
        this.camera = camera;

        this.paint = new Paint();
        this.paint.setColor(Color.GREEN);

        this.shopOverview = shopOverview;
        this.game = game;

        this.bMap = BitmapFactory.decodeResource(game.getContext().getResources(), R.drawable.shop);
        this.d = new BitmapDrawable(game.getContext().getResources(), bMap);

    }

    public void update(long now) {

    }

    public void render(Canvas canvas) {

        if(!shopOverview.isActive()) {
            Rect rect = new Rect(camera.TransformX(this.x), camera.TransformY(this.y), camera.TransformX(this.x+this.width), camera.TransformY(this.y+this.height));
            canvas.drawRect(rect, this.paint);

            d.setBounds(rect);
            d.draw(canvas);

        }




    }

    @Override
    public void event(MotionEvent event) {
        Log.d("test-ui", "shop button event {overview: "+ shopOverview.isActive() + "}");

        if(!shopOverview.isActive()) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.d("test-ui", "open shop overview");
                    shopOverview.setActive(true);
                    return;
                case MotionEvent.ACTION_MOVE:
                    return;
                case MotionEvent.ACTION_UP:
                    //Log.d("test-ui", "open shop overview");
                    //shopOverview.setActive(true);
                    return;
            }
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
