package org.academiadecodigo.hackathon.objects;

import com.badlogic.gdx.graphics.Texture;
import org.academiadecodigo.hackathon.BoatGame;
import org.academiadecodigo.hackathon.helpers.GameInfo;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by codecadet on 26/07/2018.
 */
public class GameObjectFactory {


    private Wood wood;
    private HappyObject happyObject;
    private String image;
    private BoatGame game;
    private Position[] positions;
    private int positionCount = 0;


    public GameObjectFactory(BoatGame game) {
        this.game = game;
        createPosition();
    }

    public void createPosition() {

        int xNumber = GameInfo.WIDTH / 5;
        int yNumber = (GameInfo.HEIGHT - 150) / 5;
        int xDiv = 0;
        int yDiv = 0;
        int ind = 0;
        positions = new Position[25];
        for (int i = 1; i < 6; i++) {
            yDiv = 0;
            for (int j = 1; j < 6; j++) {
                positions[ind] = new Position();
                positions[ind].setX(xDiv);
                positions[ind].setY(yDiv);
                ind++;
                yDiv += yNumber;
            }
            xDiv += xNumber;
        }

        Collections.shuffle(Arrays.asList(positions));
    }


    public HappyObject createHappy() {
        double random = Math.random();
        if (random < 0.3) {
            image = "happyObjects/beer.png";
        } else if (random < 0.6) {
            image = "happyObjects/cake.png";
        } else {
            image = "happyObjects/heart.png";
        }
        Position position = positions[positionCount];
        happyObject = new HappyObject(image, position.getX(), position.getY());
        positionCount++;
        return happyObject;
    }

    public Wood createWood() {
        Position position = positions[positionCount];
        wood = new Wood("happyObjects/log.png", position.getX(), position.getY());
        positionCount++;
        return wood;
    }

    public float randomizerWidth() {
        return (float) Math.random() * GameInfo.WIDTH;
    }

    public float randomizerHeight() {
        return (float) Math.random() * GameInfo.HEIGHT;
    }

    public Texture createBoat(String picture) {

        return new Texture(picture);

    }

    public class Position {
        float x;
        float y;

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }

        public void setX(float x) {
            this.x = x;
        }

        public void setY(float y) {
            this.y = y;
        }
    }

}


