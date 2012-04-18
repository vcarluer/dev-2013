package gamers.associate.shootem.items;

import java.util.ArrayList;

import gamers.associate.shootem.ShootEm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class LaserBeam extends Actor {
	private static final float SCALE = 2f;
	private static final float sourceWidth = 8f;
	private static final float sourceHeight = 8f;
	private Sprite sprite;
	private static float velocity = ShootEm.WIDTH;
	
	public LaserBeam(float x, float y) {
		this.sprite = new Sprite(new Texture(Gdx.files.internal("data/shoot.png")));
		this.x = x;
		this.y = y;
		
		this.scaleX = SCALE;
		this.scaleY = SCALE;
		
		this.width = 8 * SCALE;
		this.height = 2 * SCALE;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		this.sprite.setPosition(this.x - sourceWidth / 2f, this.y - sourceHeight / 2f);
		this.sprite.setScale(this.scaleX, this.scaleY);
		this.sprite.draw(batch);
	}

	@Override
	public void act(float delta) {
		float moveX = velocity * delta;
		this.x += moveX;
		
		if (this.x > ShootEm.WIDTH + this.width / 2f) {
			ShootEm.get().getStage().removeActor(this);
		}
		
		ArrayList<Poney> toKill = new ArrayList<Poney>();
		Rectangle hitbox = new Rectangle(this.x - this.width / 2f, this.y - this.height / 2f, this.width, this.height);
		for(Poney poney : ShootEm.get().getGenerator().getPoneys()) {
			Rectangle poneyBox = poney.getHitBox();
			if (hitbox.overlaps(poneyBox)) {
				toKill.add(poney);
			}
		}
		
		for(Poney poney : toKill) {
			poney.kill();
		}
		
		super.act(delta);
	}

	@Override
	public Actor hit(float x, float y) {
		return null;
	}

}
