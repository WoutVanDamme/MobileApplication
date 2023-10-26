package com.example.test;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameLoop extends Thread{

    private Game game;
    private SurfaceHolder surfaceHolder;

    private boolean isRunning = false;
    private double averageUPS;
    private double averageFPS;

    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.game = game;
        this.surfaceHolder = surfaceHolder;

        this.averageFPS = 0;
        this.averageUPS = 0;
    }

    public double getAverageUPS() {
        return averageUPS;
    }

    public double getAverageFPS() {
        return averageFPS;
    }


    public void startLoop() {
        isRunning = true;
        start();
    }

    @Override
    public void run() {
        super.run();

        int updateCount = 0;
        int frameCount = 0;

        long elapsedTime = System.currentTimeMillis();
        long startTime = System.currentTimeMillis();
        long now;

        Canvas canvas;
        while(isRunning) {

            try {
                canvas = surfaceHolder.lockCanvas();

                updateCount++;
                game.update();

                frameCount++;
                game.draw(canvas);

                surfaceHolder.unlockCanvasAndPost(canvas);

            }catch(IllegalArgumentException e) {
                e.printStackTrace();
            }




            now = System.currentTimeMillis();
            elapsedTime = now - startTime;
            if (elapsedTime >= 1000) {
                averageFPS = frameCount / (1E-3 * elapsedTime);
                averageUPS = updateCount / (1E-3 * elapsedTime);
                frameCount = 0;
                updateCount = 0;
                startTime = now;
            }
        }
    }
}
