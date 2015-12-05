package com.leonidgrinberg.asteroidstrike;

import com.leonidgrinberg.framework.Screen;
import com.leonidgrinberg.framework.impl.AndroidGame;

/**
 * Created by leonidgrinberg on 15-05-23.
 */
public class AsteroidStrikeGame extends AndroidGame {
    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        if(Assets.music1 != null && !Assets.music1.isPlaying() && Settings.soundEnabled) {
            Assets.music1.play();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Assets.music1.stop();
    }
}
