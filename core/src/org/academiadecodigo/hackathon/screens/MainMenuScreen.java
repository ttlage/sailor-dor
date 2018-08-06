package org.academiadecodigo.hackathon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import org.academiadecodigo.hackathon.BoatGame;
import org.academiadecodigo.hackathon.helpers.GameInfo;

/**
 * Created by codecadet on 27/07/2018.
 */
public class MainMenuScreen implements Screen{

    Texture menuBackground;
    BoatGame game;

    public MainMenuScreen(BoatGame game) {
        menuBackground = new Texture("menustart.jpg");
        this.game = game;

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.getBatch().begin();
        if(Gdx.input.justTouched()) {
            game.setScreen(new InstructionsScreen(game));
            dispose();
        }

        game.getBatch().draw(menuBackground, 0, 0, GameInfo.WIDTH, GameInfo.HEIGHT);
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {

    }

    public void dispose() {
        menuBackground.dispose();
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
}
