package gamers.associate.warmup.screens;

import gamers.associate.warmup.WarmUp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {	
	private static final int PADDING = 10;
	private BitmapFont font;
	private BitmapFont fontGO;
	private SpriteBatch batch;
	
	public GameScreen() {				
		this.font = new BitmapFont(Gdx.files.internal("data/ar.fnt"), Gdx.files.internal("data/ar.png"), false);
		this.font.setColor(Color.YELLOW);
		this.fontGO = new BitmapFont(Gdx.files.internal("data/ar.fnt"), Gdx.files.internal("data/ar.png"), false);
		this.fontGO.setColor(Color.YELLOW);
		this.fontGO.setScale(2f);
		this.batch = new SpriteBatch();
	}
	
	@Override
	public void dispose() {
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	
	private float cumDelta;

	@Override
	public void render(float delta) {
		this.batch.begin();
		
		if (WarmUp.get().isGameOver()) {
			cumDelta += delta;
			if (cumDelta > 1f) {
				fontGO.draw(this.batch, "INSERT COIN", 50, WarmUp.HEIGHT / 2f - 150f);
				if (Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
					this.cumDelta = 0;
					WarmUp.get().restart();
				}
			}
			fontGO.draw(this.batch, "GAME OVER", 50, WarmUp.HEIGHT / 2f);
			fontGO.draw(this.batch, "SCORE: " + String.valueOf(WarmUp.get().getScore()), 50, WarmUp.HEIGHT / 2f - 75f);
		} else {
			font.draw(this.batch, "Level: " + String.valueOf(WarmUp.get().getLevel()), 0 + PADDING, WarmUp.HEIGHT - PADDING);
			font.draw(this.batch, "Score: " + String.valueOf(WarmUp.get().getScore()), 0 + PADDING, WarmUp.HEIGHT - PADDING - 30);
			font.draw(this.batch, "Life: " + String.valueOf(WarmUp.get().getSpaceShip().getLife()), 0 + PADDING, WarmUp.HEIGHT - PADDING - 60);
		}
		
		this.batch.end();
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
		
	}
}
