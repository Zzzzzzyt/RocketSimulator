package com.zzzyt.rs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector.GestureAdapter;
import com.zzzyt.rs.draw.ButtonDrawer;

public class AndroidController extends GestureAdapter{
	private RocketSimulator rs;
	private ButtonDrawer b;
	private float h,w,scale;
	private float oldZoom;
	
	private boolean inBox(float x,float y,float x1,float y1,float w,float h) {
		return x1-w/2<=x&&y1-h/2<=y&&x<=x1+w/2&&y<=y1+h/2;
	}

	@Override
	public boolean tap (float x, float y, int count, int button) {
		if(b==null)this.b=RocketSimulator.rs.drawer.buttons;
		y=Gdx.graphics.getHeight()-1-y;
		h=RocketSimulator.rs.h;
		w=RocketSimulator.rs.w;
		
		scale=0.5f;
		if(Math.min(h,w*9/16)>700)scale=1f;

		if(inBox(x,y,w-200*scale, 80*scale,100,100)) {
			//dirl
		}
		else if(inBox(x,y,w-80*scale, 80*scale,100,100)) {
			//dirr
		}
		else if(inBox(x,y,w-200*scale, 200*scale,100,100)) {
			//giml
		}
		else if(inBox(x,y,w-80*scale, 200*scale,100,100)) {
			//gimr
		}
		else if(inBox(x,y,80*scale, 200*scale,100,100)) {
			//thu
		}
		else if(inBox(x,y,80*scale,80*scale,100,100)) {
			//thd
		}
		else if(inBox(x,y,200*scale,200*scale,100,100)) {
			//thmax
		}
		else if(inBox(x,y,200*scale,80*scale,100,100)) {
			//thmin
		}
		else if(inBox(x,y,w-80*scale,h-80*scale,100,100)) {
			//spu
		}
		else if(inBox(x,y,w-80*scale,h-200*scale,100,100)) {
			//spd
		}
		else if(inBox(x,y,w-240*scale,h-80*scale,100,100)) {
			//focus
			rs.focusOn=!rs.focusOn;
			b.Pfocus=!b.Pfocus;
		}
		else if(count==3){
            //stage
            if(rs.r.stage<rs.r.stages.size()-1)rs.r.stage();
        }
		else {
			return false;
		}
		return true;
	}

	@Override
	public boolean zoom(float initialDistance,float distance){
		if(oldZoom==-100){
			oldZoom=rs.cam.zoom;
		}
		rs.cam.zoom=oldZoom*initialDistance/distance;
		return true;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY){
		rs.cam.position.x-=deltaX*rs.cam.zoom;
		rs.cam.position.y+=deltaY*rs.cam.zoom;
		return true;
	}

	@Override
	public void pinchStop() {
		oldZoom = -100;
	}

	public AndroidController(){
		super();
		this.rs=RocketSimulator.rs;
		this.oldZoom=-100;
	}
}
