package com.leonidgrinberg.asteroidstrike;



import com.leonidgrinberg.framework.Game;
import com.leonidgrinberg.framework.Graphics;
import com.leonidgrinberg.framework.Input.TouchEvent;
import com.leonidgrinberg.framework.Screen;

import java.util.List;

/**
 * Created by leonidgrinberg on 15-05-30.
 */
public class Story extends Screen {
    public Story(Game game) {
        super(game);
    }

        @Override
        public void update ( float deltaTime){
            List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

            int len = touchEvents.size();
            for (int i = 0; i < len; i++) {
                TouchEvent event = touchEvents.get(i);
                if (event.type == TouchEvent.TOUCH_UP) {
                    if (event.y > 1700) {// CHECK THE VALUES
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
