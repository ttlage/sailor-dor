package org.academiadecodigo.hackathon.player;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import org.academiadecodigo.hackathon.helpers.GameInfo;

/**
 * Created by codecadet on 26/07/2018.
 */
public class Player {

    private int speedMovement;
    private float happiness = 0.5f;
    private Rectangle rectangle;
    private String filename;
    private Texture texture;

    public int getSpeedMovement() {

        return speedMovement;
    }

    public Player(String name, float x, float y, int speed) {

        filename = name;
        rectangle = new Rectangle();
        rectangle.x = x;
        rectangle.y = y;
        texture = new Texture(name);
        rectangle.width = texture.getWidth();
        rectangle.height = texture.getHeight();

        this.speedMovement = speed;
    }

    public void dropHapyness() {
        happiness -= GameInfo.HAPPINESS_DROP_AMMOUNT;


    }


    public float getHappiness() {
        return happiness;
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

    public void setHappiness(float happinessChange) {
        if(happinessChange>1){
            return;
        }

        this.happiness = happinessChange;
    }
}
