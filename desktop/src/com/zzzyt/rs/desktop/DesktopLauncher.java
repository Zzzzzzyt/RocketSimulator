package com.zzzyt.rs.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zzzyt.rs.RocketSimulator;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS=30;
		config.backgroundFPS=30;
		RocketSimulator game=new RocketSimulator();
		game.control=new DesktopController();
		new LwjglApplication(game, config);
	}
}
