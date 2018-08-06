package org.academiadecodigo.hackathon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.academiadecodigo.hackathon.screens.MainMenuScreen;

public class BoatGame extends Game {

    private SpriteBatch batch;


    public SpriteBatch getBatch() {
        return batch;
    }


    @Override
    public void create() {

        batch = new SpriteBatch();
        this.setScreen(new MainMenuScreen(this));

    }

    @Override
    public void render() {

        super.render();

    }


}
