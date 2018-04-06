package com.wolfsie.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.wolfsci.game.WolfSciGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "WolfSci";
		config.width = 960;
		config.height = 540;
		config.samples = 3;
		
		new LwjglApplication(new WolfSciGame(), config);
	}
}
