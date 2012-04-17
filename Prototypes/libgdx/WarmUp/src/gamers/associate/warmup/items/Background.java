package gamers.associate.warmup.items;

import gamers.associate.warmup.WarmUp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Forever;
import com.badlogic.gdx.scenes.scene2d.actions.MoveBy;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;

public class Background extends Actor {
	private Sprite fond;
	
	public Background() {
		Texture texture = new Texture(Gdx.files.internal("data/fond.png"));
		this.fond = new Sprite(texture);
		this.fond.setColor(1, 1, 1, 1.0f);
		MoveBy mb = MoveBy.$(0, -WarmUp.HEIGHT, 2.0f);
		MoveTo mt = MoveTo.$(0, 0, 0);
		Sequence seq = Sequence.$(mb, mt);
		Forever forever = Forever.$(seq);
		this.action(forever);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		float rot = 0;
		this.rotation += rot;
		this.fond.setRotation(this.rotation);
		this.fond.setPosition(this.x, this.y);
		this.fond.draw(batch);
		this.fond.setPosition(this.x, this.y + WarmUp.HEIGHT);
		this.fond.draw(batch);
	}

	@Override
	public Actor hit(float x, float y) {
		// TODO Auto-generated method stub
		return null;
	}

}
