package com.zzzyt.rs.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.zzzyt.rs.RocketSimulator;

public class DesktopController extends InputAdapter{
	private static final double[] speeds= {0,0.1,0.2,0.5,1,1.5,2,5,10,50,100,500,1000,10000,100000,1000000};
	private int spd;
	
	private RocketSimulator rs;
	private Vector3 tp;
	private Vector3 tp2;
	
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
		case Input.Keys.V:
			rs.focusOn=!rs.focusOn;
			return true;
		case Input.Keys.SPACE:
			if(rs.r.stage<rs.r.stages.size()-1)rs.r.stage();
			return true;
		case Input.Keys.X:
			rs.r.throttle=0;
			return true;
		case Input.Keys.Z:
			rs.r.throttle=1;
			return true;
		case Input.Keys.LEFT:
			if(speeds[spd]!=rs.sim.speed) {
				for(int i=speeds.length-1;i>=0;i--) {
					if(speeds[i]<=rs.sim.speed) {
						spd=i;
					}
				}
			}
			if(spd>0)spd--;
			rs.sim.speed=speeds[spd];
			return true;
		case Input.Keys.RIGHT:
			if(speeds[spd]!=rs.sim.speed) {
				for(int i=speeds.length-1;i>=0;i--) {
					if(speeds[i]<=rs.sim.speed) {
						spd=i;
					}
				}
			}
			if(spd<speeds.length-1)spd++;
			rs.sim.speed=speeds[spd];
		default:
			return false;
		}
	}
	
	public DesktopController(){
		super();
		this.rs=RocketSimulator.rs;
		this.spd=4;
	}
}
