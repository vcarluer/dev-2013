package gamers.associate.shootem;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class ShootEmDesktop {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new LwjglApplication(ShootEm.get(), "ShootEm", ShootEm.WIDTH, ShootEm.HEIGHT, true); 
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}

}
