package gamers.associate.tilegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TileGrass extends Tile {
	private static Texture texture;
	private static Texture textureBorder;
	
	private static Texture getSingleTexture() {
		if (texture == null) {
			texture = new Texture(Gdx.files.internal("data/tile_grass.png"));
		}
		
		return texture;
	}
	
	private static Texture getSingleTextureBorder() {
		if (textureBorder == null) {
			textureBorder = new Texture(Gdx.files.internal("data/tile_grass_border.png"));
		}
		
		return textureBorder;
	}
	
	
	@Override
	protected Texture getTexture() {
		return getSingleTexture();
	}


	@Override
	protected Texture getTextureBorder() {
		return getSingleTextureBorder();
	}
}
