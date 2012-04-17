package gamers.associate.warmup;

import gamers.associate.warmup.screens.GameScreen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class WarmUpDesktop {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new LwjglApplication(WarmUp.get(), "WarmUp", GameScreen.WIDTH, GameScreen.HEIGHT, true);
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}
}
