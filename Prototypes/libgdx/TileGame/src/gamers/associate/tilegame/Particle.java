package gamers.associate.tilegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Particle {
	private SpriteBatch batch;
	private ParticleEffect effect;
	
	public Particle() {
		this.batch = new SpriteBatch();
		this.effect = new ParticleEffect();
		this.effect.loadEmitters(Gdx.files.internal("data/particle.p"));
		this.effect.loadEmitterImages(Gdx.files.internal("data"));
		this.effect.setPosition(TileGame.WIDTH / 2, TileGame.HEIGHT / 2);
		
	}
	
	public void render() {
		batch.setProjectionMatrix(TileGame.get().getCamera().combined);
		batch.begin();
		this.effect.draw(batch, Gdx.graphics.getDeltaTime());
		// this.effect.getEmitters().get(0).draw(batch, Gdx.graphics.getDeltaTime());
		batch.end();
	}
}
