package gamers.associate.warmup;

import gamers.associate.warmup.items.Asteroid;
import gamers.associate.warmup.items.AsteroidGenerator;
import gamers.associate.warmup.items.Background;
import gamers.associate.warmup.items.Spaceship;
import gamers.associate.warmup.screens.GameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class WarmUp extends Game {	
	public static int WIDTH = 600;
	public static int HEIGHT = 800;
	private Stage stage;
	private Spaceship spaceship;
	private AsteroidGenerator generator;
	
	private static final String MusicPath = "data/warmup.mp3";
	public static final String Tag = "WarmUp";
	private static final float LEVEL_STEP  = 10f;
	private static WarmUp warmUp;	
	private Music music;
	private int score;
	
	private float fromStart;
	private int level;
	
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
		Background bkg = new Background();
		this.stage.addActor(bkg);
		
		this.spaceship = new Spaceship();
		this.stage.addActor(this.spaceship);
		
		generator = new AsteroidGenerator();
		this.stage.addActor(generator);
		
		Gdx.input.setInputProcessor(this.stage);
		
		
		this.init();
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
		this.stage.setKeyboardFocus(this.spaceship);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		float delta = Gdx.graphics.getDeltaTime();
		this.fromStart += delta;
		if (!this.isGameOver()) {
			if (this.fromStart > LEVEL_STEP ) {
				this.level++;
				this.fromStart = 0;
				this.spaceship.addLife();
			}
		}
		
		this.stage.act(delta);		
		this.stage.draw();
		super.render();
	}
	
	public Spaceship getSpaceShip() {
		return this.spaceship;
	}

	public void addNewActor(Actor actor) {
		this.stage.addActor(actor);
	}
	
	public void removeActor(Actor actor) {
		this.stage.removeActor(actor);
	}
	
	public void addScore() {
		this.score ++;
	}
	
	public int getScore() {
		return this.score;
	}

	public boolean isGameOver() {
		return this.spaceship.getState() == Spaceship.DESTROY;
	}
	
	public void restart() {
		this.init();
		this.spaceship.restart();
		this.generator.restart();
	}
	
	private void init() {
		this.score = 0;
		this.level = 1;
		this.fromStart = 0;
	}
	
	public int getLevel() {
		return this.level;
	}
}
