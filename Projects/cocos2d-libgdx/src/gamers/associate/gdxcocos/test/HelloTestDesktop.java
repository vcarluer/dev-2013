package gamers.associate.gdxcocos.test;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class HelloTestDesktop {
	public static void main (String[] argv) {
		new  LwjglApplication(new HelloTest(), "Hello test", 480, 320, false);
	}
}
