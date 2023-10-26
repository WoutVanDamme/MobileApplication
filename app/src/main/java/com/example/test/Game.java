package com.example.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private GameLoop gameLoop;
    private Context context;
    public Game(Context context) {
        super(context);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        this.context = context;
        this.gameLoop = new GameLoop(this, surfaceHolder);

        setFocusable(true);
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

    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);
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
