package gamers.associate.shootem;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;


public class ShootEm extends Game {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private static final String Tag = "ShootEm";
	private static ShootEm game;
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	public static ShootEm get() {
		if (game == null) {
			game = new ShootEm();
		}
		
		return game;
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		super.render();
	}
	
}
