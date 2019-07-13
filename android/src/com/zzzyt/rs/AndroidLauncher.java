package com.zzzyt.rs;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.input.GestureDetector;
import com.zzzyt.rs.RocketSimulator;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		RocketSimulator game=new RocketSimulator();
		initialize(game, config);
		game.control=new GestureDetector(new AndroidController());
		game.handler=new AndroidHandler();
		game.showButtons=true;
	}
}
