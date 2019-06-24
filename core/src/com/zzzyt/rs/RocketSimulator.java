package com.zzzyt.rs;

import com.badlogic.gdx.Application;
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
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.zzzyt.rs.data.Rocket;
import com.zzzyt.rs.phy.Physics;
import com.zzzyt.rs.phy.Simulator;
import com.zzzyt.rs.rocket.RocketManager;

public class RocketSimulator extends ApplicationAdapter{
	SpriteBatch batch;
	Sprite rocket;
	Sprite rocketi;
	Sprite exhaust;
	
	public OrthographicCamera cam;
	ShapeRenderer shape;
	FitViewport viewport;
	public Controller control;
	
	float h,w;
	
	Simulator sim;
	public Rocket r;
	
	private int fcd;

	public boolean focusOn;
	
	private Vector3 tmp,tmp2;
	
	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		
		h=Gdx.graphics.getHeight();
		w=Gdx.graphics.getWidth();
		
		r = RocketManager.get("F9B5 manual");
		sim=new Simulator(this,30,1);
		
		shape=new ShapeRenderer();
		
		batch = new SpriteBatch();
		rocket = new Sprite(new Texture(Gdx.files.internal("rocket.png")));
		rocket.setRegion(0, 0, 23, 45);
		rocket.setOrigin(rocket.getWidth()/2,0);
		
		rocketi = new Sprite(new Texture(Gdx.files.internal("rocketi.png")));
		rocketi.setRegion(0, 0, 32, 32);
		rocketi.setOriginCenter();

		exhaust = new Sprite(new Texture(Gdx.files.internal("exhaust.png")));
		exhaust.setRegion(0,0,11,32);
		exhaust.setOrigin(exhaust.getWidth()/2, exhaust.getHeight());
		
		cam=new OrthographicCamera(w,h);
		cam.position.set(cam.viewportWidth/2f,cam.viewportHeight/2f,0);
		cam.setToOrtho(false);
		cam.translate(0,(float)Physics.R);
		cam.update();
		
		viewport=new FitViewport(w,h,cam);
		
		control=new Controller(this);
		Gdx.input.setInputProcessor(control);
		
		focusOn=true;
		fcd=0;
	}

	@Override
	public void render() {
		control.handle();
		
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
			rocket.setRotation((float)(r.dir/(2*Math.PI)*360)-90);
			rocket.draw(batch);
		}
		else {
			rocketi.setPosition((float)r.x-rocketi.getWidth()/2, (float)r.y-rocketi.getHeight()/2);
			rocketi.setRotation((float)(r.dir/(2*Math.PI)*360)-90);
			rocketi.setScale(cam.zoom/2);
			rocketi.draw(batch);
		}
		
		if(r.getTr()>0) {
			exhaust.setPosition((float)r.x-exhaust.getWidth()/2, (float)r.y-exhaust.getHeight());
			exhaust.setRotation((float)(r.getTheta()/(2*Math.PI)*360)-90);
			exhaust.setScale((float)(Math.cbrt(r.getTr())/200));
			exhaust.draw(batch);
		}
		
		
		batch.end();
		
		float len=0;
		if(cam.zoom<0.2) {
			len=10;
		}
		else if(cam.zoom<3) {
			len=100;
		}
		else if(cam.zoom<30) {
			len=1E3f;
		}
		else if(cam.zoom<300) {
			len=1E4f;
		}
		else if(cam.zoom<3E3f) {
			len=1E5f;
		}
		else if(cam.zoom<3E4f){
			len=1E6f;
		}
		else if(cam.zoom<3E5f) {
			len=1E7f;
		}
		else {
			len=1E8f;
		}
		tmp=new Vector3(10,10,0);
		tmp2=new Vector3(12,8,0);
		cam.unproject(tmp);
		cam.unproject(tmp2);
		
		shape.begin(ShapeType.Filled);
		shape.setColor(new Color(-16777216));
		shape.rect(tmp.x, tmp.y, len, tmp.y-tmp2.y);
		shape.end();
		
		shape.begin(ShapeType.Filled);
		shape.setColor(new Color(-16777216));
		shape.rect(tmp.x, tmp2.y, tmp2.x-tmp.x, tmp.y-tmp2.y);
		shape.end();
		
		shape.begin(ShapeType.Filled);
		shape.setColor(new Color(-16777216));
		shape.rect(tmp.x+len-(tmp2.x-tmp.x), tmp2.y, tmp2.x-tmp.x, tmp.y-tmp2.y);
		shape.end();
		
		if(fcd<=0) {
			Gdx.app.debug("Rocket", String.format("t=%.1f x=%.2f y=%.2f vx=%.2f vy=%.2f m=%.0f", r.t,r.x,r.y,r.vx,r.vy,r.getMass())+"\n\t"+String.format("sp=%.2f zm=%.2f tr=%.2f f=%.2f h=%.1f drag=%.2f th=%.2f",sim.speed,cam.zoom,r.throttle,r.stages.get(r.stage).f,Physics.tri(r.x,r.y)-Physics.R,Physics.tri(r.x,r.y)>=Physics.R+Physics.up?0:r.getDrag()*(Physics.R+Physics.up-Physics.tri(r.x,r.y))/Physics.up,r.getTheta()/Math.PI));
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
	
	
}
