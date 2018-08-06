package org.academiadecodigo.hackathon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Entity {

    public Vector2 pos;
    public Texture texture;
    public float width;
    public float height;

    public Entity(Vector2 pos, Texture texture) {
        this.pos = pos;
        this.texture = texture;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, pos.x, pos.y, width, height);
    }

    public Texture getTexture() {
        return texture;
    }
}
