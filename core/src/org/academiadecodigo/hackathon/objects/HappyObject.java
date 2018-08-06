package org.academiadecodigo.hackathon.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by codecadet on 26/07/2018.
 */
public class HappyObject implements GameObjectType{

    private Rectangle rectangle;
    private String filename;
    private Texture texture;

    public HappyObject(String name, float x, float y){

        filename = name;
        rectangle = new Rectangle();
        rectangle.x = x;
        rectangle.y = y;
        texture = new Texture(name);
        rectangle.width = texture.getWidth();
        rectangle.height = texture.getHeight();

    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public String getFilename() {
        return filename;
    }

    public Texture getTexture() {
        return texture;
    }
}
