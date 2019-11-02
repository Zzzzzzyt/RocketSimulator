package com.zzzyt.rs.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zzzyt.rs.RocketSimulator;
import com.zzzyt.rs.input.DesktopController;
import com.zzzyt.rs.input.DesktopHandler;
import com.zzzyt.rs.util.DesktopFormatter;
import com.zzzyt.rs.util.StringUtil;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS=30;
		config.backgroundFPS=30;
		
		StringUtil.formatter=new DesktopFormatter();
		RocketSimulator game=new RocketSimulator();
		game.control=new DesktopController();
		game.handler=new DesktopHandler();
		
		new LwjglApplication(game, config);
	}
}
