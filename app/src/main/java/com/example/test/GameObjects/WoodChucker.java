package com.example.test.GameObjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.example.test.utils.Camera;
import com.example.test.utils.WoodChuckerState;

public class WoodChucker extends GameObject{

    private int x, y;
    private House house;

    private WoodChuckerState state;

    private Camera camera;

    // TEMP
    int size = 64;
    private Paint paint;

    public WoodChucker(int x, int y, House house, Camera camera) {
        this.x = x;
        this.y = y;
        this.house = house;
        this.state = WoodChuckerState.RESTING;

        this.paint = new Paint();
        this.paint.setColor(Color.rgb(150,120,80));

        this.camera = camera;
    }
    @Override
    public void update() {

        switch(this.state) {
            case RESTING:
                // wait
                this.state = WoodChuckerState.WALKING_TO_FOREST;
                break;
            case COLLECTING_WOOD:
                break;
            case WALKING_TO_FOREST:
                this.WalkToForest();
                break;
            case WALKING_HOME:
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
            this.state = WoodChuckerState.WALKING_HOME;
        }else {
            this.y -= 3;
        }
    }
}
