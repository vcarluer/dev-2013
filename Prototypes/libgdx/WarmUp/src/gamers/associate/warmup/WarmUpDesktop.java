package gamers.associate.warmup;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class WarmUpDesktop {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new LwjglApplication(WarmUp.get(), "WarmUp", 300, 480, true);
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}
}
