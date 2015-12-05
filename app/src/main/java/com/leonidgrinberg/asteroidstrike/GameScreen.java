package com.leonidgrinberg.asteroidstrike;


import android.graphics.Point;
import android.graphics.Rect;

import com.leonidgrinberg.framework.Game;
import com.leonidgrinberg.framework.Graphics;
import com.leonidgrinberg.framework.Input;
import com.leonidgrinberg.framework.Screen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


/**
 * Created by leonidgrinberg on 15-06-05.
 */

public class GameScreen extends Screen {



    ship myShip;
    List<Missile> missiles = new ArrayList<Missile>();
    List<Asteroid> asteroids = new ArrayList<Asteroid>();

    float bombtime = 0;
    float bombTimeCutoff = 3;

    float missileTime = 0;
    float missileTimeCutoff = 0.5f;
    Random r = new Random();
    int numberOfLives = 3;
    int score = 0;

    ParticalManager particalManager;

    public GameScreen(Game game) {
        super(game);

        myShip = new ship(300, 1100);
        particalManager = new ParticalManager();
    }
    @Override
    public void update ( float deltaTime) {

        // this updates partical movment
        particalManager.update(deltaTime);

        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_UP) {
                if (event.x > 416 && event.y > 1700) {
                    game.setScreen(new MainManuScreen(game));
                    //   if(Settings.soundEnabled)
                    //    Assets.click.play(1);
                    return;
                }
            } else {
                myShip.xPos = event.x - Assets.ship.getWidth() / 2;
                myShip.yPositon = event.y - Assets.ship.getHeight();
            }
        }

        missileTime += deltaTime;
        if(missileTime > missileTimeCutoff) {
            // add a Missile
            missiles.add(new Missile(myShip.xPos + Assets.ship.getWidth() / 2 - Assets.Missile.getWidth() / 2, myShip.yPositon, -600));
            missileTime = 0;
        }


        bombtime += deltaTime;
        if (bombtime > bombTimeCutoff) {
            // add a Meteor
            asteroids.add(new Asteroid(r.nextInt(game.getGraphics().getWidth() - Assets.Meteorx22.getWidth()), -Assets.Meteorx22.getHeight()));
            bombtime = 0;
        }

        for (int i = 0; i < asteroids.size(); i++) {
            asteroids.get(i).update(deltaTime, 200);
        }

        for (int i = 0; i < missiles.size(); i++) {
            missiles.get(i).update(deltaTime);
        }



        // ------ CHECK METEOR AND SHIP COLLISION     ----

        // check bomb and catcher collision
        Iterator<Asteroid> itr2 = asteroids.iterator();
        while (itr2.hasNext()) {
            Asteroid asteroid = itr2.next();

            if (collision(asteroid, myShip)) {
                itr2.remove();
                numberOfLives--;
                //if(Settings.soundEnabled)
                //    Assets.catchBomb.play(1);

                if (numberOfLives == 0) {
                    Settings.addScore(score);
                    Settings.save(game.getFileIO());
                    //state = GameState.GameOver;
                    game.setScreen(new MainManuScreen(game));
                }
            }
        }

        Iterator<Asteroid> itr3 = asteroids.iterator();
        while (itr3.hasNext()) {
            Asteroid asteroid = itr3.next();

            Iterator<Missile> itr1 = missiles.iterator();
            while (itr1.hasNext()) {
                Missile missile = itr1.next();

                if (collision(missile, asteroid)) {

                    asteroid.health = asteroid.health - missile.damage;
                    if (asteroid.health <= 0) {

                        itr1.remove();
                        itr3.remove();
                        score++;
                        particalManager.emit(40,0xFFFFFF00,new Point((int)asteroid.x,(int)asteroid.y),40,30,50,0);
                        //if(Settings.soundEnabled)
                        //    Assets.catchBomb.play(1);

                        break;
                    }
                    itr1.remove();
                }
            }
        }

    }



    private boolean collision(Missile m, Asteroid a)
    {
        Rect missile = new Rect((int) m.x, (int) m.y ,
                (int) m.x + Assets.Missile.getWidth(),
                (int) m.y + Assets.Missile.getHeight());
        Rect assteroid = new Rect((int) a.x, (int) a.y,
                (int) a.x + Assets.Meteorx22.getWidth(),
                (int) a.y + Assets.Meteorx22.getHeight());

        return missile.intersect(assteroid);
    }

    private boolean collision(Asteroid b, ship c)
    {
        Rect bomb = new Rect((int) b.x, (int) b.y ,
                (int) b.x + Assets.Meteorx22.getWidth(),
                (int) b.y + Assets.Meteorx22.getHeight());
        Rect catcher = new Rect((int) c.xPos, (int) c.yPositon,
                (int) c.xPos + Assets.ship.getWidth(),
                (int) c.yPositon + Assets.ship.getHeight());

        return bomb.intersect(catcher);
    }

    @Override
    public void present ( float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.mainBackground, 0, 0);


        for (int i = 0; i < missiles.size(); i++) {
            g.drawPixmap(Assets.Missile, (int) missiles.get(i).x, (int) missiles.get(i).y);
        }
        g.drawPixmap(Assets.ship, (int) myShip.xPos, (int) myShip.yPositon);

        // this draw the particles
        for (Partical p: particalManager.particalBuffer) {
            // get all active particles
            int offset = 100;
            int x = (int)p.x+offset ;
            int y = (int)p.y+offset ;
//            g.drawPixel(x,y,p.color);
//            g.drawPixel(x+1,y,p.color);
//            g.drawPixel(x,y+1,p.color);
//            g.drawPixel(x+1,y+1,p.color);


            g.drawPixmap(Assets.partic1,x,y);
        }


        //g.drawPixmap(Assets.life, 950, 200);
        //g.drawPixmap(Assets.life, 950, 350);
        //g.drawPixmap(Assets.life, 950, 500);


        //g.drawPixmap(Assets.Meteorx22, 200, 100);
        for (int i = 0; i < asteroids.size(); i++) {
            g.drawPixmap(Assets.Meteorx22, (int) asteroids.get(i).x, (int) asteroids.get(i).y);
        }



        for(int i = 0; i < this.numberOfLives; i++) {
            int x = 0;
            int y = Assets.life.getHeight() * i;
            g.drawPixmap(Assets.life, x, y);
        }

        drawText(g, score + "",
                g.getWidth() / 2 - (score + "").length()*20 / 2,  5);

        g.drawPixmap(Assets.volumeup, 950, 20);

        //g.drawPixmap(Assets.back, 300, 1700);
    }

    public void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);

            if (character == ' ') {
                x += 20;
                continue;
            }

            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }

            g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;


        }
    }
    @Override
    public void pause () {

    }

    @Override
    public void resume () {

    }

    @Override
    public void dispose () {

    }
}
