package org.academiadecodigo.hackathon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.academiadecodigo.hackathon.BoatGame;
import org.academiadecodigo.hackathon.helpers.GameInfo;
import org.academiadecodigo.hackathon.objects.GameObjectFactory;
import org.academiadecodigo.hackathon.objects.HappyObject;
import org.academiadecodigo.hackathon.objects.Wood;
import org.academiadecodigo.hackathon.player.Player;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by codecadet on 26/07/2018.
 */
public class MainGameScreen implements Screen {


    private Player player;
    private BoatGame game;
    private World world;
    private List<Wood> woods;
    private List<HappyObject> happyObjects;
    private GameObjectFactory gameObjectFactory;
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private OrthographicCamera cam;
    private Music gameSound;
    private Music woodDrop;

    private float x = 0;
    private float y = 0;
    private int boatIt = 0;

    private Texture happiness;
    private Texture hapinessBar;

    private Texture boatBar;


    private List<Texture> woodInBag;

    private Texture boat;
    private List<Texture> boatGoal;

    private float boatload = 0.1f;
    private boolean woodBag;
    private int counter = 0;

    private int boatX = GameInfo.WIDTH / 4;
    private boolean win = false;

    public MainGameScreen(BoatGame boat) {

        this.game = boat;
        world = new World(new Vector2(0, -9.8f), true);

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        int h = (int) (height / Math.floor(height / 160));
        int w = (int) (width / (width / (width / Math.floor(width / 160))));

        cam = new OrthographicCamera(w, h);
        cam.setToOrtho(false, width, height);
        cam.zoom = 1f;
        cam.update();
        tiledMap = new TmxMapLoader().load("map/untitled.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    @Override
    public void show() {

        gameSound = Gdx.audio.newMusic(Gdx.files.internal("gamesound.mp3"));
        woodDrop = Gdx.audio.newMusic(Gdx.files.internal("droppingwood.mp3"));
        gameSound.play();

        player = new Player("sprite/SOBRALsprite.png", 10, 10, 5);


        happyObjects = new ArrayList();
        woods = new ArrayList();
        gameObjectFactory = new GameObjectFactory(game);
        boatGoal = new ArrayList();

        for (int i = 0; i < 10; i++) {
            happyObjects.add(gameObjectFactory.createHappy());
        }
        for (int i = 0; i < 10; i++) {
            woods.add(gameObjectFactory.createWood());
        }

        happiness = new Texture("blank.png");
        hapinessBar = new Texture("barhappiness.png");
        boat = new Texture("blank.png");
        boatBar = new Texture("barboat.png");

        woodInBag = new ArrayList();
        for (int i = 0; i < 10; i++) {
            woodInBag.add(new Texture("happyObjects/bag.png"));
        }


        boatGoal = new ArrayList();
        for (int i = 1; i <= 11; i++) {
            boatGoal.add(gameObjectFactory.createBoat("boat/boatPhase" + i + ".png"));
        }

        //heart = new Heart("heart.png", 5, 5);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();

    }

    @Override
    public void render(float delta) {

        move();
        cam.update();
        tiledMapRenderer.render();

        tiledMapRenderer.setView(cam);

        game.getBatch().begin();

        counter++;

        /*
        for (HappyObject happyObject : happyObjects) {
            game.getBatch().draw(happyObject.getTexture(), happyObject.getRectangle().x, happyObject.getRectangle().y);
        }
        for (Wood wood : woods) {
            game.getBatch().draw(wood.getTexture(), wood.getRectangle().x, wood.getRectangle().y);
        }
    */
        if (!win) {
            game.getBatch().draw(player.getTexture(), x, y);
        }


        if (happyObjects.size() >= 2) {
            for (int i = 0; i < 2; i++) {
                game.getBatch().draw(happyObjects.get(i).getTexture(), happyObjects.get(i).getRectangle().x, happyObjects.get(i).getRectangle().y);
            }
        } else if (happyObjects.size() == 1) {
            game.getBatch().draw(happyObjects.get(0).getTexture(), happyObjects.get(0).getRectangle().x, happyObjects.get(0).getRectangle().y);
        }

        if (woods.size() >= 2) {
            for (int i = 0; i < 2; i++) {
                game.getBatch().draw(woods.get(i).getTexture(), woods.get(i).getRectangle().x, woods.get(i).getRectangle().y);
            }
        } else if (woods.size() == 1) {
            game.getBatch().draw(woods.get(0).getTexture(), woods.get(0).getRectangle().x, woods.get(0).getRectangle().y);
        }

        game.getBatch().draw(hapinessBar, 1000, 1280 - 160);
        if (!win) {
            game.getBatch().draw(player.getTexture(), x, y);
        }
        game.getBatch().draw(hapinessBar, 1050, 1024 - 130);

        game.getBatch().draw(boatBar, 1050, 1024 - 130 - 50);

        if (player.getHappiness() > 0.6f)
            game.getBatch().setColor(Color.GREEN);
        else if (player.getHappiness() > 0.2f)
            game.getBatch().setColor(Color.ORANGE);
        else
            game.getBatch().setColor(Color.RED);

        game.getBatch().draw(happiness, 1050 + 7, 1024 - 130 + 6, 187 * player.getHappiness(), 15);
        game.getBatch().setColor(Color.WHITE);


        if (boatload > 0.6f)
            game.getBatch().setColor(Color.GREEN);
        else if (boatload > 0.2f)
            game.getBatch().setColor(Color.ORANGE);
        else
            game.getBatch().setColor(Color.RED);

        game.getBatch().draw(boat, 1050 + 7, 1024 - 130 + 6 - 49, 187 * boatload, 15);
        game.getBatch().setColor(Color.WHITE);

        if (woodBag) {
            game.getBatch().draw(woodInBag.get(0), 980, 1024 - 170 + 6);
        }

        if ((x > 470 && x < 530) && (y > 725) && (woodBag)) {
            boatload += 0.1;
            woodBag = false;
            woodInBag.remove(0);
            boatIt++;
            woodDrop.play();
            woodDrop.play();

        }

        if (!win) {
            game.getBatch().draw(boatGoal.get((int) (boatload * 10) - 1), (boatX), GameInfo.HEIGHT - 210);
        } else {

            game.getBatch().draw(boatGoal.get(10), (boatX), GameInfo.HEIGHT - 210);
        }

        game.getBatch().end();


        world.step(Gdx.graphics.getDeltaTime(), 6, 2);

        /*
        for (Iterator<HappyObject> iter = happyObjects.iterator(); iter.hasNext(); ) {
            HappyObject happyObject = iter.next();
            if (happyObject.getRectangle().overlaps(player.getRectangle())) {
                iter.remove();
                player.setHappiness(player.getHappiness() + 0.1f);
            }
        }
        */

        if (happyObjects.size() >= 2) {
            for (int i = 0; i < 2; i++) {
                if (happyObjects.size() >= 2) {
                    if (happyObjects.get(i).getRectangle().overlaps(player.getRectangle())) {
                        happyObjects.remove(i);
                        player.setHappiness(player.getHappiness() + 0.099999f);
                    }
                }
            }
        } else if (happyObjects.size() == 1) {
            if (happyObjects.get(0).getRectangle().overlaps(player.getRectangle())) {
                happyObjects.remove(0);
                player.setHappiness(player.getHappiness() + 0.1f);
            }
        }


        if (counter == GameInfo.FRAMES_TO_DROP_HAPPINESS) {
            player.dropHapyness();
            counter = 0;

        }


        if (woods.size() >= 2) {
            for (int i = 0; i < 2; i++) {
                if (woods.size() >= 2) {
                    if (woods.get(i).getRectangle().overlaps(player.getRectangle())) {
                        if (woodBag) {
                            return;
                        }
                        woods.remove(i);
                        woodBag = true;
                    }
                }
            }
        } else if (woods.size() == 1) {
            if (woods.get(0).getRectangle().overlaps(player.getRectangle())) {
                if (woodBag) {
                    return;
                }
                woods.remove(0);
                woodBag = true;
            }
        }

        /*
        for (Iterator<Wood> iter = woods.iterator(); iter.hasNext(); ) {
            Wood wood = iter.next();
            if (wood.getRectangle().overlaps(player.getRectangle())) {
                if (woodBag) {
                    return;
                }
                iter.remove();
                woodBag = true;
            }
        }
        */

        if (player.getHappiness() <= 0) {
            gameSound.stop();
            game.setScreen(new GameOverScreen(game));
        }

        if (boatload >= 1) {
            boatX += 5;
            win = true;
        }

        if (boatX > GameInfo.WIDTH) {
            gameSound.stop();
            game.setScreen(new WinScreen(game));
        }

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
        player.getTexture().dispose();
        tiledMap.dispose();
        gameSound.dispose();
    }

    private boolean checkMapCollision() {
        MapLayer collisionLayer = tiledMap.getLayers().get(GameInfo.COLLISION_LAYER);
        MapObjects mapObjects = collisionLayer.getObjects();

        for (RectangleMapObject rectangleObject : mapObjects.getByType(RectangleMapObject.class)) {
            Rectangle rectangle = rectangleObject.getRectangle();

            if (Intersector.overlaps(rectangle, player.getRectangle())) {
                return true;
            }
        }

        return false;
    }

    private void move() {
        Float playerOldX = player.getRectangle().getX();
        Float playerOldY = player.getRectangle().getY();

        Float camOldX = cam.position.x;
        Float camOldY = cam.position.y;

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            //cam.position.y += player.getSpeedMovement();
            y += player.getSpeedMovement();
            player.getRectangle().y = y;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            //cam.position.y += -1 * player.getSpeedMovement();
            y -= player.getSpeedMovement();
            player.getRectangle().y = y;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            //cam.position.x += -1 * player.getSpeedMovement();
            x -= player.getSpeedMovement();
            player.getRectangle().x = x;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            //cam.position.x += player.getSpeedMovement();
            x += player.getSpeedMovement();
            player.getRectangle().x = x;
        }


        if (checkMapCollision()) {
            x = playerOldX;
            y = playerOldY;
            player.getRectangle().x = playerOldX;
            player.getRectangle().y = playerOldY;
            //cam.position.y = camOldY;
            // cam.position.x = camOldX;
        }
    }


}
