package gamers.associate.tilegame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class TileGameDesktop {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new LwjglApplication(TileGame.get(), "Tile game", TileGame.WIDTH, TileGame.HEIGHT, true);
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}

}
