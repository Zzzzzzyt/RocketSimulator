package com.zzzyt.rs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zzzyt.rs.data.Rocket;
import com.zzzyt.rs.phy.Simulator;
import com.zzzyt.rs.rocket.F9B5;

public class RocketSimulator extends ApplicationAdapter {
	SpriteBatch batch;
	Texture rocket;
	OrthographicCamera camera;
	
	Simulator sim;
	double last;
	public Rocket r;

	@Override
	public void create() {
		batch = new SpriteBatch();
		rocket = new Texture("badlogic.jpg");

		r = F9B5.get();
		last = -10000d;
		sim=new Simulator(this,30);
	}

	@Override
	public void render() {
		if(!sim.stopped)sim.sim();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(rocket, (float)(r.x/1000), (float)((r.y-6378000)/1000));
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		rocket.dispose();
	}
}
