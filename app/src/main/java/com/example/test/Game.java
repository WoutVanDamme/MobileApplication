package com.example.test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.test.GameObjects.GameObject;
import com.example.test.GameObjects.House;
import com.example.test.GameObjects.WoodChucker;
import com.example.test.UI.ShopButton;
import com.example.test.UI.ShopCloseButton;
import com.example.test.UI.ShopOverview;
import com.example.test.UI.UIElement;
import com.example.test.utils.Camera;

import java.util.ArrayList;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private Long wood;

    private GameLoop gameLoop;
    private Context context;

    private int width, height;

    private Camera camera;
    private ArrayList<UIElement> uiElements;
    private ArrayList<House> houses;
    private ArrayList<WoodChucker> woodChuckers;

    public Game(Context context) {
        super(context);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        this.context = context;
        this.gameLoop = new GameLoop(this, surfaceHolder);

        setFocusable(true);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        this.width = displayMetrics.widthPixels;
        this.height = displayMetrics.heightPixels;

        // init

        this.wood = 0l;

        this.camera = new Camera(width, height);

        this.uiElements = new ArrayList<>();
        this.houses = new ArrayList<>();
        this.woodChuckers = new ArrayList<>();

        ShopOverview shopOverview = new ShopOverview(100, 100, camera);
        ShopButton shopButton = new ShopButton(100, 1800, camera, shopOverview);

        uiElements.add(shopButton);
        uiElements.add(shopOverview);


        houses.add(new House(700, 1500, 10, camera, woodChuckers, this));
    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }


    public void update(long now) {

        for(GameObject woodChucker: woodChuckers) {
            woodChucker.update(now);
        }

    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);


        for(GameObject woodChucker: woodChuckers) {
            woodChucker.render(canvas);
        }


        for(GameObject house: houses) {
            house.render(canvas);
        }

        for(UIElement element: uiElements) {
            if(element.isActive()){
                element.render(canvas);
            }
        }

        drawWood(canvas);
        drawUPS(canvas);
        drawFPS(canvas);
        drawRes(canvas);
    }


    public void drawWood(Canvas canvas) {
        Paint paint = new Paint();
        int color = Color.WHITE;
        paint.setColor(color);
        paint.setTextSize(70);
        canvas.drawText("Wood: " + this.wood, width/2, 90, paint);
    }
    public void drawRes(Canvas canvas) {
        Paint paint = new Paint();
        int color = Color.RED;
        paint.setColor(color);
        canvas.drawText("Res: " + this.width + "x"+this.height, 100, 100, paint);
    }

    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = Color.RED;
        paint.setColor(color);
        canvas.drawText("UPS: " + averageUPS, 100, 20, paint);
    }

    public void drawFPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = Color.RED;
        paint.setColor(color);
        canvas.drawText("FPS: " + averageUPS, 100, 60, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Log.d("test-ui", "registered touch event");
        for(UIElement element: uiElements) {


            int x = (int)event.getX();
            int y = (int)event.getY();

            if(element.isActive() && x > element.getTX() && x < element.getTX() + element.getWidth() && y > element.getTY() && y < element.getTY()+element.getHeight()) {
                Log.d("test-ui", "event on active uiElement");
                element.event(event);
            }

        }
        return super.onTouchEvent(event);
    }

    public Long getWood() {
        return wood;
    }

    public void setWood(Long wood) {
        this.wood = wood;
    }
}
