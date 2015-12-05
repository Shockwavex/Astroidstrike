package com.leonidgrinberg.asteroidstrike;

/**
 * Created by Richard on 15-01-27.
 */
public class Ticker {
    public float tick               = 1;
    private float tickTime          = 0;

    public Ticker(float tick) {
        this.tick = tick;
    }

    public boolean ready(float deltaTime) {
        if (tick <= 0) return false;
        tickTime += deltaTime;
        if (tickTime > tick) {
            tickTime = 0;
            return true;
        }

        return false;
    }
}
