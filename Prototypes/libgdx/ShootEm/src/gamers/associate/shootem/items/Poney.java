package gamers.associate.shootem.items;

import gamers.associate.shootem.ShootEm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Poney extends Actor {
	private Sprite sprite;
	private static float originalWidth = 32f;
	private static float originalHeight = 32f;
	
	public Poney(float x, float y) {
		this.sprite = new Sprite(new Texture(Gdx.files.internal(("data/poney.png"))));
		this.x = x;
		this.y = y;
		this.width = 32f * this.scaleX;
		this.height = 32f * this.scaleY;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		this.sprite.setPosition(this.x - originalWidth / 2f, this.y - this.originalHeight / 2f);
		this.sprite.draw(batch);
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
		ShootEm.get().getStage().removeActor(this);
		ShootEm.get().getGenerator().removePoney(this);
	}

}
