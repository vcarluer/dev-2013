package gamers.associate.warmup.items;

import gamers.associate.warmup.WarmUp;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AsteroidGenerator extends Actor {
	private float cumDelta;
	private double next;
	@Override
	public void act(float delta) {
		if (!WarmUp.get().isGameOver()) {
			cumDelta += delta;
			if(cumDelta > next) {
				WarmUp.get().addNewActor(new Asteroid());
				cumDelta = 0;
				next = Math.random() * 3f;
			}
		}
		
		super.act(delta);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Actor hit(float x, float y) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void restart() {
		
	}
}
