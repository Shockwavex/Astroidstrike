package com.leonidgrinberg.asteroidstrike;

/**
 * Created by leonidgrinberg on 15-09-26.
 */
public class Missile  {
    public float x, y;
    public float speed;
    public int damage;

    public Missile(float x, float y, float speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = 1;
    }

    public void update(float timeDelta) {
        this.y += speed * timeDelta;
    }
}