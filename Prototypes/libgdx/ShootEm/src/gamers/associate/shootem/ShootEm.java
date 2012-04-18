package gamers.associate.shootem;

import gamers.associate.shootem.items.Craft;
import gamers.associate.shootem.items.Poney;
import gamers.associate.shootem.items.PoneyGenerator;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class ShootEm extends Game {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private static final String Tag = "ShootEm";
	private static ShootEm game;
	private Stage stage;
	private Craft craft;
	private Music music;
	private PoneyGenerator generator;
	
	@Override
	public void create() {
		this.stage = new Stage(WIDTH, HEIGHT, true);
		this.craft = new Craft();
		this.stage.addActor(this.craft);
		
		generator = new PoneyGenerator();
		this.stage.addActor(generator);
		
		Gdx.input.setInputProcessor(this.stage);
		
		this.music = Gdx.audio.newMusic(Gdx.files.internal("data/shootem.mp3"));
		this.music.setLooping(true);
		this.music.play();
	}

	@Override
	public void resize(int width, int height) {
		this.stage.setViewport(width, height, true);
	}

	public static ShootEm get() {
		if (game == null) {
			game = new ShootEm();
		}
		
		return game;
	}

	@Override
	public void render() {
		this.stage.setKeyboardFocus(this.craft);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		this.stage.act(Gdx.graphics.getDeltaTime());
		this.stage.draw();
		super.render();
	}

	public Stage getStage() {
		return this.stage;
	}
	
	public PoneyGenerator getGenerator() {
		return this.generator;
	}
}
