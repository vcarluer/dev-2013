package gamers.associate.warmup.items;

import gamers.associate.warmup.WarmUp;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AsteroidGenerator extends Actor {
	private static final double MIN_TIME = 0.1f;
	private float cumDelta;
	private double next;
	@Override
	public void act(float delta) {
		if (!WarmUp.get().isGameOver()) {
			cumDelta += delta;
			if(cumDelta > next) {
				WarmUp.get().addNewActor(new Asteroid());
				cumDelta = 0;
				next = (Math.random() * 3f) / WarmUp.get().getLevel();
				if (next < MIN_TIME) {
					next = MIN_TIME;
				}
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
