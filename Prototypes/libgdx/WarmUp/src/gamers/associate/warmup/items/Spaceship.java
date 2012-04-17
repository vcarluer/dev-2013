package gamers.associate.warmup.items;

import gamers.associate.warmup.WarmUp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleTo;

public class Spaceship extends Actor{
	private static final int Frame_Cols = 2;
	private static final float Max_Velocity = 6;
	private static final float Damp = 0.9f;
	private static int Frame_Rows = 1;
	private static String resource = "data/spaceship_pack.png";
	private static int LEFT = -1;
	private static int RIGHT = 1;
	private int direction;
	private Animation anim;
	private Sprite sprite;
	private static float Acceleration = 20f; // px per 0.5 sec
	private Vector2 acceleration;
	private Vector2 velocity;
	
	private boolean spawn;
	private TextureRegion[] tr;
	private int index;
	
	private int life;
	private float stateTime;
	
	@Override
	public void act(float delta) {
		super.act(delta);		
		if (!spawn) {
			ScaleTo scale = ScaleTo.$(3.0f, 3.0f, 1.0f);
			this.action(scale);
			spawn = true;
		}
		
		float accTime = this.acceleration.x * delta;
		if (this.acceleration.x != 0) {
			velocity.x += accTime;
		} else {
			velocity.x *= Damp;
		}
		
		if (this.velocity.x > Max_Velocity) {
			this.velocity.x = Max_Velocity;
		}
		
		if (this.velocity.x < - Max_Velocity) {
			this.velocity.x = - Max_Velocity;
		}
		
		this.x = this.x + this.velocity.x;
	}

	public Spaceship() {
		Gdx.app.debug(WarmUp.Tag, "Creating spaceship");		
		Texture texture = new Texture(Gdx.files.internal(resource));
		TextureRegion[][] animR = TextureRegion.split(texture, 16, 16);
		tr = new TextureRegion[2];
		int idx = 0;
		for(int i = 0; i < Frame_Rows; i++) {
			for (int j = 0; j < Frame_Cols; j++) {
				tr[idx] = animR[i][j];
				idx++;
			}
		}
		
		this.x = 200f;
		this.y = 50f;
		
		this.life = 1;
		
		this.sprite = new Sprite(texture, 16, 16);
		this.anim = new  Animation(0.1f, tr);
		this.stateTime = 0f;
		
		this.velocity = new Vector2();
		this.acceleration = new Vector2();
	}

	@Override
	public void touchUp(float x, float y, int pointer) {
		// TODO Auto-generated method stub
		super.touchUp(x, y, pointer);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		this.stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion region = this.anim.getKeyFrame(this.stateTime, true);
				
		this.sprite.setRegion(region);
		this.sprite.setPosition(this.x, this.y);
		this.sprite.setScale(this.scaleX, this.scaleY);
		this.sprite.setRotation(this.rotation);
		this.sprite.draw(batch);
	}

	@Override
	public Actor hit(float x, float y) {
		 return x > 0 && x < width && y > 0 && y < height ? this : null;
	}

	@Override
	public boolean keyTyped(char character) {		
		return super.keyTyped(character);
	}

	@Override
	public boolean keyDown(int keycode) {
		Gdx.app.debug(WarmUp.Tag, "Key down: " + String.valueOf(keycode));
		this.acceleration.x = 0;
		switch (keycode) {
		case Keys.RIGHT:
			this.direction = RIGHT;
			break;
		case Keys.LEFT:
			this.direction = LEFT;
			break;
		default:
			return super.keyDown(keycode);
		}
		
		this.acceleration.x = Acceleration * this.direction;

		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		this.acceleration.x = 0;
		return true;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer) {
		// TODO Auto-generated method stub
		return super.touchDown(x, y, pointer);
	}

	@Override
	public void touchDragged(float x, float y, int pointer) {
		// TODO Auto-generated method stub
		super.touchDragged(x, y, pointer);
	}

	@Override
	public boolean touchMoved(float x, float y) {
		// TODO Auto-generated method stub
		return super.touchMoved(x, y);
	}

	public boolean isAlive() {
		return this.life > 0;
	}
}
