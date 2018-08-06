package org.academiadecodigo.hackathon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import org.academiadecodigo.hackathon.BoatGame;
import org.academiadecodigo.hackathon.helpers.GameInfo;

/**
 * Created by codecadet on 27/07/2018.
 */
public class InstructionsScreen implements Screen {

    Texture instructions;
    BoatGame boatGame;

    public InstructionsScreen(BoatGame boatGame) {
        this.boatGame = boatGame;
        instructions = new Texture("finalmenteAsInstComoDeveSer.jpg");
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        boatGame.getBatch().begin();
        if(Gdx.input.justTouched()) {
            boatGame.setScreen(new MainGameScreen(boatGame));
        }
        boatGame.getBatch().draw(instructions, 0,0, GameInfo.WIDTH, GameInfo.HEIGHT);
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

    }
}
