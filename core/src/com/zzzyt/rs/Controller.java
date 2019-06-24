package com.zzzyt.rs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;

public class Controller extends InputAdapter {
	RocketSimulator rs;
	Vector3 tp,tp2;
	
	public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		if(button!=Input.Buttons.RIGHT)return false;
		tp=new Vector3(screenX,screenY,0);
		tp2=new Vector3(0,0,0);
		return true;
	}

	public boolean touchUp (int screenX, int screenY, int pointer, int button) {
		tp=null;
		return true;
	}

	public boolean touchDragged (int screenX, int screenY, int pointer) {
		if(tp!=null) {
			tp2=new Vector3(screenX,screenY,0);
			rs.cam.translate(rs.cam.unproject(tp).sub(rs.cam.unproject(tp2)));
			tp.x=screenX;
			tp.y=screenY;
			return true;
		}
		return false;
	}
	
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Input.Keys.SHIFT_RIGHT:
			rs.focusOn=!rs.focusOn;
			return true;
		case Input.Keys.SPACE:
			if(rs.r.stage<rs.r.stages.size()-1)rs.r.stage();
			return true;
		default:
			return false;
		}
	}
	
	public void handle() {
		double zdif=1;
		if(rs.cam.zoom<1.5) {
			zdif=0.01;
		}
		else if(rs.cam.zoom<20) {
			zdif=0.5;
		}
		else if(rs.cam.zoom<100) {
			zdif=2;
		}
		else if(rs.cam.zoom<1000) {
			zdif=20;
		}
		else if(rs.cam.zoom<10000){
			zdif=200;
		}
		else if(rs.cam.zoom<100000){
			zdif=1000;
		}
		else {
			zdif=10000;
		}
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			rs.cam.zoom-=zdif;
			if(rs.cam.zoom<0.01)rs.cam.zoom=0.01f;
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			rs.cam.zoom+=zdif;
		}
		
		double sdif=1;
		if(rs.sim.speed<=10) {
			sdif=0.1;
		}
		else if(rs.sim.speed<=50) {
			sdif=1;
		}
		else if(rs.sim.speed<=200) {
			sdif=5;
		}
		else {
			sdif=20;
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			rs.sim.speed-=sdif;
			if(rs.sim.speed<0)rs.sim.speed=0;
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			rs.sim.speed+=sdif;
		}
		
		if(Gdx.input.isKeyPressed(Keys.A)) {
			rs.r.gimbal+=0.01;
			if(rs.r.gimbal>0.2)rs.r.gimbal=0.2;
		}
		if(Gdx.input.isKeyPressed(Keys.D)) {
			rs.r.gimbal-=0.01;
			if(rs.r.gimbal<-0.2)rs.r.gimbal=-0.2;
		}
		if(Gdx.input.isKeyPressed(Keys.Q)) {
			rs.r.dir+=0.01;
		}
		if(Gdx.input.isKeyPressed(Keys.E)) {
			rs.r.dir-=0.01;
		}
		
		if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			rs.r.throttle+=0.01;
			if(rs.r.throttle<0)rs.r.throttle=0;
		}
		if(Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)) {
			rs.r.throttle-=0.01;
			if(rs.r.throttle>1)rs.r.throttle=1;
		}
		if(Gdx.input.isKeyPressed(Keys.Z)) {
			rs.r.throttle=0;
		}
		if(Gdx.input.isKeyPressed(Keys.X)) {
			rs.r.throttle=1;
		}
	}
	
	Controller(RocketSimulator rs){
		super();
		this.rs=rs;
	}
}
