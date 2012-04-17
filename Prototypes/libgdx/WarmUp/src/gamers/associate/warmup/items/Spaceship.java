package gamers.associate.warmup.items;

import gamers.associate.warmup.WarmUp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleTo;

public class Spaceship extends Actor{
	private static final int Frame_Cols = 2;
	private static int Frame_Rows = 1;
	private static String resource = "data/spaceship_pack.png";
	private Animation anim;
	private Sprite sprite;
	private float targetX;
	private float targetY;
	private static float speed = 20f; // px per 0.5 sec
	
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
		
		this.targetX = this.x;
		this.targetY = this.y;
		
		this.life = 1;
		
		this.sprite = new Sprite(texture, 16, 16);
		this.anim = new  Animation(0.1f, tr);
		this.stateTime = 0f;
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
