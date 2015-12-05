package com.leonidgrinberg.asteroidstrike;

import com.leonidgrinberg.framework.Game;
import com.leonidgrinberg.framework.Graphics;
import com.leonidgrinberg.framework.Input;
import com.leonidgrinberg.framework.Screen;

import java.util.List;

/**
 * Created by leonidgrinberg on 15-05-30.
 */

public class Leaderboard extends Screen {
    String lines[] = new String[5];

    public Leaderboard(Game game) {
        super(game);
        for (int i = 0; i < 5; i++) {
            lines[i] = "" + (i + 1) + ". " + Settings.highscores[i];
        }
    }

    @Override
    public void update ( float deltaTime){
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_UP) {
                if (event.x > 416 && event.y > 1700) {
                    game.setScreen(new MainManuScreen(game));
                    // if(Settings.soundEnabled)
                    //     Assets.click.play(1);
                    return;
                }
            }
        }
    }

    @Override
    public void present ( float deltaTime){
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.mainBackground, 0, 0);
        g.drawPixmap(Assets.back, 300, 1700);

        int y = 100;
        for (int i = 0; i < 5; i++) {
            drawText(g, lines[i], 170, y);
            y += 30;
        }

        g.drawPixmap(Assets.numbers, 416, 256, 0, 128, 64, 64);

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
