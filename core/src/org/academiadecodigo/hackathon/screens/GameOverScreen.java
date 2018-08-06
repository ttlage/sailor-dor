package org.academiadecodigo.hackathon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import org.academiadecodigo.hackathon.BoatGame;
import org.academiadecodigo.hackathon.helpers.GameInfo;

/**
 * Created by codecadet on 27/07/2018.
 */
public class GameOverScreen implements Screen {


    Texture gameOver;
    BoatGame boatGame;
    Music loseSound;

    public GameOverScreen(BoatGame boatGame) {
        gameOver = new Texture("gameOverScreen.jpg");
        this.boatGame = boatGame;
    }



    @Override
    public void show() {
        loseSound = Gdx.audio.newMusic(Gdx.files.internal("losesound.mp3"));
        loseSound.setLooping(true);
        loseSound.play();
    }

    @Override
    public void render(float delta) {
        boatGame.getBatch().begin();
        if (Gdx.input.justTouched()) {
            loseSound.stop();
            boatGame.setScreen(new MainMenuScreen(boatGame));

        }
        boatGame.getBatch().draw(gameOver, 0, 0, GameInfo.WIDTH, GameInfo.HEIGHT);
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
        gameOver.dispose();
    }
}
