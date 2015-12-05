package com.leonidgrinberg.framework.impl;

import android.content.Context;
import android.view.View;

import com.leonidgrinberg.framework.Input;

import java.util.List;

public class AndroidInput implements Input {    
    com.leonidgrinberg.framework.impl.AccelerometerHandler accelHandler;
    com.leonidgrinberg.framework.impl.TouchHandler touchHandler;

    public AndroidInput(Context context, View view, float scaleX, float scaleY) {
        accelHandler = new com.leonidgrinberg.framework.impl.AccelerometerHandler(context);
        touchHandler = new com.leonidgrinberg.framework.impl.TouchHandler(view, scaleX, scaleY);
    }

    @Override
    public boolean isTouchDown(int pointer) {
        return touchHandler.isTouchDown(pointer);
    }

    @Override
    public int getTouchX(int pointer) {
        return touchHandler.getTouchX(pointer);
    }

    @Override
    public int getTouchY(int pointer) {
        return touchHandler.getTouchY(pointer);
    }

    @Override
    public float getAccelX() {
        return accelHandler.getAccelX();
    }

    @Override
    public float getAccelY() {
        return accelHandler.getAccelY();
    }

    @Override
    public float getAccelZ() {
        return accelHandler.getAccelZ();
    }

    @Override
    public List<TouchEvent> getTouchEvents() {
        return touchHandler.getTouchEvents();
    }
}


