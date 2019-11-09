package com.zzzyt.rs.rocket;

import com.badlogic.gdx.Gdx;
import com.zzzyt.rs.type.Rocket;

public class RocketHelper {
	public static Rocket get(String name) {
		if("F9B5 auto".equals(name))return new F9B5auto();
		if("F9B5 auto 2".equals(name))return new F9B5auto2();
		if("F9B5 manual".equals(name))return new F9B5manual();
		if("DDF".equals(name))return new DeepDarkFantasy();
		return null;
	}
	
	public static void printList() {
		Gdx.app.log("Rocket Manager", "F9B5 auto");
		Gdx.app.log("Rocket Manager", "F9B5 auto 2");
		Gdx.app.log("Rocket Manager", "F9B5 manual");
		Gdx.app.log("Rocket Manager", "DDF");
	}
}
