package com.leonidgrinberg.asteroidstrike;

import android.util.Log;

import com.leonidgrinberg.framework.Game;
import com.leonidgrinberg.framework.Graphics;
import com.leonidgrinberg.framework.Input.TouchEvent;
import com.leonidgrinberg.framework.Screen;

import java.util.List;

/**
 * Created by leonidgrinberg on 15-05-23.
 */
public class MainManuScreen  extends Screen {
    public MainManuScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            Log.d("LEO", "x = " + event.x + " y = " + event.y);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 0, g.getHeight() - 64, 64, 64)) {
                    Settings.soundEnabled = !Settings.soundEnabled;
                    if(Settings.soundEnabled) {
                        //Assets.click.play(1);
                        Assets.music1.play();
                    }
                    else
                    {
                        Assets.music1.pause();
                    }
                }

                if (inBounds(event, 350, 750, 282, 120)) {
                    game.setScreen(new GameScreen(game));
                    //  if (Settings.soundEnabled)
                    //    Assets.click.play(1);

                    return;
                }

                // STORY
                if (inBounds(event, 350, 900, 334, 112)) {
                    game.setScreen(new Story(game));
                    //if(Settings.soundEnabled)
                    //Assets.click.play(1);
                    return;

                }
                // LEADERBOARD
                if (inBounds(event, 450, 1050, 737, 107)) {
                    // Log.d("LEO", "SHOW INSTRUCTIONS");
                    game.setScreen(new Leaderboard(game));
                    //if(Settings.soundEnabled)
                    //Assets.click.play(1);
                    return;

                }
                // INSTRUCTIONS
                if (inBounds(event, 270, 1200, 683, 122)) {
                    // Log.d("LEO", "SHOW INSTRUCTIONS");
                    game.setScreen(new Instructions(game));

                    //if(Settings.soundEnabled)
                    //Assets.click.play(1);
                    return;

                }
            }
        }
    }

    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if(event.x > x && event.x < x + width - 1 &&
                event.y > y && event.y < y + height - 1)
            return true;
        else
            return false;
    }



    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.sunbackground, 0, 0);
        g.drawPixmap(Assets.title, 125, 420);
        g.drawPixmap(Assets.play, 350, 750);
        g.drawPixmap(Assets.story, 325, 900);
        g.drawPixmap(Assets.leaderboard, 270, 1050);
        g.drawPixmap(Assets.instructions, 270, 1200);




    }

    @Override
    public void pause() {Settings.save(game.getFileIO()); }



        @Override
        public void resume () {

        }

        @Override
        public void dispose () {


        }
    }

