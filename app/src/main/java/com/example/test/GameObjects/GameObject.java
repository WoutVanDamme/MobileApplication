package com.example.test.GameObjects;

import android.graphics.Canvas;

public abstract class GameObject {


    public abstract void update(long now);

    public abstract void render(Canvas canvas);

}
