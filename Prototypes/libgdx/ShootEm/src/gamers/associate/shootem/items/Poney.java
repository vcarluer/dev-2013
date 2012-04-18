package gamers.associate.shootem.items;

import gamers.associate.shootem.ShootEm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Poney extends Actor {
	private static final int DYING = 9;
	private Sprite sprite;
	private static float originalWidth = 32f;
	private static float originalHeight = 32f;
	private TextureAtlas atlas;
	private Animation animDeath;
	private int state;
	private float cumTime;
	
	public Poney(float x, float y) {
		
		this.x = x;
		this.y = y;
		this.width = 32f * this.scaleX;
		this.height = 32f * this.scaleY;
		
		this.atlas = new TextureAtlas(Gdx.files.internal("data/pack"));
		this.sprite = new Sprite(this.atlas.findRegion("poney"));
		this.animDeath = new Animation(0.1f, this.atlas.findRegion("poneyd1"),
				this.atlas.findRegion("poneyd2"),
				this.atlas.findRegion("poneyd3"),
				this.atlas.findRegion("poneyd4"),
				this.atlas.findRegion("poneyd5"),
				this.atlas.findRegion("poneyd6"),
				this.atlas.findRegion("poneyd7"));
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		this.sprite.setPosition(this.x - originalWidth / 2f, this.y - this.originalHeight / 2f);
		if (this.state == DYING) {
			this.cumTime += Gdx.graphics.getDeltaTime();
			TextureRegion region = this.animDeath.getKeyFrame(this.cumTime, false);
			this.sprite.setRegion(region);
		}
		
		this.sprite.draw(batch);
		if (this.cumTime > 1) {
			ShootEm.get().getStage().removeActor(this);
			ShootEm.get().getGenerator().removePoney(this);
		}
	}

	@Override
	public Actor hit(float x, float y) {
		return null;
	}

	public Rectangle getHitBox() {
		Rectangle hitbox = new Rectangle(this.x - this.width / 2f, this.y - this.height / 2f, this.width, this.height);
		return hitbox;
	}

	public void kill() {
		this.state = DYING;
		
	}

}
