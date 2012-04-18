package gamers.associate.shootem.items;

import java.util.ArrayList;
import java.util.List;

import gamers.associate.shootem.ShootEm;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PoneyGenerator extends Actor {
	private float actTime;
	private float spawnTime;
	private List<Poney> poneys;
	
	public PoneyGenerator() {
		this.spawnTime = 1;
		this.poneys = new ArrayList();
	}
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
	}

	@Override
	public Actor hit(float x, float y) {
		return null;
	}

	@Override
	public void act(float delta) {
		actTime += delta;
		if (actTime > spawnTime) {
			float x = (float) (200f + Math.random() * 200f);
			float y = (float) (100f + Math.random() * 400f);
			Poney poney = new Poney(x, y);
			ShootEm.get().getStage().addActor(poney);
			this.poneys.add(poney);
			actTime = 0;
		}
		super.act(delta);
	}
	
	public List<Poney> getPoneys() {
		return  this.poneys;
	}
	
	public void removePoney(Poney poney) {
		this.poneys.remove(poney);
	}
}
