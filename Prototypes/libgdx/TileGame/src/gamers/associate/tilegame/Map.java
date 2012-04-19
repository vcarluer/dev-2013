package gamers.associate.tilegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {
	public static final int TILE_SIZE = 16;
	private SpriteBatch batch;
	private Tile[][] tiles;
	private int width;
	private int height;
	
	public Map() {		
		this.batch = new SpriteBatch();
		
		this.createMap();
	}
	
	private void createMap() {
		Pixmap pixmap = new Pixmap(Gdx.files.internal("data/map.png"));
		this.width = pixmap.getWidth();
		this.height = pixmap.getHeight();
		this.tiles = new Tile[this.width][this.height];
		
		for (int x = 0; x < this.width; x++) {
			for (int y = 0; y < this.height; y++) {
				int pixel = (pixmap.getPixel(x, y) >>> 8) & 0xffffff;
				Tile tile = null;
				switch (pixel) {
				case 0x36ff57:
					tile = new TileGrass();
					break;
				case 0xb89726:
					tile = new TileWall();
					break;
				}
				this.tiles[x][y] = tile;
			}
		}
	}
	
	public void render() {
		batch.begin();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				this.tiles[i][j].draw(this.batch, i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE);
			}
		}
		
		batch.end();
	}	
}
