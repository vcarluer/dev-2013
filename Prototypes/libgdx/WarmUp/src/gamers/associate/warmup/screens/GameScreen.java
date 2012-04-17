package gamers.associate.warmup.screens;

import gamers.associate.warmup.WarmUp;
import gamers.associate.warmup.items.Spaceship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen implements Screen {	
	private Stage stage;
	private Spaceship spaceship;
	
	public GameScreen() {				
		this.stage = new Stage(0, 0, true);
		this.spaceship = new Spaceship();
		this.stage.addActor(this.spaceship);
		Gdx.input.setInputProcessor(this.stage);
		this.stage.setKeyboardFocus(this.spaceship);
	}
	
	@Override
	public void dispose() {
		this.stage.dispose();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		this.stage.act(Gdx.graphics.getDeltaTime());		
		this.stage.draw();
	}
	
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		this.stage.setViewport(width, height, true);
	}

}
