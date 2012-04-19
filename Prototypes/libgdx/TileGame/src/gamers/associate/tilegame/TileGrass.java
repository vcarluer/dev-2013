package gamers.associate.tilegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TileGrass extends Tile {
	private static Texture texture;
	
	private static Texture getSingleTexture() {
		if (texture == null) {
			texture = new Texture(Gdx.files.internal("data/tile_grass.png"));
		}
		
		return texture;
	}
	
	
	@Override
	protected Texture getTexture() {
		return getSingleTexture();
	}
}
