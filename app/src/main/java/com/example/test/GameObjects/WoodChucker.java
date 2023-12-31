package com.example.test.GameObjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.example.test.Game;
import com.example.test.utils.Camera;
import com.example.test.utils.WoodChuckerState;

public class WoodChucker extends GameObject{

    private int x, y;
    private House house;

    private WoodChuckerState state;

    private Camera camera;
    private Game game;

    private long last;
    private int rest;

    // TEMP
    int size = 64;
    private Paint paint;

    public WoodChucker(int x, int y, House house, Camera camera, Game game, int rest) {
        this.x = x;
        this.y = y;
        this.house = house;
        this.state = WoodChuckerState.RESTING;

        this.paint = new Paint();
        this.paint.setColor(Color.rgb(150,120,80));

        this.camera = camera;
        this.game = game;

        this.last = -69;

        this.rest = rest;
    }
    @Override
    public void update(long now) {

        switch(this.state) {
            case RESTING:
                Wait(now, rest*575, WoodChuckerState.WALKING_TO_FOREST);
                break;
            case RETURN:
                this.game.setWood(this.game.getWood() + 1);
                //Wait(now, 100, WoodChuckerState.WALKING_TO_FOREST);
                this.state = WoodChuckerState.WALKING_TO_FOREST;
                break;
            case COLLECTING_WOOD:
                Wait(now, 2000, WoodChuckerState.WALKING_HOME);
                break;
            case WALKING_TO_FOREST:
                this.WalkToForest();
                break;
            case WALKING_HOME:
                this.WalkHome();
                break;

        }


    }


    @Override
    public void render(Canvas canvas) {
        Rect rect = new Rect(camera.TransformX(this.x), camera.TransformY(this.y), camera.TransformX(this.x+this.size), camera.TransformY(this.y+this.size));
        canvas.drawRect(rect, this.paint);
    }


    private void WalkToForest() {

        if(this.y <= 0) {
            this.state = WoodChuckerState.COLLECTING_WOOD;
        }else {
            this.y -= 3;
        }
    }

    private void WalkHome() {

        if(this.y < this.house.getY()) {
            this.y += 3;
        }
        else {
            this.state = WoodChuckerState.RETURN;
        }
    }

    private void Wait(long now, long time, WoodChuckerState state) {


        if(last == -69) {
            last = now;
        }

        if(now-last > time) {
            this.state = state;
            last = -69;
        }
    }
}
