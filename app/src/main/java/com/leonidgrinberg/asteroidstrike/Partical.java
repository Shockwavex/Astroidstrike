package com.leonidgrinberg.asteroidstrike;

/**
 * Created by Richard on 15-01-27.
 */
public class Partical {


    public boolean dead = false;
    public float y;
    public float x;
    public float vy;
    public float vx;
    public int life;
    public int color;
    public float gravity;


    public void update(float deltaTime) {

        if ( life-- <= 0 ) dead = true;
        x += vx;
        y += vy;

        vy += gravity;

    }

}
