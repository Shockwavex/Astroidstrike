package com.leonidgrinberg.asteroidstrike;

import com.leonidgrinberg.framework.Game;
import com.leonidgrinberg.framework.Graphics;
import com.leonidgrinberg.framework.Screen;


/**
 * Created by leonidgrinberg on 15-05-23.
 */
public class LoadingScreen extends Screen {
    public LoadingScreen(Game game)

    {super(game);}

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Settings.load(game.getFileIO());

        if(!Assets.initialized) {
            Assets.sunbackground = g.newPixmap("sunbackground.png", Graphics.PixmapFormat.RGB565);
            Assets.title = g.newPixmap("title.png", Graphics.PixmapFormat.RGB565);
            Assets.play = g.newPixmap("play.png", Graphics.PixmapFormat.RGB565);
            Assets.story = g.newPixmap("story.png", Graphics.PixmapFormat.RGB565);
            Assets.leaderboard = g.newPixmap("leaderboard.png", Graphics.PixmapFormat.RGB565);
            Assets.instructions = g.newPixmap("instructions.png", Graphics.PixmapFormat.RGB565);

            Assets.back = g.newPixmap("back.png", Graphics.PixmapFormat.RGB565);
            Assets.ship = g.newPixmap("ship.png", Graphics.PixmapFormat.RGB565);
            Assets.mainBackground = g.newPixmap("mainBackground.png", Graphics.PixmapFormat.RGB565);
            Assets.numbers = g.newPixmap("numbers.png", Graphics.PixmapFormat.RGB565);
            Assets.volumeup = g.newPixmap("volumeup.png", Graphics.PixmapFormat.RGB565);
            Assets.Missile = g.newPixmap("missile.png", Graphics.PixmapFormat.RGB565);

            Assets.life = g.newPixmap("life.png", Graphics.PixmapFormat.RGB565);
            Assets.stop = g.newPixmap("stop.png", Graphics.PixmapFormat.RGB565);
            Assets.Meteorx22 = g.newPixmap("Meteorx22.png", Graphics.PixmapFormat.RGB565);
            Assets.partic1 = g.newPixmap("partic1.png", Graphics.PixmapFormat.RGB565);

            Assets.music1 = game.getAudio().newMusic("music1.ogg");
            //Assets.gameSound = game.getAudio().newMusic("gameSound.ogg");

            if (!Assets.music1.isPlaying() && Settings.soundEnabled) {
                Assets.music1.play();
            }

            Assets.initialized = true;
        }

        game.setScreen(new MainManuScreen(game));
    }

    @Override
    public void present(float deltaTime) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose()
    {
    }
}