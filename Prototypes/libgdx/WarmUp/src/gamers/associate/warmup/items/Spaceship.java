package gamers.associate.warmup.items;

import gamers.associate.warmup.WarmUp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Animator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.AnimationAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleTo;

public class Spaceship extends Actor{
	private static String resource = "data/spaceship.png";
	// private static String resource2 = "data/spaceship2.png";
	private Sprite sprite;
	private float targetX;
	private float targetY;
	private static float speed = 20f; // px per 0.5 sec
	
	private boolean spawn;
	
	private int life;
	
	@Override
	public void act(float delta) {
		super.act(delta);		
		if (!spawn) {
			ScaleTo scale = ScaleTo.$(3.0f, 3.0f, 1.0f);
			this.action(scale);
			spawn = true;
		}
	}

	public Spaceship() {
		Gdx.app.debug(WarmUp.Tag, "Creating spaceship");		
		this.sprite = new Sprite(new Texture(Gdx.files.internal(resource)));
		
		this.x = 200f;
		this.y = 50f;
		
		this.targetX = this.x;
		this.targetY = this.y;
		
		this.life = 1;
		
		
	}

	@Override
	public void touchUp(float x, float y, int pointer) {
		// TODO Auto-generated method stub
		super.touchUp(x, y, pointer);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
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
		boolean actionNeeded = false;
		switch (keycode) {
		case Keys.RIGHT:
			this.targetX += speed;
			actionNeeded = true;
			break;
		case Keys.LEFT:
			this.targetX -= speed;
			actionNeeded = true;
			break;
		default:
			return super.keyDown(keycode);
		}
		
		if (actionNeeded) {
			MoveTo moveTo = MoveTo.$(this.targetX, this.targetY, 0.5f);
			this.action(moveTo);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean keyUp(int keycode) {
		return super.keyUp(keycode);
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
