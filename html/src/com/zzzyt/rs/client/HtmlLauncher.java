package com.zzzyt.rs.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.badlogic.gdx.input.GestureDetector;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.zzzyt.rs.RocketSimulator;
import com.zzzyt.rs.input.HtmlMobileController;
import com.zzzyt.rs.input.HtmlMobileHandler;
import com.zzzyt.rs.util.HtmlFormatter;
import com.zzzyt.rs.util.StringUtil;

public class HtmlLauncher extends GwtApplication {

	// USE THIS CODE FOR A FIXED SIZE APPLICATION
//	@Override
//	public GwtApplicationConfiguration getConfig() {
//		return new GwtApplicationConfiguration(480*3, 320*3);
//	}
	// END CODE FOR FIXED SIZE APPLICATION

	// UNCOMMENT THIS CODE FOR A RESIZABLE APPLICATION
	// PADDING is to avoid scrolling in iframes, set to 20 if you have problems
	private static final int PADDING = 0;
	private GwtApplicationConfiguration cfg;

	@Override
	public GwtApplicationConfiguration getConfig() {
		int w = Window.getClientWidth() - PADDING;
		int h = Window.getClientHeight() - PADDING;
		cfg = new GwtApplicationConfiguration(w, h);
		Window.enableScrolling(false);
		Window.setMargin("0");
		Window.addResizeHandler(new ResizeListener());
		cfg.preferFlash = false;
		return cfg;
	}

	class ResizeListener implements ResizeHandler {
		@Override
		public void onResize(ResizeEvent event) {
			int width = event.getWidth() - PADDING;
			int height = event.getHeight() - PADDING;
			getRootPanel().setWidth("" + width + "px");
			getRootPanel().setHeight("" + height + "px");
			getApplicationListener().resize(width, height);
			Gdx.graphics.setWindowedMode(width, height);
		}
	}
	// END OF CODE FOR RESIZABLE APPLICATION

	@Override
	public ApplicationListener createApplicationListener() {
		StringUtil.formatter=new HtmlFormatter();
		RocketSimulator game = new RocketSimulator();
//		game.control = new HtmlDesktopController();
//		game.handler = new HtmlDesktopHandler();
		game.control=new GestureDetector(new HtmlMobileController());
		game.handler=new HtmlMobileHandler();
		game.showButtons=true;
		return game;
	}
}