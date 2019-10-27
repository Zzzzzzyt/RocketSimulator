package com.zzzyt.rs.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.zzzyt.rs.Handler;
import com.zzzyt.rs.RocketSimulator;

public class HtmlHandler implements Handler {
	RocketSimulator rs;
	
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
		
		if(Gdx.input.isKeyPressed(Keys.A)) {
			rs.r.gimbal+=0.01;
			if(rs.r.gimbal>0.2)rs.r.gimbal=0.2; 
		}
		if(Gdx.input.isKeyPressed(Keys.D)) {
			rs.r.gimbal-=0.01;
			if(rs.r.gimbal<-0.2)rs.r.gimbal=-0.2;
		}
		if(Gdx.input.isKeyPressed(Keys.Q)) {
			rs.r.dir+=0.05;
		}
		if(Gdx.input.isKeyPressed(Keys.E)) {
			rs.r.dir-=0.05;
		}
		
		if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			rs.r.throttle+=0.01;
			if(rs.r.throttle<0)rs.r.throttle=0;
		}
		if(Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)) {
			rs.r.throttle-=0.01;
			if(rs.r.throttle>1)rs.r.throttle=1;
		}
	}
	
	public HtmlHandler() {
		super();
		this.rs=RocketSimulator.rs;
	}
}
