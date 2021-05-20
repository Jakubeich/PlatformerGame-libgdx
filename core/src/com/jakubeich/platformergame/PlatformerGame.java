package com.jakubeich.platformergame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.jakubeich.platformergame.world.GameMap;
import com.jakubeich.platformergame.world.TileType;
import com.jakubeich.platformergame.world.TiledGameMap;

public class PlatformerGame extends ApplicationAdapter {
	
	OrthographicCamera cam;
	SpriteBatch batch;
	Texture img;
	
	GameMap gameMap;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		
		///////////////Camera//////////////////////
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.update();
		
		////////////////////////////////////////
		
		gameMap = new TiledGameMap();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		
		// Touched Movement Input
		if(Gdx.input.isTouched()) {
			cam.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
			cam.update();
		}
		
		if(Gdx.input.justTouched()) {
			Vector3 pos = cam.unproject(new Vector3(Gdx.input.getY(), Gdx.input.getX(), 0));
			TileType type = gameMap.getTileTypeByLocation(1, pos.x, pos.y);
			
			if(type != null) {
				System.out.println("Kliknul jsi na dlaždici s id " + type.getId() + " " + type.getName() + " " + type.isCollidable() + " " + type.getDamage());
			}
		}
		
		gameMap.render(cam);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
