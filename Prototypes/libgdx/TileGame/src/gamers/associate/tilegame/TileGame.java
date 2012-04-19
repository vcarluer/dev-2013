package gamers.associate.tilegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

public class TileGame extends Game {
	private static final float CAMERA_ROTATION = 0.5f;
	private static final int CAMERA_VELOCITY = 3;
	private static TileGame game;
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	private Map map;
	private OrthographicCamera cam;
	private Rectangle viewport;
	
	private Particle particle;
	
	@Override
	public void create() {
		this.map = new Map();	
		this.viewport = new Rectangle(0, 0, WIDTH, HEIGHT);
		this.cam = new OrthographicCamera(this.viewport.width, this.viewport.height);
		this.cam.position.set(WIDTH / 2, HEIGHT / 2, 0);
		this.particle = new Particle();
	}

	public static TileGame get() {
		if (game == null) {
			game = new TileGame();
		}
		
		return game;
	}

	@Override
	public void render() {
		this.handleInput();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);				
		Gdx.gl.glViewport((int) viewport.x, (int) viewport.y, (int) viewport.width, (int) viewport.height);				
		cam.update();
		cam.apply(Gdx.gl10);
		
		this.map.render();				
		this.particle.render();
		
		
		super.render();
	}

	private void handleInput() {
		if (Gdx.input.isKeyPressed(Keys.Z)) {
			this.cam.zoom += 0.02;
		}
		
		if (Gdx.input.isKeyPressed(Keys.A)) {
			this.cam.zoom -= 0.02;
		}
		
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			this.cam.translate(CAMERA_VELOCITY, 0, 0);
		}
		
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			this.cam.translate(-CAMERA_VELOCITY, 0, 0);
		}
		
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			this.cam.translate(0, CAMERA_VELOCITY, 0);
		}
		
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			this.cam.translate(0, - CAMERA_VELOCITY, 0);
		}
		
		if (Gdx.input.isKeyPressed(Keys.S)) {
			this.cam.rotate(CAMERA_ROTATION, 0, 0, 1);
		}
		
		if (Gdx.input.isKeyPressed(Keys.Q)) {
			this.cam.rotate(-CAMERA_ROTATION, 0, 0, 1);
		}
	}
	
	public OrthographicCamera getCamera() {
		return this.cam;
	}
}
