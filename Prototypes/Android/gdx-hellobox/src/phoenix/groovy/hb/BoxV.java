package phoenix.groovy.hb;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class BoxV {
	private Sprite mSprite;
	private Body mBody;
	
	public int forceApplyCount;
	
	public BoxV(Sprite sprite, Body body) {
		this.setSprite(sprite);
		this.setBody(body);
	}
	
	public void setSprite(Sprite sprite) {
		this.mSprite = sprite;
	}
	
	public void setBody (Body body) {
		this.mBody = body;
	}
	
	public Body getBody() {
		return this.mBody;
	}
	
	public boolean getForceApplied() {
		if (this.forceApplyCount >= 6) {
			return true;
		}
		else {
			return false;
		}			
	}
	
	
	public void draw(SpriteBatch spriteBach) {
		this.mSprite.setPosition((this.mBody.getPosition().x * HelloWorld.box2DRatio) - (this.mSprite.getWidth() / 2), this.mBody.getPosition().y * HelloWorld.box2DRatio - (this.mSprite.getHeight() / 2));
		this.mSprite.setRotation((float) Math.toDegrees(this.mBody.getAngle()));
		this.mSprite.draw(spriteBach);
	}
	
	public Vector2 getPosition() {
		return this.mBody.getPosition();
	}
}
