package org.academiadecodigo.hackathon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import org.academiadecodigo.hackathon.BoatGame;
import org.academiadecodigo.hackathon.helpers.GameInfo;

import java.time.temporal.Temporal;

/**
 * Created by codecadet on 27/07/2018.
 */
public class WinScreen implements Screen{

    Texture youwin;
    BoatGame boatGame;
    Music youwinsound;

    public WinScreen(BoatGame boatGame) {
        this.boatGame = boatGame;
        youwin = new Texture("endScreenWithPitch.jpg");

    }

    @Override
    public void show() {
        youwinsound = Gdx.audio.newMusic(Gdx.files.internal("youwinsound.mp3"));
        youwinsound.play();
    }

    @Override
    public void render(float delta) {
        boatGame.getBatch().begin();
        if(Gdx.input.justTouched()) {
            youwinsound.stop();
            boatGame.setScreen(new MainMenuScreen(boatGame));
        }
        boatGame.getBatch().draw(youwin, 0,0, GameInfo.WIDTH, GameInfo.HEIGHT);
        boatGame.getBatch().end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        youwin.dispose();
        youwinsound.dispose();
    }
}
