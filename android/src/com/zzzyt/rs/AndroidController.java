package com.zzzyt.rs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector.GestureAdapter;

public class AndroidController extends GestureAdapter{
	RocketSimulator rs;
	
	private boolean inBox(float x,float y,float x1,float y1,float w,float h) {
		return x1<=x&&y1<=y&&x<=x1+w&&y<=y1+h;
	}
	
	public boolean tap (float x, float y, int count, int button) {
		if(inBox(x,y,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight())) {
			if(rs.r.stage<rs.r.stages.size()-1)rs.r.stage();
			return true;
		}
		return false;
	}
	
	public AndroidController(){
		super();
		this.rs=RocketSimulator.rs;
	}
}
