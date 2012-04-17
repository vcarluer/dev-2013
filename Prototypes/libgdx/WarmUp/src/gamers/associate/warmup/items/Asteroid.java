package gamers.associate.warmup.items;

import gamers.associate.warmup.WarmUp;
import gamers.associate.warmup.screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.OnActionCompleted;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;

public class Asteroid extends Actor {

	private static final float originalWidth = 16f;
	private static final float originalHeight = 16f;
	private Sprite sprite;
	
	public Asteroid() {
		this.sprite = new Sprite(new Texture(Gdx.files.internal("data/asteroid.png")));
		
		this.width = this.originalWidth;
		this.height = this.originalHeight;
		this.x = GameScreen.WIDTH / 2f;
		this.y = GameScreen.HEIGHT + this.height / 2f;
		
		MoveTo moveTo = MoveTo.$(0, - this.height / 2f, 5f);
		moveTo.setCompletionListener(new OnActionCompleted() {
			
			@Override
			public void completed(Action action) {
				Gdx.app.debug(WarmUp.Tag, "asteroid to destination");
			}
		});
		
		this.action(moveTo);
	}
	
	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
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
