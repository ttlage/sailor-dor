package org.academiadecodigo.hackathon.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


/**
 * Created by codecadet on 26/07/2018.
 */
public class Wood implements GameObjectType{

    private Rectangle rectangle;
    private String filename;
    private Texture texture;

    public Wood(String name, float x, float y){
        filename = name;
        rectangle = new Rectangle();
        rectangle.x = x;
        rectangle.y = y;
        texture = new Texture(name);
        rectangle.height = texture.getHeight();
        rectangle.width = texture.getWidth();

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
