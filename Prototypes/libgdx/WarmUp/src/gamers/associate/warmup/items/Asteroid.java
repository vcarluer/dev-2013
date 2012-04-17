package gamers.associate.warmup.items;

import gamers.associate.warmup.WarmUp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.OnActionCompleted;
import com.badlogic.gdx.scenes.scene2d.actions.Forever;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.actions.RotateBy;

public class Asteroid extends Actor {

	private static final float originalWidth = 16f;
	private static final float originalHeight = 16f;
	private Sprite sprite;
	private Actor actor;
	
	public Asteroid() {
		this.actor = this;
		this.sprite = new Sprite(new Texture(Gdx.files.internal("data/asteroid.png")));
		
		this.width = originalWidth;
		this.height = originalHeight;
		this.x = (float) (Math.random() * WarmUp.WIDTH);
		this.y = WarmUp.HEIGHT + this.height / 2f;
		
		float speed = (float) (3 + Math.random() * 5f) / WarmUp.get().getLevel();
		if (speed < 1) {
			speed = 1;
		}
		
		MoveTo moveTo = MoveTo.$((float) (Math.random() * WarmUp.WIDTH), - this.height / 2f, speed);
		moveTo.setCompletionListener(new OnActionCompleted() {
			
			@Override
			public void completed(Action action) {
				Gdx.app.debug(WarmUp.Tag, "asteroid to destination");
				WarmUp.get().removeActor(actor);
				Spaceship ship = WarmUp.get().getSpaceShip();
				if (ship.getState() != Spaceship.DESTROY) {
					WarmUp.get().addScore();
				}
			}
		});
		
		RotateBy rotate = RotateBy.$(360, 3f);
		Forever repeat = Forever.$(rotate);
		this.action(repeat);
		
		this.action(moveTo);
	}
	
	@Override
	public void act(float delta) {
		Spaceship ship = WarmUp.get().getSpaceShip();
		if (ship.getState() != Spaceship.DESTROY) {
			Rectangle recMe = new Rectangle(this.x - this.width / 2f, this.y - this.height / 2f, this.width, this.height);
			
			Rectangle recShip = new Rectangle(ship.x - ship.width / 2f, ship.y - ship.height / 2f, ship.width, ship.height);
			if (recMe.overlaps(recShip)) {
				ship.hurt();
				this.destroy();
			}
		} else {
			this.clearActions();
			WarmUp.get().removeActor(this);
		}
		
		super.act(delta);
	}

	private void destroy() {
		WarmUp.get().removeActor(this);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		
		this.sprite.setPosition(this.x - (originalWidth / 2f), this.y - (originalHeight / 2f));
		this.sprite.setScale(this.scaleX, this.scaleY);
		this.sprite.setRotation(this.rotation);
		this.sprite.draw(batch);
	}

	@Override
	public Actor hit(float x, float y) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
