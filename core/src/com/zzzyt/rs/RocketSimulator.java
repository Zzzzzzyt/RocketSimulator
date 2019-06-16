package com.zzzyt.rs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.zzzyt.rs.data.Rocket;
import com.zzzyt.rs.phy.Physics;
import com.zzzyt.rs.phy.Simulator;
import com.zzzyt.rs.rocket.F9B5;

public class RocketSimulator extends ApplicationAdapter{
	SpriteBatch batch;
	Sprite rocket;
	OrthographicCamera cam;
	ShapeRenderer shape;
	FitViewport viewport;
	MyInput input;
	
	float h,w;
	
	Simulator sim;
	double last;
	public Rocket r;

	@Override
	public void create() {
		h=Gdx.graphics.getHeight();
		w=Gdx.graphics.getWidth();
		
		shape=new ShapeRenderer();
		
		batch = new SpriteBatch();
		rocket = new Sprite(new Texture(Gdx.files.internal("rocket.png")));
		rocket.setRegion(0, 0, 23, 44);
		rocket.setOriginCenter();

		r = F9B5.get();
		last = -10000d;
		sim=new Simulator(this,30,100);
		
		cam=new OrthographicCamera(w,h);
		cam.position.set(cam.viewportWidth/2f,cam.viewportHeight/2f,0);
		cam.translate(-w/2,-h/2);
		cam.update();
		
		viewport=new FitViewport(w,h,cam);
		
		input=new MyInput(this);
		Gdx.input.setInputProcessor(input);
	}

	@Override
	public void render() {
		if(!sim.stopped)sim.sim();
		
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		
		rocket.setPosition((float)(r.x/1000), (float)(r.y/1000));
		rocket.setRotation((float)(r.getVAngle()/(2*Math.PI)*360)-90);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
		
		shape.setProjectionMatrix(cam.combined);
		shape.begin(ShapeType.Filled);
		shape.setColor(new Color(0f,0.2f,0.5f,1f));
		shape.ellipse(-(float)Physics.R/1000, -(float)Physics.R/1000, (float)Physics.R*2/1000, (float)Physics.R*2/1000);
		shape.end();
		
		batch.begin();
		rocket.draw(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		rocket.getTexture().dispose();
	}
	
	@Override
	public void resize(int width,int height) {
		viewport.update(width,height);
	}
}
