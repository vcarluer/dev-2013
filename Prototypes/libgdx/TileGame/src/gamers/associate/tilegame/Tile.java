package gamers.associate.tilegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Tile {
		
	public Tile() {		
	}
	
	public void draw(SpriteBatch batch, float x, float y, int size) {
		batch.draw(this.getTexture(), x, y, size, size);
	}
	
	protected abstract Texture getTexture();
}
