package items;

import gamers.associate.shootem.ShootEm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Craft extends Actor {
	private static final float StartX = 100f;
	private static final int UP = 1;
	private static final int DOWN = -1;
	private static final int SPEED = 20;
	private static final float DAMP = 0.9f;
	private static final int NONE = 0;
	private static final float MAX_VELOCITY = 6;
	private Sprite sprite;
	private float originalWidth = 16f;
	private float originalHeight = 16f;
	private static float SCALE = 2f; 
	private Vector2 acceleration;
	private Vector2 velocity;
	private int yDirection;
	
	public Craft() {
		this.sprite = new Sprite(new Texture(Gdx.files.internal("data/craft.png")));
		this.x = StartX;
		this.y = ShootEm.HEIGHT / 2f;
		this.scaleX = SCALE;
		this.scaleY = SCALE;
		this.width = originalWidth * this.scaleX;
		this.height = originalHeight * this.scaleY;
		
		this.acceleration = new Vector2();
		this.velocity = new Vector2();
	}
	
	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
			case Keys.UP:
				this.yDirection = UP;
				break;
			case Keys.DOWN:
				this.yDirection = DOWN;
				break;
			default:
				return false;
		}
		
		this.acceleration.y = this.yDirection * SPEED;
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode != Keys.UP && keycode != Keys.DOWN) {
			return true;
		}
		
		if (keycode == Keys.UP && this.yDirection != UP) {
			return true;
		}
		
		if (keycode == Keys.DOWN && this.yDirection != DOWN) {
			return true;
		}
		
		this.yDirection = NONE;
		this.acceleration.y = 0;
		return true;
	}

	@Override
	public void act(float delta) {
		
		if (this.acceleration.y != 0) {
			float yAcceTime = this.acceleration.y * delta;
			this.velocity.y += yAcceTime;
		} else {
			this.velocity.y *= DAMP;
		}
		
		if (this.velocity.y > MAX_VELOCITY) {
			this.velocity.y = MAX_VELOCITY;
		}
		
		if (this.velocity.y < - MAX_VELOCITY) {
			this.velocity.y = - MAX_VELOCITY;
		}
		
		this.y += this.velocity.y;
		
		super.act(delta);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		this.sprite.setPosition(this.x - this.originalWidth / 2f, this.y - this.originalHeight / 2f);
		this.sprite.setScale(this.scaleX, this.scaleY);
		this.sprite.draw(batch);
	}

	@Override
	public Actor hit(float x, float y) {
		return this;
	}
	
}
