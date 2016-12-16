package com.theironyard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	TextureRegion down, up, right, left;

	float x, y, xv, yv;
	static final float MAX_VELOCITY = 100;

	//Random Number
	//Math.random() //can set 0.0 -1.0

	
	@Override
	public void create () {
		batch = new SpriteBatch();

		Texture tiles = new Texture("tiles.png");
		TextureRegion[][] grid = TextureRegion.split(tiles, 16, 16);
		down = grid[6][0];
		up = grid[6][1];
		right = grid[6][3];
		left = new TextureRegion(right);
		left.flip(true, false);
	}

	@Override
	public void render () {
		move();

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(down, x, y);
		batch.end();

	}

	public float decelerate(float velocity) {
		float deceleration = 0.95f;
		velocity *= deceleration;
		if(Math.abs(velocity) < 1) {
			velocity = 0;
		}
		return velocity;
	}

	public void move() {
		if(Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			yv = MAX_VELOCITY * 5;
		} else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			yv = MAX_VELOCITY;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			yv = MAX_VELOCITY * -5;
		} else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			yv = MAX_VELOCITY * -1;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			xv = MAX_VELOCITY * 5;
		} else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			xv = MAX_VELOCITY;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			xv = MAX_VELOCITY * -5;
		} else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			xv = MAX_VELOCITY * -1;
		}

		y = y + (yv * Gdx.graphics.getDeltaTime());
		x = x + (xv * Gdx.graphics.getDeltaTime());

		if(x > Gdx.graphics.getWidth()) {
			x = 0;
		}

		if(x < 0) {
			x = Gdx.graphics.getWidth();
		}

		if(y > Gdx.graphics.getHeight()) {
			y = 0;
		}

		if(y < 0 ) {
			y = Gdx.graphics.getHeight();
		}


		yv = decelerate(yv);
		xv = decelerate(xv);


	}




	@Override
	public void dispose () {
		batch.dispose();

	}
}
