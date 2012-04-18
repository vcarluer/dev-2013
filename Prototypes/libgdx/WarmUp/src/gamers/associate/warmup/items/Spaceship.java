package gamers.associate.warmup.items;

import gamers.associate.warmup.WarmUp;
import gamers.associate.warmup.screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.FadeIn;
import com.badlogic.gdx.scenes.scene2d.actions.FadeOut;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.actions.Repeat;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleTo;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;

public class Spaceship extends Actor{
	private static final float START_Y = 50f;
	private static final float SCALE = 3f;
	private static final String DATA_ENGINE_WAV = "data/engine.wav";
	private static final int Frame_Cols = 2;
	private static final float Max_Velocity = 6;
	private static final float Damp = 0.9f;
	public static final int DESTROY = 9;
	private static final int BASE = 0;
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
	private int keycpt;
	private static float originalWidth = 16f;
	private static float originalHeight = 16f;
	
	private boolean spawn;
	private TextureRegion[] tr;
	private int index;
	
	private int life;
	private float stateTime;
	
	private Sound engineSound;
	private long engineSndId;
	private int state;
	
	private Sound hurtSound;
	private Sound destroySound;
	private Sound spawnSound;
	
	@Override
	public void act(float delta) {
		super.act(delta);		
		if (!spawn) {
			ScaleTo scale = ScaleTo.$(SCALE, SCALE, 0.5f);
			this.action(scale);
			this.spawnSound.play();
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
		
		if (this.x < this.width / 2f) {
			this.x = this.width / 2f;
		}
		
		if (this.x > WarmUp.WIDTH - (this.width / 2f)) {
			this.x = WarmUp.WIDTH - (this.width / 2f);
		}
		
		this.engineSound = Gdx.audio.newSound(Gdx.files.internal(DATA_ENGINE_WAV));
		
//		Gdx.app.debug(WarmUp.Tag, "x, y: " + String.valueOf(this.x) + ", " + String.valueOf(this.y));
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
		
		this.width = 12 * SCALE;
		this.height = 12 * SCALE;
		
		this.restart();
		
		this.sprite = new Sprite(texture, 16, 16);
		this.sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		this.anim = new  Animation(0.1f, tr);
		this.stateTime = 0f;
		
		this.velocity = new Vector2();
		this.acceleration = new Vector2();
		
		this.engineSndId = -1;
		
		this.hurtSound = Gdx.audio.newSound(Gdx.files.internal("data/hurt.wav"));
		this.destroySound = Gdx.audio.newSound(Gdx.files.internal("data/destroy.wav"));
		this.spawnSound = Gdx.audio.newSound(Gdx.files.internal("data/spawn.wav"));
		
	}

	@Override
	public void touchUp(float x, float y, int pointer) {
		// TODO Auto-generated method stub
		super.touchUp(x, y, pointer);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if (this.state != DESTROY) {
			this.stateTime += Gdx.graphics.getDeltaTime();
			TextureRegion region = this.anim.getKeyFrame(this.stateTime, true);
					
			this.sprite.setRegion(region);
			// sprite position ) always bottom left corner before transform!
			this.sprite.setPosition(this.x - (originalWidth / 2f), this.y - (originalHeight / 2f));
			this.sprite.setScale(this.scaleX, this.scaleY);
			this.sprite.setRotation(this.rotation);
			this.sprite.setColor(this.color);
			this.sprite.draw(batch);
		}
	}

	@Override
	public Actor hit(float x, float y) {
		return this;
//		 return x > 0 && x < width && y > 0 && y < height ? this : null;
	}

	@Override
	public boolean keyTyped(char character) {		
		return super.keyTyped(character);
	}

	@Override
	public boolean keyDown(int keycode) {
		if (this.state == DESTROY) {
			return false;
		}
			
		Gdx.app.debug(WarmUp.Tag, "Key down: " + String.valueOf(keycode));
		this.acceleration.x = 0;
		keycpt++;
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
		
		if (keycpt == 1) {
			this.engineSndId = this.engineSound.loop(0.3f);
		}
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (this.state == DESTROY) {
			return false;
		}
		
		if (keycpt > 0) {
			keycpt--;
			if (keycpt == 0) {
				this.acceleration.x = 0;
				this.engineSound.stop(this.engineSndId);
				this.engineSndId = -1;
			}
		}
		
		return true;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer) {
		return true;
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
		return this.state != DESTROY;
	}

	public void hurt() {
		if (this.state == DESTROY) {
			return;
		}
		
		this.life--;
		Gdx.app.debug(WarmUp.Tag, "life: " + String.valueOf(this.life));
		if (this.life == 0) {
			this.destroy();
		} else {
			FadeOut fo = FadeOut.$(0.1f);
			FadeIn fi = FadeIn.$(0.1f);
			Sequence seq = Sequence.$(fo, fi);
			Repeat rep = Repeat.$(seq, 3);
			this.action(rep);
			this.hurtSound.play();
		}
	}

	private void destroy() {
		if (this.engineSndId != -1) {
			this.engineSound.stop(this.engineSndId);
		}
		
		this.destroySound.play();
		this.state = DESTROY;
	}
	
	public int getState() {
		return this.state;
	}

	public void restart() {
		this.life = 1;
		this.state = BASE;
		this.keycpt = 0;
		
		this.x = WarmUp.WIDTH / 2f;
		this.y = START_Y;
		
		ScaleTo st = ScaleTo.$(1, 1, 0);
		this.action(st);
		this.spawn = false;
	}

	public void addLife() {
		this.life++;
	}
	
	public int getLife() {
		return this.life;
	}
}
