package com.zzzyt.rs;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;

public class MyInput extends InputAdapter {
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
		default:
			return false;
		}
	}
	
	MyInput(RocketSimulator rs){
		super();
		this.rs=rs;
	}
}
