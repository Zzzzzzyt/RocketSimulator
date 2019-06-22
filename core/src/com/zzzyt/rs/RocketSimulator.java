package com.zzzyt.rs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.zzzyt.rs.data.Rocket;
import com.zzzyt.rs.phy.Physics;
import com.zzzyt.rs.phy.Simulator;
import com.zzzyt.rs.rocket.F9B5;

public class RocketSimulator extends ApplicationAdapter{
	SpriteBatch batch;
	Sprite rocket;
	Sprite rocketi;
	OrthographicCamera cam;
	ShapeRenderer shape;
	FitViewport viewport;
	MyInput input;
	
	float h,w;
	
	Simulator sim;
	double last;
	public Rocket r;
	private int fcd;

	public boolean focusOn;
	
	private Vector3 tmp;
	
	@Override
	public void create() {
		h=Gdx.graphics.getHeight();
		w=Gdx.graphics.getWidth();
		
		shape=new ShapeRenderer();
		
		batch = new SpriteBatch();
		rocket = new Sprite(new Texture(Gdx.files.internal("rocket.png")));
		rocket.setRegion(0, 0, 23, 45);
		rocket.setOriginCenter();
		
		rocketi = new Sprite(new Texture(Gdx.files.internal("rocketi.png")));
		rocketi.setRegion(0, 0, 32, 32);
		rocketi.setOriginCenter();

		r = F9B5.get();
		last = -10000d;
		sim=new Simulator(this,30,1);
		
		cam=new OrthographicCamera(w,h);
		cam.position.set(cam.viewportWidth/2f,cam.viewportHeight/2f,0);
		cam.setToOrtho(false);
		cam.translate(0,(float)Physics.R);
		cam.update();
		
		viewport=new FitViewport(w,h,cam);
		
		input=new MyInput(this);
		Gdx.input.setInputProcessor(input);
		
		focusOn=true;
		fcd=0;
	}

	@Override
	public void render() {
		inputs();
		
		if(!sim.stopped)sim.sim();
		
		if(focusOn) {
			cam.position.set((float)r.x, (float)r.y, 0);
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
		
		cam.update();
		
		shape.setProjectionMatrix(cam.combined);
		batch.setProjectionMatrix(cam.combined);
		
		shape.begin(ShapeType.Filled);
		shape.setColor(new Color(0,0,0.3f,1));
		shape.circle(0, 0, (float)(Physics.R+Physics.up));
		shape.end();
		
		shape.begin(ShapeType.Filled);
		shape.setColor(new Color(0.1f,0.2f,0.6f,1));
		shape.circle(0, 0, (float)(Physics.R+Physics.up/2));
		shape.end();
		
		shape.begin(ShapeType.Filled);
		shape.setColor(new Color(0,0.5f,0,1));
		shape.circle(0, 0, (float)Physics.R);
		shape.end();
		
		batch.begin();
		if(cam.zoom<=10) {
			rocket.setPosition((float)r.x-rocket.getWidth()/2, (float)r.y);
			rocket.setRotation((float)(Math.atan2(r.vy, r.vx)/(2*Math.PI)*360)-90);
			rocket.draw(batch);
		}
		else {
			rocketi.setPosition((float)r.x-rocket.getWidth()/2, (float)r.y);
			rocketi.setScale(cam.zoom/2);
			rocketi.draw(batch);
		}
		batch.end();
		
		shape.begin(ShapeType.Line);
		shape.setColor(new Color(-16777216));
		tmp=new Vector3(10,10,0);
		cam.unproject(tmp);
		shape.line(tmp.x, tmp.y, tmp.x+1000000, tmp.y);
		shape.end();
		
		if(fcd<=0) {
			r.debug();
			System.out.printf("Speed: %.2f    Zoom: %.2f\n",sim.speed,cam.zoom);
			fcd=30;
		}
		else {
			fcd--;
		}
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
	
	private void inputs() {
		double zdif=1;
		if(cam.zoom<2) {
			zdif=0.01;
		}
		else if(cam.zoom<30) {
			zdif=0.5;
		}
		else if(cam.zoom<500) {
			zdif=10;
		}
		else if(cam.zoom<30000){
			zdif=200;
		}
		else {
			zdif=1000;
		}
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			cam.zoom-=zdif;
			if(cam.zoom<0)cam.zoom=0;
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			cam.zoom+=zdif;
		}
		
		double sdif=1;
		if(sim.speed<=10) {
			sdif=0.1;
		}
		else if(sim.speed<=50) {
			sdif=1;
		}
		else if(sim.speed<=200) {
			sdif=5;
		}
		else {
			sdif=20;
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			sim.speed-=sdif;
			if(sim.speed<0)sim.speed=0;
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			sim.speed+=sdif;
		}
		
	}
}
