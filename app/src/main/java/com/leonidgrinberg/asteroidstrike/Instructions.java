package com.leonidgrinberg.asteroidstrike;

import com.leonidgrinberg.framework.Game;
import com.leonidgrinberg.framework.Graphics;
import com.leonidgrinberg.framework.Input;
import com.leonidgrinberg.framework.Screen;

import java.util.List;

/**
 * Created by leonidgrinberg on 15-05-30.
 */
public class Instructions extends Screen {
    public Instructions(Game game) {
        super(game);
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
