package gamers.associate.tilegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

public class TileGame extends Game {
	private static TileGame game;
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	private Map map;
	
	@Override
	public void create() {
		this.map = new Map();		
	}

	public static TileGame get() {
		if (game == null) {
			game = new TileGame();
		}
		
		return game;
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		this.map.render();
		super.render();
	}

}
