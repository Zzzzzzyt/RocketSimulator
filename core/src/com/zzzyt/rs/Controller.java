package com.zzzyt.rs;

import com.badlogic.gdx.InputAdapter;

public abstract class Controller extends InputAdapter {
	public abstract boolean touchDown (int screenX, int screenY, int pointer, int button);
	public abstract boolean touchUp (int screenX, int screenY, int pointer, int button);
	public abstract boolean touchDragged (int screenX, int screenY, int pointer);
	public abstract boolean keyDown(int keycode);
	
	public abstract void handle();
}
