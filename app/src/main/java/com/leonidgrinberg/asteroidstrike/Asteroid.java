package com.leonidgrinberg.asteroidstrike;

import java.util.Random;

/**
 * Created by leonidgrinberg on 15-06-05.
 */

public class Asteroid {
    //public float x, y;
    public float speed;//test
    public float vx;
    public int health;

    public Asteroid(float x, float y, float speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.vx = (float) (((float)-5) + (Math.random()*10));
        this.health = 3;
    }

    public void update(float deltaTime, float timeDelta) {
        this.y += speed * timeDelta;
    }


    public float x, y;
    private int dir = 1;
    Random r = new Random();

    public Asteroid(float x, float y) {
        this.x = x;
        this.y = y;
        this.vx = (float) (((float)-5) + (Math.random()*10));
        this.health = 3;

    }

//    public void update(float deltaTime, int speed) {
//          int mySpeed = Math.min(15, speed / 5) + 1;
//        if (this.x < 50)
//            dir *= -1;
//
//        else if (this.x > 362)
//            dir *= -1;

         public void update(float deltaTime, int speed)
        {
            int mySpeed = Math.min(15, speed / 5) + 1;

            this.y += (20 * mySpeed) * deltaTime;


//            this.x += (1 * mySpeed) * deltaTime;
                this.x += vx;
        }
    }






