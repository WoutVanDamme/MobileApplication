package com.example.test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.test.GameObjects.GameObject;
import com.example.test.GameObjects.House;
import com.example.test.GameObjects.WoodChucker;
import com.example.test.utils.Camera;

import java.util.ArrayList;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private GameLoop gameLoop;
    private Context context;

    private int width, height;

    private Camera camera;
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

        this.camera = new Camera(width, height);

        this.houses = new ArrayList<>();
        this.woodChuckers = new ArrayList<>();

        houses.add(new House(700, 1500, 5));
        woodChuckers.add(new WoodChucker(700, 1500, houses.get(0), this.camera));

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


    public void update() {

        for(GameObject woodChucker: woodChuckers) {
            woodChucker.update();
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



        drawUPS(canvas);
        drawFPS(canvas);
        drawRes(canvas);
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
}
