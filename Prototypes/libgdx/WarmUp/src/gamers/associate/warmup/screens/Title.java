package gamers.associate.warmup.screens;

import gamers.associate.warmup.WarmUp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Title implements Screen {
	private SpriteBatch batch;
	private Sprite sprite;
	
	public Title() {
		this.batch = new SpriteBatch();
		this.sprite = new Sprite(new Texture(Gdx.files.internal("data/title.png")));
		this.sprite.setSize(WarmUp.WIDTH, WarmUp.HEIGHT);
	}
	@Override
	public void render(float delta) {
		this.batch.begin();
		this.sprite.draw(this.batch);
		this.batch.end();
		if (Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
			WarmUp.get().start();
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
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
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
