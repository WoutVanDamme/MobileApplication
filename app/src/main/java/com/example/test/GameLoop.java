package com.example.test;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameLoop extends Thread{

    private boolean running = false;
    private SurfaceHolder surfaceHolder;
    private Game game;

    private double avUPS = 0;
    private double avFPS = 0;


    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.game = game;
        this.surfaceHolder = surfaceHolder;
    }

    public double getAverageUPS() {
        return this.avUPS;
    }

    public double getAverageFPS() {
        return this.avFPS;
    }

    public void startLoop() {
        running = true;
        start();
    }

    @Override
    public void run() {
        super.run();

        int ticks = 0;
        int upd = 0;

        Canvas canvas;

        final float tps = 1000000000/60;

        float deltaTime = 0f;

        long now1;

        long start = System.currentTimeMillis();
        long beg = System.nanoTime();


        long now = start;


        while (running) {
            try {
                canvas = surfaceHolder.lockCanvas();


                game.draw(canvas); upd++;


                now1 = System.nanoTime();
                if(now1-beg+deltaTime > tps) {
                    game.update(now); ticks++;
                    deltaTime = (now1-beg+deltaTime)-tps;
                    beg = now1;
                }




                now =System.currentTimeMillis();
                if(now-start > 1000) {
                    this.avUPS = ticks;
                    this.avFPS = upd;
                    ticks = 0;
                    upd = 0;

                    start = now;
                }

                surfaceHolder.unlockCanvasAndPost(canvas);
            }catch (IllegalArgumentException e) {

            }

        }


    }
}
