package com.zzzyt.rs.rocket;

import com.badlogic.gdx.Gdx;
import com.zzzyt.rs.type.Rocket;

public class RocketManager {
	public static Rocket get(String name) {
		if(name.equals("F9B5 auto"))return new F9B5auto();
		if(name.equals("F9B5 auto 2"))return new F9B5auto2();
		if(name.equals("F9B5 manual"))return new F9B5manual();
		if(name.equals("DDF"))return new DeepDarkFantasy();
		return null;
	}
	
	public static void printList() {
		Gdx.app.log("Rocket Manager", "F9B5 auto");
		Gdx.app.log("Rocket Manager", "F9B5 auto 2");
		Gdx.app.log("Rocket Manager", "F9B5 manual");
		Gdx.app.log("Rocket Manager", "DDF");
	}
}
