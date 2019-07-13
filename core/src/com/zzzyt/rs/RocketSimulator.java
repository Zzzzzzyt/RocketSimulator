package com.zzzyt.rs;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.zzzyt.rs.draw.Drawer;
import com.zzzyt.rs.type.Rocket;
import com.zzzyt.rs.phy.Phy;
import com.zzzyt.rs.phy.Simulator;
import com.zzzyt.rs.rocket.RocketManager;

import java.util.Locale;

public class RocketSimulator extends ApplicationAdapter{
	public static RocketSimulator rs;

	public OrthographicCamera cam;
	public FitViewport viewport;

	public Drawer drawer;

	public float h,w;
	
	public InputProcessor control;
	public Handler handler;
	public Simulator sim;
	public Rocket r;
	
	private int fcd;

	public boolean focusOn;
	public boolean showButtons = false;
	
	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.input.setInputProcessor(control);
		
		h=Gdx.graphics.getHeight();
		w=Gdx.graphics.getWidth();
		
		r = RocketManager.get("DDF");
		sim=new Simulator(30,1);

		drawer=new Drawer();
		drawer.init();
		
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
        cam.update();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));

        drawer.drawTerrian();
        drawer.drawRocket();
        drawer.drawOverlay();
        if(showButtons){
            drawer.drawGUI();
        }

		if(fcd<=0) {
			Gdx.app.debug("Rocket", String.format(Locale.getDefault(),"t=%.1f x=%.2f y=%.2f vx=%.2f vy=%.2f m=%.0f", r.t,r.x,r.y,r.vx,r.vy,r.getMass())+"\n\t"+String.format(Locale.getDefault(),"sp=%.2f zm=%.2f tr=%.2f f=%.2f h=%.1f drag=%.2f th=%.2f",sim.speed,cam.zoom,r.throttle,r.stages.get(r.stage).f,Phy.tri(r.x,r.y)-Phy.R,Phy.tri(r.x,r.y)>=Phy.R+Phy.up?0:r.getDrag()*(Phy.R+Phy.up-Phy.tri(r.x,r.y))/Phy.up,r.getTheta()/Math.PI));
			fcd=30;
		}
		else {
			fcd--;
		}
	}

	@Override
	public void dispose() {

		drawer.dispose();
	}
	
	@Override
	public void resize(int width,int height) { 
		w=width;
		h=height;
		float oldZoom=cam.zoom;
		Vector3 oldPos=cam.position;
		cam=new OrthographicCamera(w,h);
		cam.position.set(cam.viewportWidth/2f,cam.viewportHeight/2f,0);
		cam.setToOrtho(false);
		cam.zoom=oldZoom;
		cam.position.set(oldPos);
		cam.update();
		viewport=new FitViewport(w,h,cam);
		ShapeRenderer tmp=new ShapeRenderer();
		drawer.defaultMat=tmp.getProjectionMatrix();
		tmp.dispose();
	}
	
	public RocketSimulator() {
		super();
		RocketSimulator.rs=this;
	}
}
