package gamers.associate.warmup;

import gamers.associate.warmup.items.Asteroid;
import gamers.associate.warmup.items.Spaceship;
import gamers.associate.warmup.screens.GameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class WarmUp extends Game {	
	public static int WIDTH = 300;
	public static int HEIGHT = 480;
	private Stage stage;
	private Spaceship spaceship;
	
	private static final String MusicPath = "data/warmup.mp3";
	public static final String Tag = "WarmUp";
	private static WarmUp warmUp;	
	private Music music;
	
	public static WarmUp get() {
		if (warmUp == null) {
			warmUp = new WarmUp();
		}
		
		return warmUp;
	}
	
	@Override
	public void create() {		
		this.setScreen(new GameScreen());	
		this.music = Gdx.audio.newMusic(Gdx.files.internal(MusicPath));
		this.music.setLooping(true);
		this.music.play();
		
		this.stage = new Stage(0, 0, true);
		this.spaceship = new Spaceship();
		this.stage.addActor(this.spaceship);
		
		Asteroid asteroid = new Asteroid();
		this.stage.addActor(asteroid);
		
		Gdx.input.setInputProcessor(this.stage);
		this.stage.setKeyboardFocus(this.spaceship);
	}

	@Override
	public void dispose() {
		this.music.stop();
		this.stage.dispose();
		super.dispose();
	}

	@Override
	public void resize(int width, int height) {
		this.stage.setViewport(width, height, true);
		super.resize(width, height);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		this.stage.act(Gdx.graphics.getDeltaTime());		
		this.stage.draw();
		super.render();
	}
}
