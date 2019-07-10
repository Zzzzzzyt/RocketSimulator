package com.zzzyt.rs;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.zzzyt.rs.data.Rocket;
import com.zzzyt.rs.phy.Phy;
import com.zzzyt.rs.phy.Simulator;
import com.zzzyt.rs.rocket.RocketManager;

public class RocketSimulator extends ApplicationAdapter{
	public static RocketSimulator rs;
	
	SpriteBatch batch;
	Sprite rocket;
	Sprite rocketi;
	Sprite exhaust;
	
	Matrix4 defaultMat;
	public OrthographicCamera cam;
	ShapeRenderer shape;
	BitmapFont font;
	FitViewport viewport;
	
	float h,w;
	
	public InputProcessor control;
	public Handler handler;
	public Simulator sim;
	public Rocket r;
	
	private int fcd;

	public boolean focusOn;
	
	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.input.setInputProcessor(control);
		
		h=Gdx.graphics.getHeight();
		w=Gdx.graphics.getWidth();
		
		r = RocketManager.get("DDF");
		sim=new Simulator(30,1);
		
		font=new BitmapFont(Gdx.files.internal("font/debug.fnt"),Gdx.files.internal("font/debug.png"),false);
		font.setColor(Color.WHITE);
		
		shape=new ShapeRenderer();
		defaultMat=shape.getProjectionMatrix().cpy();
		batch = new SpriteBatch();
		
		rocket = new Sprite(new Texture("rocket.png"));
		rocket.setRegion(0, 0, 23, 45);
		rocket.setSize(23, 45);
		rocket.setOrigin(rocket.getWidth()/2,0);
		
		rocketi = new Sprite(new Texture("rocketi.png"));
		rocketi.setRegion(0, 0, 32, 32);
		rocketi.setOriginCenter();

		exhaust = new Sprite(new Texture("exhaust.png"));
		exhaust.setRegion(0,0,11,32);
		exhaust.setOrigin(exhaust.getWidth()/2, exhaust.getHeight());
		
		cam=new OrthographicCamera(w,h);
		cam.position.set(cam.viewportWidth/2f,cam.viewportHeight/2f,0);
		cam.setToOrtho(false);
		cam.translate(0,(float)Phy.R);
		cam.update();
		
		viewport=new FitViewport(w,h,cam);
		
		focusOn=true;
		fcd=0;
	}

	@Override
	public void render() {
		handler.handle();
		
		if(!sim.stopped)sim.sim();
		
		if(focusOn) {
			cam.position.set((float)r.x, (float)r.y, 0);
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
		
		cam.update();
		
		shape.setProjectionMatrix(cam.combined);
		
		shape.begin(ShapeType.Filled);
		shape.setColor(new Color(0,0,0.3f,1));
		shape.circle(0, 0, (float)(Phy.R+Phy.up));
		shape.end();
		
		shape.begin(ShapeType.Filled);
		shape.setColor(new Color(0.1f,0.2f,0.6f,1));
		shape.circle(0, 0, (float)(Phy.R+Phy.up/2));
		shape.end();
		
		shape.begin(ShapeType.Filled);
		shape.setColor(new Color(0,0.5f,0,1));
		shape.circle(0, 0, (float)Phy.R);
		shape.end();
		
		batch.setProjectionMatrix(cam.combined);
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
		
		if(r.getThrust()>0) {
			exhaust.setPosition((float)r.x-exhaust.getWidth()/2, (float)r.y-exhaust.getHeight());
			exhaust.setRotation((float)(r.getTheta()/(2*Math.PI)*360)-90);
			exhaust.setScale((float)(Math.cbrt(r.getThrust())/200));
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
		
		batch.setProjectionMatrix(defaultMat);
		batch.begin();
		font.draw(batch, "Rocket Name: "+r.name,10,h-10);
		font.draw(batch, String.format("t=%.1f x=%.2f y=%.2f vx=%.2f vy=%.2f m=%.0f", r.t,r.x,r.y,r.vx,r.vy,r.getMass()), 10,h-30);
		font.draw(batch,String.format("sp=%.2f zm=%.2f tr=%.2f f=%.2f h=%.1f drag=%.2f th=%.2f",sim.speed,cam.zoom,r.throttle,r.stages.get(r.stage).f,Phy.tri(r.x,r.y)-Phy.R,Phy.tri(r.x,r.y)>=Phy.R+Phy.up?0:r.getDrag()*(Phy.R+Phy.up-Phy.tri(r.x,r.y))/Phy.up,r.getTheta()/Math.PI),10,h-50);
		if(len>=1000) {
			font.draw(batch,String.format("%.0fkm", len/1000),20,30);
		}
		else {
			font.draw(batch,String.format("%.0fm", len),20,30);
		}
		batch.end();
		
		shape.setProjectionMatrix(defaultMat);
		
		shape.begin(ShapeType.Filled);
		shape.setColor(new Color(1,1,1,1));
		shape.rect(10, 10, len/cam.zoom, 2);
		shape.end();
		
		shape.begin(ShapeType.Filled);
		shape.setColor(new Color(1,1,1,1));
		shape.rect(10, 10, 2, 5);
		shape.end();
		
		shape.begin(ShapeType.Filled);
		shape.setColor(new Color(1,1,1,1));
		shape.rect(10+len/cam.zoom-2, 10, 2, 5);
		shape.end();
		
		if(fcd<=0) {
			Gdx.app.debug("Rocket", String.format("t=%.1f x=%.2f y=%.2f vx=%.2f vy=%.2f m=%.0f", r.t,r.x,r.y,r.vx,r.vy,r.getMass())+"\n\t"+String.format("sp=%.2f zm=%.2f tr=%.2f f=%.2f h=%.1f drag=%.2f th=%.2f",sim.speed,cam.zoom,r.throttle,r.stages.get(r.stage).f,Phy.tri(r.x,r.y)-Phy.R,Phy.tri(r.x,r.y)>=Phy.R+Phy.up?0:r.getDrag()*(Phy.R+Phy.up-Phy.tri(r.x,r.y))/Phy.up,r.getTheta()/Math.PI));
			fcd=30;
		}
		else {
			fcd--;
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
		shape.dispose();
		rocket.getTexture().dispose();
		rocketi.getTexture().dispose();
		exhaust.getTexture().dispose();
	}
	
	@Override
	public void resize(int width,int height) { 
		w=width;
		h=height;
		float oldzoom=cam.zoom;
		cam=new OrthographicCamera(w,h);
		cam.position.set(cam.viewportWidth/2f,cam.viewportHeight/2f,0);
		cam.setToOrtho(false);
		cam.zoom=oldzoom;
		cam.update();
		viewport=new FitViewport(w,h,cam);
		ShapeRenderer tmp=new ShapeRenderer();
		defaultMat=tmp.getProjectionMatrix();
		tmp.dispose();
	}
	
	public RocketSimulator() {
		super();
		RocketSimulator.rs=this;
	}
}
