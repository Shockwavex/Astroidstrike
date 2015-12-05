package com.leonidgrinberg.asteroidstrike;

/**
 * Created by Richard on 15-01-27.
 */



import android.graphics.Point;

import com.leonidgrinberg.framework.Pool;

import java.util.ArrayList;
import java.util.List;

public class ParticalManager {

    private Pool<Partical> particalPool;
    private List<Partical> particals = new ArrayList<Partical>();
    public List<Partical> particalBuffer = new ArrayList<Partical>();

    public ParticalManager() {
        Pool.PoolObjectFactory<Partical> factory = new Pool.PoolObjectFactory<Partical>() {
            @Override
            public Partical createObject() {
                return new Partical();
            }
        };
        particalPool = new Pool<Partical>(factory, 100);



    }

    public void update(float deltaTime){
        int l = particalBuffer.size()-1;
        for (int i = l; i >= 0; i--) {
            Partical partical = particalBuffer.get(i);
            partical.update(deltaTime);

            if (partical.dead){
                particalPool.free(partical);
                particalBuffer.remove(partical);
            }
        }
    }

    public void present(float deltaTime){


    }

    public void emit(int count, int color, Point origin, int maxVel ,int life,int jitter , float gravity ){
        for (int i = 0; i < count; i++) {
            Partical partical = particalPool.newObject();
            partical.x = origin.x +  (int)(-(jitter*.5)+ (Math.random()*jitter));
            partical.y = origin.y +  (int)(-(jitter*.5)+ (Math.random()*jitter));
            partical.vx = (int)(-(maxVel*.5)+ (Math.random()*maxVel));
            partical.vy = (int)(-(maxVel*.5)+ (Math.random()*maxVel));
            partical.color = color;
            partical.life = life;
            partical.dead = false;
            partical.gravity = gravity;
            particalBuffer.add(partical);
        }
//        Point

    }

}
