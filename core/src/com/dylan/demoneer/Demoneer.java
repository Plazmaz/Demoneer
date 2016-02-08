package com.dylan.demoneer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.dylan.demoneer.entity.EntityPlayer;
import com.dylan.demoneer.entity.EntitySnake;
import com.dylan.demoneer.entity.ai.pathfinding.TileNode;
import com.dylan.demoneer.entity.ai.pathfinding.TiledGraph;

import java.util.Arrays;

public class Demoneer extends ApplicationAdapter {
	public static final float TILE_SIZE = 16f;
	SpriteBatch batch;
	Stage stage;
	public static TiledMap MAP;
	public static final float MAP_SCALE = 3.5f;
	private static Demoneer instance;
    TiledMapRenderer mapRenderer;
	OrthographicCamera camera;
	private EntityPlayer player;
	private TiledGraph pathfinderGraph;

	@Override
	public void create () {
		instance = this;
		batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
		MAP = new TmxMapLoader().load("lvl1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(MAP, MAP_SCALE);
        camera = (OrthographicCamera) stage.getCamera();
		camera.zoom = .9f;
		camera.update();
		pathfinderGraph = new TiledGraph(Demoneer.MAP.getProperties().get("width", Integer.class), Demoneer.MAP.getProperties().get("height", Integer.class));

//        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		player = new EntityPlayer(stage.getCamera());
		player.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

		EntitySnake snake = new EntitySnake();
		snake.setX(player.getX() + 100);
		snake.setY(player.getY());

		stage.addActor(snake);
		stage.addActor(player);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        camera.update();
        mapRenderer.setView(camera);
        mapRenderer.render();
        stage.draw();
	}

	public static Demoneer getInstance() {
		return instance;
	}

	public TileNode getTileNodeAt(int x, int y, String... allowedLayers) {
		for (MapLayer layer : MAP.getLayers()) {
			if(!Arrays.asList(allowedLayers).contains(layer.getName())) {
				continue;
			}
			TiledMapTileLayer.Cell cell = ((TiledMapTileLayer) layer).getCell(x, y);
			if (cell == null) {
				continue;
			} else if(cell.getTile() != null){
				return new TileNode(x, y, cell.getTile(), layer.getName().equals("obstructions"));
			}
		}
		return null;
	}

	public boolean isObstructed(float x, float y) {
		int gridX = (int) (x / (Demoneer.TILE_SIZE * Demoneer.MAP_SCALE));
		int gridY = (int) (y / (Demoneer.TILE_SIZE * Demoneer.MAP_SCALE));
		TiledMapTileLayer.Cell cell = ((TiledMapTileLayer)MAP.getLayers().get("obstructions")).getCell(gridX, gridY);
		if(cell != null && cell.getTile() != null) {
			return true;
		}
		return false;
	}

	public EntityPlayer getPlayer() {
		return player;
	}

	public TiledGraph getPathfinderGraph() {
		return pathfinderGraph;
	}

	public double getZoom() {
		return camera.zoom;
	}
}
