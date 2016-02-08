package com.dylan.demoneer.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dylan.demoneer.Demoneer;

import java.awt.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Demoneer";
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		config.width = size.width;
		config.height = size.width;
		new LwjglApplication(new Demoneer(), config);
	}
}
