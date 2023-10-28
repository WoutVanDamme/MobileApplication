package com.example.test.UI;

import android.graphics.Canvas;
import android.view.MotionEvent;

public abstract class UIElement {

    public abstract void update(long now);
    public abstract void render(Canvas canvas);

    public abstract void event(MotionEvent event);

    public abstract int getTX();
    public abstract int getTY();

    public abstract int getTRight();
    public abstract int getTBottom();

    public abstract int getWidth();
    public abstract int getHeight();

    public abstract void setActive(boolean x);
    public abstract boolean isActive();
}
